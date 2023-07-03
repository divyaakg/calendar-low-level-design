package model;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Calendar {
    void showDay(String name);

    void addEvent(Event a);


    LocalDate getDate();

    Event getEventConflict(LocalTime st, LocalTime end);
    void deleteEvent(Event ev);
}
