package main.java.commands;

import main.java.exceptions.NotSignedInException;
import main.java.services.EventsService;
import main.java.services.UserSessionService;

public class AcceptOrDeclineEvent extends BaseCommand {
    public AcceptOrDeclineEvent(String[] splitted, EventsService evtSvc, UserSessionService sessionService) {
        super(splitted,evtSvc,sessionService);
    }

    public void execute() {
        try {
            String name = sessionService.getSession();
            if(usercomm[0].equals("acceptEvent"))
                evtsvc.userHasAccepted(name,Integer.parseInt(usercomm[1]));
            else
                evtsvc.userHasDeclined(name,Integer.parseInt(usercomm[1]));
        } catch (NotSignedInException e){
            System.out.println("Sign in first to execute acceptEvent command");
        }
    }
}
