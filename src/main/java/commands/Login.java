package commands;

import services.EventsService;
import services.UserSessionService;
import services.UserSessionService;

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

        if(!evtsvc.userExists(usercomm[1])) {
            System.out.println("User "+usercomm[1]+" was not registered, Registering him");
            evtsvc.addUser(usercomm[1]); //registering also
        }
        sessionService.setSession(usercomm[1]);
        System.out.println("Logged in "+usercomm[1]);
    }


}
