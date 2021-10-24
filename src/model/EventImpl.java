package model;

import services.TokenService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventImpl implements Event{
    int id;
    String title;
    String desc;
    String organiser;
    List<String> invitees;
    LocalDateTime st,end;

    public LocalDateTime getStart() {
        return st;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    public String toString(){
        return st.toLocalTime()+"-"+end.toLocalTime()+": "+title+" with "+organiser+" and "+invitees.toString();
    }
    public EventImpl(TokenService tokenService){
        id = tokenService.getToken();
        invitees=new ArrayList<>();
    }
    @Override
    public int compareTo(Event o) {
        return (this.getStart().compareTo(o.getStart()));
    }
    //setters
    public void setTitle(String utitle){title=utitle;}
    public void setStart(LocalDateTime start) {st=start;}
    public void setEnd(LocalDateTime uend) {end=uend;}


    public void setOrganiser(String org){organiser=org;}

    @Override
    public Integer getId() {
        return id;
    }


    public void addInvitees(String[] arrayi) {
        for (String u:arrayi)
            invitees.add(u);
    }
}
