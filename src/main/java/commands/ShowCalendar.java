package main.java.commands;

import main.java.exceptions.NotSignedInException;
import main.java.services.EventsService;
import main.java.services.UserSessionService;

import java.time.LocalDate;

public class ShowCalendar extends BaseCommand {
    //help.add("Enter \"showCalendar day <ddmmyy>\" to see day's calendar\n");
    //LocalDate dt;//ddmmyy
    String COMMAND_NAME="showCalendar";
    public void execute() {
        if(!isValid()){
            System.out.println("Invalid showCalendar command");
            return;
        } try {
            if(usercomm[1].equals("day")) {
                LocalDate dt = LocalDate.parse(usercomm[2]);
                evtsvc.showDay(sessionService.getSession(), dt);
            } else if(usercomm[1].equals("all")){
                evtsvc.showAll(sessionService.getSession());
            }
        } catch(NotSignedInException e){
            System.out.println("Sign in first to execute showCalendar command");
        }
    }

    boolean isValid(){
        if(usercomm.length<2)
            return false;
        if(usercomm[1].equals("day") && usercomm.length<3)
            return false;
        return true;
    }
    public ShowCalendar(String[] inp, EventsService evsvc, UserSessionService sess){
        super(inp,evsvc,sess);

    }
}
