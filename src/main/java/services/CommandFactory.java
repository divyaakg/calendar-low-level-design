package main.java.services;

import main.java.commands.*;

import java.util.Arrays;

public class CommandFactory {
    EventsService evtSvc;
    TokenService tknSvc;
    UserSessionService sessionService;

    public Command createCommand(String userstr){
        String[] splitted = userstr.split("\\s+");
        //Todo: create command once and let execute accept splitted string
        Command comm=new DoNothing(splitted,evtSvc,sessionService);
        //System.out.println(Arrays.toString(splitted));
        if (splitted[0].equals("showCalendar"))
            comm = new ShowCalendar(splitted,evtSvc,sessionService);
        else if (splitted[0].equals("createEvent"))
            comm = new CreateEvent(splitted, evtSvc, tknSvc,sessionService);
        else if (splitted[0].equals("login"))
            comm = new Login(splitted, evtSvc, sessionService);
        else if (splitted[0].equals("logout"))
            comm = new Logout(splitted, evtSvc, sessionService);
        return comm;
    }

    public CommandFactory(EventsService eventsService, TokenService tokenService,UserSessionService userSessionService) {
        evtSvc=eventsService;
        tknSvc=tokenService;
        sessionService=userSessionService;
    }

}
