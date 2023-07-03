package commands;

import services.*;


public class Register  extends BaseCommand{
    public Register(String[] splitted, EventsService evtSvc, UserSessionService sessionService) {
        super(splitted,evtSvc,sessionService);
    }

    public void execute() {
        String[] names=usercomm[1].split(",");
        for(String name:names){
            evtsvc.addUser(name);
            System.out.println("Registered "+name);
        }
    }

}
