package main.java.commands;

import main.java.exceptions.NotSignedInException;
import main.java.services.EventsService;
import main.java.services.UserSessionService;

import java.time.LocalDate;

public class Logout extends BaseCommand {
    public void execute() {
        sessionService.deleteSession();
    }

    public Logout(String[] inp, EventsService evsvc, UserSessionService sess){
        super(inp,evsvc,sess);

    }

}
