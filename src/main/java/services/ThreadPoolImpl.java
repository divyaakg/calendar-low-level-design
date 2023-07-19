package services;

import commands.Command;
import org.springframework.stereotype.Component;
import services.ThreadPoolCommand;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadPoolImpl implements ThreadPoolCommand {
    ExecutorService svc;
    public ThreadPoolImpl(){
        System.out.println("Creating a pool of threads");
        svc=Executors.newFixedThreadPool(10);
    }
    @Override
    public void submitWork(Command command) {
        svc.submit(new CreateEventProcessor(command));
    }

    class CreateEventProcessor implements Runnable{
        Command command;
        public CreateEventProcessor(Command c){
            command=c;
        }
        @Override
        public void run() {
            command.execute();
        }
    }
}
