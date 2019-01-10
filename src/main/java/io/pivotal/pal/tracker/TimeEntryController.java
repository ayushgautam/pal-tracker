package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry) {

        this.timeEntryRepository = timeEntryRepository;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");

        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
        System.out.println("CHECK_CONSTRUCT:"+actionCounter);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry te = timeEntryRepository.create(timeEntryToCreate);
        if(te != null){
            ResponseEntity re = new ResponseEntity(te, HttpStatus.CREATED);
            System.out.println("CHECK:"+actionCounter);
            actionCounter.increment();
            timeEntrySummary.record(timeEntryRepository.list().size());

            return re;
        }
        else
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry te = timeEntryRepository.find(id);
        if(te != null){
            actionCounter.increment();
            return new ResponseEntity(te, HttpStatus.OK);
        }
        else
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> te = timeEntryRepository.list();

        if(te != null){
            actionCounter.increment();
            return new ResponseEntity(te, HttpStatus.OK);
        }
        else
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry te = timeEntryRepository.update(id,expected);
        if(te != null){
            actionCounter.increment();
            return new ResponseEntity(te, HttpStatus.OK);
        }
        else
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());
        return new ResponseEntity( HttpStatus.NO_CONTENT);
    }
}
