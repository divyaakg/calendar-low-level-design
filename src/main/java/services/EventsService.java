package main.java.services;

import main.java.model.Calendar;
import main.java.model.CalendarImpl;
import main.java.model.Event;
import main.java.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsService {
    List<User> userCal;
    Map<Integer,Event> allEvents;


    public EventsService(){
        userCal=new ArrayList<User>();
        allEvents=new HashMap<>();
    }
    public void addUser(String name){
        User u=new User(name);
        userCal.add(u);
    }
    private User getUser(String name){
        for(User u:userCal){
            if(u.getUsername().equals(name))
                return u;
        }
        return null;
    }
    public void addEvent(String username, Event ev){
        LocalDate dt=ev.getStart().toLocalDate();
        User u=getUser(username);
        if(u==null){
            u=new User(username);
            userCal.add(u);
        }
        //System.out.println(u);
        Calendar day =u.getDay(dt);
        if(day== null){
            day=new CalendarImpl(dt);
            u.addDay(day);
        } day.addEvent(ev);
        allEvents.put(ev.getId(),ev);
        //System.out.println(u);
    }
    public void showDay(String username, LocalDate dt){
        User u=getUser(username);
        if (u.getDay(dt) == null) {
            u.addDay(new CalendarImpl(dt));
        }
        u.getDay(dt).showDay(username);
    }

    public void showAll(String username) {
        User u=getUser(username);
        u.showAll(username);
    }

    public Event getConflictedEvent(String username, LocalDateTime start, LocalDateTime end){
        return getUser(username).getConflictedEvent(start,end);
    }

    public void userHasAccepted(String name, Integer s) {
        Event evt = allEvents.get(s);
        if(!evt.getOrganiser().equals(name))
            evt.getInvitees().saveAcceptFrom(name);
        if(!evt.getInvitees().isUserInvited(name)) {
            System.out.println("There is no such event in your calendar to accept. You need to be invited by the organiser");
            return;
        }
    }

    public void userHasDeclined(String name, int parseInt) {
        Event evt= allEvents.get(parseInt);
        if(evt.getOrganiser().equals(name)) {
            System.out.println("Since you are the organiser of this, you cannot decline. Use the deleteEvent command");
            return;
        } if(!evt.getInvitees().isUserInvited(name)) {
            System.out.println("There is no such event in your calendar to decline. You need to be invited by the organiser");
            return;
        } evt.getInvitees().saveDeclineFrom(name);
        Calendar day = getUser(name).getDay(evt.getStart().toLocalDate());
        day.deleteEvent(evt);//delete the event from the calendar
        //but what if the organiser says declineEvent?

    }
}
