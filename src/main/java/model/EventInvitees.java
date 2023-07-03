package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EventInvitees {
    Set<String> accepts;
    Set<String> declines;
    Set<String> maybes;

    public EventInvitees(){
        accepts=new HashSet<>();
        declines=new HashSet<>();
        maybes=new HashSet<>();
    }

    public void saveAcceptFrom(String name){
        accepts.add(name);
        maybes.remove(name);
    }
    public void saveDeclineFrom(String name){
        declines.add(name);
        maybes.remove(name);
        accepts.remove(name);
    }
    public boolean isUserInvited(String name){
        if(accepts.contains(name) || maybes.contains(name))
            return true;
        else
            return false;
    }

    public boolean hasUserDeclined(String name){
        if(declines.contains(name))
            return true;
        else
            return false;

     }
     public void addInvitees(String[] names){
        for(String name:names)
            maybes.add(name);
     }

     public String toString(){
        return "accepted:"+accepts.toString()+" declines:"+declines+" maybes"+maybes.toString();
     }

}
