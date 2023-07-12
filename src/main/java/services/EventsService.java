package services;

import exceptions.UserNotRegistered;
import model.Calendar;
import model.CalendarImpl;
import model.Event;
import model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EventsService {

    public void addUser(String name);
    public boolean userExists(String name);
    // User getUser(String name);
    public void addEvent(String username, Event ev);
    public void showDay(String username, LocalDate dt);

    public void showAll(String username);

    public Event getConflictedEvent(String username, LocalDateTime start, LocalDateTime end);

    public void userHasAccepted(String name, Integer s);

    public void userHasDeclined(String name, int parseInt);
}
