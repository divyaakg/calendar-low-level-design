package services;

import model.Calendar;
import model.CalendarImpl;
import model.Event;
import model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventsServiceImpl implements EventsService {
    List<User> userCal;
    Map<Integer, Event> allEvents;


    public EventsServiceImpl(){
        userCal=new ArrayList<User>();
        allEvents=new HashMap<>();
    }
    public void addUser(String name){
        User u=new User(name);
        userCal.add(u);
    }
    public boolean userExists(String name){
        for(User u:userCal){
            if(u.getUsername().equals(name))
                return true;
        } return false;
    }
    private User getUser(String name){
        for(User u:userCal){
            if(u.getUsername().equals(name))
                return u;
        }
        System.out.println("User "+name+" does not exist");
        //throw new UserNotRegistered("Need to register "+name);
        return null;
    }
    public void addEvent(String username, Event ev){
        LocalDate dt=ev.getStart().toLocalDate();
        User u=getUser(username);
        if(u==null){
            u=new User(username);
            userCal.add(u);
            System.out.println("Added event in user "+username+" calendar");
        }
        //System.out.println(u);
        Calendar day =u.getDay(dt);
        if(day== null){
            day=new CalendarImpl(dt);
            u.addDay(day);
        } day.addEvent(ev);
        try {
            System.out.println("Working to create the event");
            Thread.sleep(10000);
        } catch(InterruptedException e){
            ;
        } allEvents.put(ev.getId(),ev);
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
