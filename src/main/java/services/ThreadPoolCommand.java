package services;

import commands.Command;

public interface ThreadPoolCommand {
    public void submitWork(Command c);
}
