package main.java.model;

import java.time.LocalDateTime;
import java.util.*;

public interface Event extends Comparable<Event>{
    LocalDateTime getStart();

    void setTitle(String s);
    void setEnd(LocalDateTime end);
    void setOrganiser(String creator);

    Integer getId();

    LocalDateTime getEnd();
    EventInvitees getInvitees();

    String getOrganiser();

//    void setParticipants(List<User> invitees);
}
