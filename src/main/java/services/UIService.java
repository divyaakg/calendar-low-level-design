package main.java.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class UIService {

    public static void showHelp() {
        List<String> help =new ArrayList<>();
        help.add("********************Hello**************************");
        help.add("Enter \"help\" to see help menu again");
        help.add("Enter \"login username\" to login");
        help.add("Enter \"showCalendar day <yyyy-MM-dd>\" to see day's calendar");
        help.add("Enter \"showCalendar all\" to see the full calendar");
        help.add("You can create an event saying \"createEvent <username> <title> <yyyy-MM-ddTHH:mm:ss> <yyyy-MM-ddTHH:mm:ss> <invitee1,invitee2,..>\"");
        help.add("Example: createEvent lunch 2021-10-19T14:00:00 2019-01-04T14:30:00 tim,john");
        help.add("Enter \"editEvent 1 lunch 2021-10-19T14:30:00 2019-01-04T15:30:00 tim,john");
        help.add("Enter \"logout\" to logout");
        help.add("Enter \"exit\" to exit");
        for(String s:help){
            System.out.println(s);
        }
    }
}
