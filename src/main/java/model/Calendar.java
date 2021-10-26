package main.java.model;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Calendar {
    void showDay();

    void addEvent(Event a);


    LocalDate getDate();

    Event getEventConflict(LocalTime st, LocalTime end);
}
