package main.java.commands;

import main.java.services.EventsService;
import main.java.services.UserSessionService;
import main.java.services.UserSessionService;

public class Login extends BaseCommand {

    public Login(String[] inp, EventsService evsvc, UserSessionService sessSvc) {
        super(inp, evsvc,sessSvc);

    }

    @Override
    public void execute() {
        if(usercomm.length<2) {
            System.out.println("Invalid login command");
            return;
        }
        sessionService.setSession(usercomm[1]);
        evtsvc.addUser(usercomm[1]);
    }


}
