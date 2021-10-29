package main.java;

import main.java.commands.Command;
import main.java.services.*;

import java.util.Scanner;
import java.util.logging.Logger;
public class Main {
    /*
    todo: 1. multithreading
          2. design patterns
          3. create session
          4. filemode
          5. exceptions
          6. conflicts/accept and decline.
          7.junit
     */
    public static void main(String[] args){
        UIService.showHelp();
        EventsService eventsService=new EventsService();
        CommandFactory commandFactory=new CommandFactory(eventsService, new TokenService(), new UserSessionService());
        Scanner sc=new Scanner(System.in);
        String usercomm=sc.nextLine();
        while (usercomm.compareTo("exit") != 0) {
            Command cmd = commandFactory.createCommand(usercomm);
            cmd.execute();
            usercomm=sc.nextLine();
        }
    }
}
