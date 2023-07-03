package commands;

import services.EventsService;
import services.UserSessionService;

public abstract class BaseCommand implements Command {
    protected String [] usercomm;
    EventsService evtsvc;
    UserSessionService sessionService;
    BaseCommand(String[] inp, EventsService evsvc, UserSessionService sess)
    {
        usercomm = inp;
        evtsvc=evsvc;
        sessionService=sess;
    }
}
