package main.java.commands;

import main.java.services.EventsService;
import main.java.services.UIService;
import main.java.services.UserSessionService;

public class DoNothing extends BaseCommand {
    //String[] userstr;

    @Override
    public void execute() {
        System.out.println("Don't recognise this");
        //UIService.showHelp();
    }

        public DoNothing(String []userinp, EventsService evtsvc, UserSessionService usvc){
        //this.userstr = userinp;
        super(userinp,evtsvc,usvc);
    }
}
