package main.java.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
public class User {
    //Calendar calendar;
    String name;
    List<Calendar> days;

    public String getUsername() {
        return name;
    }
    public Calendar getDay(LocalDate dt){
        for(Calendar day:days){
            //System.out.println(day);
            if (day.getDate().equals(dt))
                return day;
        }
        return null;
    }
    public void addDay(Calendar day){
        days.add(day);
    }
    public User(String n){
        name=n;
        days=new ArrayList<>();
    }
    public String toString(){
        return name+": "+days.toString();
    }
    public void showAll() {
        for(Calendar day:days)
            day.showDay();
        if(days.size()==0)
            System.out.println("** Nothing to show **");
    }

    public Event getConflictedEvent(LocalDateTime start, LocalDateTime end) {
        Calendar day =getDay(start.toLocalDate());
        if(day==null)
            return null;
        else
            return day.getEventConflict(start.toLocalTime(),end.toLocalTime());
    }
}
