package main.java.commands;

import main.java.services.EventsService;
import main.java.services.UserSessionService;

public class Register  extends BaseCommand{
    public Register(String[] splitted, EventsService evtSvc, UserSessionService sessionService) {
        super(splitted,evtSvc,sessionService);
    }

    public void execute() {
        String[] names=usercomm[1].split(",");
        for(String name:names){
            evtsvc.addUser(name);
        }
    }

}
