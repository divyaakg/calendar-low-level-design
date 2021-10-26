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
    String COMMAND_NAME="createEvent";

    public CreateEvent(String[] inp, EventsService evsvc, TokenService tknsvc, UserSessionService sessionService) {
        //help.add("Example: createEvent lunch 2021-10-19T14:00:00 2019-01-04T14:30:00 tim,john\n");
        super(inp, evsvc,sessionService);
        tokenService=tknsvc;
    }

    public void execute() {
        if(usercomm.length<5) {
            System.out.println("Invalid createEvent command");
            return;
        }
        if(usercomm.length==6 && usercomm[5].equals("dontcare")){
            createEvent();
        } else {
            String []invitees = usercomm[4].split(",");
            if (checkConflicts(invitees, LocalDateTime.parse(usercomm[2]), LocalDateTime.parse(usercomm[3]))) {
                System.out.println("If you want to proceed anyway, type the same " +
                        "createEvent command with \"dontcare\" (without quotes) at the end");
            } else{
                createEvent();
            }
        }

    }

    private void createEvent(){
        try{
            String organiser=sessionService.getSession();
            EventImpl event=new EventImpl(tokenService);
            event.setTitle(usercomm[1]);
            event.setStart(LocalDateTime.parse(usercomm[2]));
            event.setEnd(LocalDateTime.parse(usercomm[3]));
            event.setOrganiser(organiser);
            String []invitees = usercomm[4].split(",");
            event.addInvitees(invitees);
            evtsvc.addEvent(organiser, event);
            for(String name:invitees){
                evtsvc.addEvent(name, event);
            }
        } catch (NotSignedInException e) {
            System.out.println("Sign in first to execute createEvent command");
        }
    }
    private boolean checkConflicts(String[] invitees, LocalDateTime st,LocalDateTime end){
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





}
