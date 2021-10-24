package commands;

import exceptions.NotSignedInException;
import services.EventsService;
import services.UserSessionService;

import java.time.LocalDate;

public class Logout extends BaseCommand {
    public void execute() {
        sessionService.deleteSession();
    }

    public Logout(String[] inp, EventsService evsvc, UserSessionService sess){
        super(inp,evsvc,sess);

    }

}
