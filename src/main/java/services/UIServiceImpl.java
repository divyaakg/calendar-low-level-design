package services;

import commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UIServiceImpl implements UIService{

    @Autowired
    EventsService evtSvc;
    @Autowired
    UserSessionService sessionService;
    @Autowired
    TokenService tknSvc;

    public void showHelp() {
        List<String> help =new ArrayList<>();
        help.add("********************Hello**************************");
        help.add("Enter \"help\" to see help menu again");
        help.add("Enter \"register <username1,username2>\" to register");
        help.add("Enter \"login username\" to login");
        help.add("Enter \"showCalendar day <yyyy-MM-dd>\" to see day's calendar");
        help.add("Enter \"showCalendar all\" to see the full calendar");
        help.add("You can create an event saying \"createEvent <username> <title> <yyyy-MM-ddTHH:mm:ss> <yyyy-MM-ddTHH:mm:ss> <invitee1,invitee2,..>\"");
        help.add("Example: createEvent lunch 2021-10-19T14:00:00 2021-10-19T14:30:00 tim,john");
        help.add("Enter \"editEvent 1 lunch 2021-10-19T14:30:00 2021-10-19T15:30:00 tim,john");
        help.add("Enter \"logout\" to logout");
        help.add("Enter \"exit\" to exit");
        for(String s:help){
            System.out.println(s);
        }
    }

    @Override
    public void handleUserInput(String s) {
        Command command=createCommand(s);
        command.execute();
    }

    private Command createCommand(String userstr){
        String[] splitted = userstr.split("\\s+");
        //Todo: create command once and let execute accept splitted string
        Command comm=new DoNothing(splitted,evtSvc,sessionService);
        //System.out.println(Arrays.toString(splitted));
        if (splitted[0].equals("showCalendar"))
            comm = new ShowCalendar(splitted,evtSvc,sessionService);
        else if (splitted[0].equals("createEvent"))
            comm = new CreateEvent(splitted, evtSvc, tknSvc,sessionService);
        else if (splitted[0].equals("login"))
            comm = new Login(splitted, evtSvc, sessionService);
        else if (splitted[0].equals("logout"))
            comm = new Logout(splitted, evtSvc, sessionService);
        else if(splitted[0].equals("register"))
            comm= new Register(splitted, evtSvc,sessionService);
        else if(splitted[0].equals("acceptEvent"))
            comm= new AcceptOrDeclineEvent(splitted, evtSvc,sessionService);
        else if(splitted[0].equals("declineEvent"))
            comm= new AcceptOrDeclineEvent(splitted, evtSvc,sessionService);
        return comm;
    }

}
