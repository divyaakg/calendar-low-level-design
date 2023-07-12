package services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service



public interface UIService {
    //public Command createCommand(String raw);
    public void showHelp();

    void handleUserInput(String s);
}