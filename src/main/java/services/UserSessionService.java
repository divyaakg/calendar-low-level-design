package services;


import exceptions.NotSignedInException;
import org.springframework.stereotype.Service;

@Service
public interface UserSessionService {

    public String getSession() throws NotSignedInException;


    public void deleteSession();

    public void setSession(String s);


}
