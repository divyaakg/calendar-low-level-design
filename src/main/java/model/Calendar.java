package main.java.model;

import java.time.LocalDate;

public interface Calendar {
    void showDay();

    void addEvent(Event a);


    LocalDate getDate();
}
