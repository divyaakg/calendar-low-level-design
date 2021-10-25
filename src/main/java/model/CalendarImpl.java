package main.java.model;

import java.time.LocalDate;
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


}
