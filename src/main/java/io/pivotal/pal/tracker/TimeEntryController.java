package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(path="/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry te = timeEntryRepository.create(timeEntryToCreate);
        if(te != null){
            ResponseEntity re = new ResponseEntity(te, HttpStatus.CREATED);
            System.out.println("Status Create :"+re.getStatusCode());
            return re;
        }
        else
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry te = timeEntryRepository.find(id);
        if(te != null){
            return new ResponseEntity(te, HttpStatus.OK);
        }
        else
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path="/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> te = timeEntryRepository.list();
        if(te != null){
            return new ResponseEntity(te, HttpStatus.OK);
        }
        else
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry te = timeEntryRepository.update(id,expected);
        if(te != null){
            return new ResponseEntity(te, HttpStatus.OK);
        }
        else
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity( HttpStatus.NO_CONTENT);
    }
}
