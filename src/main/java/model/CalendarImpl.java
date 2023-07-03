package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CalendarImpl implements Calendar {
    LocalDate date;
    List<Event> events;


    @Override
    public void showDay(String name) {
        System.out.println("*********************Showing "+date.toString()+" for "+name+" *********************");
        Collections.sort(events);
        for(Event e:events){
            System.out.println(e.toString());
        }
    }
    public LocalDate getDate(){
        return date;
    }

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
    public void deleteEvent(Event ev){
        events.remove(ev);
    }
}
