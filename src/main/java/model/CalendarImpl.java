package main.java.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CalendarImpl implements Calendar {
    LocalDate date;
    List<Event> events;


    @Override
    public void showDay() {
        System.out.println("*********************Showing "+date.toString()+"*********************");
        Collections.sort(events);
        for(Object e:events){
            System.out.println(e.toString());
        }
    }
    public LocalDate getDate(){
        return date;
    }
    @Override
    public void addEvent(Event a) {
        events.add(a);

    }
    public String toString(){
        return date+": "+events.toString();
    }
    public CalendarImpl(LocalDate dt){
        date=dt;
        events=new ArrayList<>();
    }
    public Event getEventConflict(LocalTime start, LocalTime end){
        for (Event ev:events){
            LocalTime evtSt=ev.getStart().toLocalTime();
            LocalTime evtEnd=ev.getEnd().toLocalTime();

            if(!evtSt.isBefore(start) && evtSt.isBefore(end))
                return ev;
            //if ev ends after start and before or at end
            if(!evtEnd.isAfter(end) && evtEnd.isAfter(start))
                return ev;
        }
        return null;
    }

}
