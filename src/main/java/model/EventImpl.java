package main.java.model;

import main.java.services.TokenService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventImpl implements Event{
    int id;
    String title;
    String desc;
    String organiser;
    //List<String> invitees;
    EventInvitees invitees;
    LocalDateTime st,end;

    public EventImpl(TokenService tokenService){
        id = tokenService.getToken();
        //invitees=new ArrayList<>();
        invitees=new EventInvitees();
    }

    public LocalDateTime getStart() {
        return st;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    public Integer getId() {
        return id;
    }
    public EventInvitees getInvitees(){return invitees;}
    public String getOrganiser(){return organiser;}

    public void setTitle(String utitle){title=utitle;}
    public void setStart(LocalDateTime start) {st=start;}
    public void setEnd(LocalDateTime uend) {end=uend;}
    public void setOrganiser(String org){organiser=org;}

//    public void addInvitees(String[] arrayi) {
//        for (String u:arrayi)
//            invitees.add(u);
//    }

    public String toString(){
        return st.toLocalTime()+"-"+end.toLocalTime()+": eventid= "+id+", "+title+" by "+organiser+". People "+invitees.toString();
    }
    public int compareTo(Event o) {
        return (this.getStart().compareTo(o.getStart()));
    }



}
