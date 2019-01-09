package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(){
        //No Data
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeEntry)) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return getId() == timeEntry.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours)
    {
        this.id=id;
        this.projectId=projectId;
        this.userId=userId;
        this.date=date;
        this.hours=hours;
    }

    public TimeEntry( long projectId, long userId, LocalDate date, int hours)
    {
        this.projectId=projectId;
        this.userId=userId;
        this.date=date;
        this.hours=hours;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }
}
