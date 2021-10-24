package commands;

import exceptions.NotSignedInException;
import services.UIService;

public interface Command {
    void execute();

}
