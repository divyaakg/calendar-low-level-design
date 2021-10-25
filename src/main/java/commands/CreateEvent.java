package main.java.commands;


import main.java.exceptions.NotSignedInException;
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



}
