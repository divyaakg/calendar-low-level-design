package main.java.commands;


import main.java.exceptions.NotSignedInException;
import main.java.model.Event;
import main.java.model.EventImpl;
import main.java.services.EventsService;
import main.java.services.TokenService;
import main.java.services.UserSessionService;

import java.time.LocalDateTime;
import java.util.*;

public class CreateEvent extends BaseCommand{
    TokenService tokenService;
    String organiser;

    public CreateEvent(String[] inp, EventsService evsvc, TokenService tknsvc, UserSessionService sessionService) {
        //help.add("Example: createEvent lunch 2021-10-19T14:00:00 2019-01-04T14:30:00 tim,john\n");
        super(inp, evsvc,sessionService);
        tokenService=tknsvc;
    }

    private boolean isValid(){
        if(usercomm.length<5) {
            System.out.println("Invalid createEvent command");
            return false;
        } if(usercomm.length==6 && !usercomm[5].equals("dontcare")){
            System.out.println("Invalid createEvent command");
            return false;
        }
        try {
            String org = sessionService.getSession();
            this.organiser = org;
        } catch (NotSignedInException e) {
            System.out.println("Sign in first to execute createEvent command");
            return false;
        }
        return true;
    }

    private boolean caresForConflicts(){
        if(usercomm.length==6 && usercomm[5].equals("dontcare"))
            return false;
        else
            return true;
    }

    public void execute() {
        if (!isValid())
            return;
        String[] invitees = usercomm[4].split(",");
        LocalDateTime start=LocalDateTime.parse(usercomm[2]);
        LocalDateTime end= LocalDateTime.parse(usercomm[3]);

        String [] newinvitees = removeOrganiserFromInviteesIfPresent(invitees);

        if (caresForConflicts() && hasConflicts(newinvitees,start,end)) {
            System.out.println("If you want to proceed anyway, type the same " +
                    "createEvent command with \"dontcare\" (without quotes) at the end");
        } else{
            createEvent(newinvitees,start,end);
        }
    }

    private void createEvent(String [] invitees,LocalDateTime start, LocalDateTime end){
        EventImpl event=new EventImpl(tokenService);
        event.setTitle(usercomm[1]);
        event.setStart(start);
        event.setEnd(end);
        event.setOrganiser(this.organiser);

        event.getInvitees().addInvitees(invitees);
        evtsvc.addEvent(this.organiser, event);
        for(String name:invitees){
            evtsvc.addEvent(name, event);
        }
    }

    private boolean hasConflicts(String[] invitees, LocalDateTime st,LocalDateTime end){
        boolean isConflicted=false;
        for(String inv:invitees){
            Event evt= evtsvc.getConflictedEvent(inv,st,end);
            if (evt != null) {
                System.out.println(inv + " has a " + evt.getStart() + " to " + evt.getEnd() + " event that conflicts");
                isConflicted=true;
            }
        }
        return isConflicted;
    }

    private String[] removeOrganiserFromInviteesIfPresent(String[] invitees ){
        int count=0;
        for(int i=0;i<invitees.length;i++){
            if(invitees[i].equals(this.organiser)){
                count++;
            }
        } if(count ==0)
            return invitees;
        System.out.println("No need to mention yourself in invitees, you are the organiser");
        String []newinvitees = new String[invitees.length-count];
        int i=0;
        for(String str:invitees){
            if(!str.equals(organiser)) {
                newinvitees[i] = str;
                i++;
            }
        } return newinvitees;


    }






}
