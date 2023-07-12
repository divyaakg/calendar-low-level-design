

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.*;
import commands.Command;

import java.util.Scanner;

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
        ApplicationContext context=new AnnotationConfigApplicationContext(MainConfig.class);
        UIService uiService=context.getBean(UIService.class);
        uiService.showHelp();
        Scanner sc=new Scanner(System.in);
        String usercomm=sc.nextLine();
        while (usercomm.compareTo("exit") != 0) {
            uiService.handleUserInput(usercomm);
            usercomm=sc.nextLine();
        }
    }
}
