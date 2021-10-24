package services;


import exceptions.NotSignedInException;

public class UserSessionService {
    //Map<String,UserSession> sessionMap;
    String username;


    public String getSession() throws NotSignedInException{
        if(username.equals("")){
            throw new NotSignedInException();
        }
        return username;
//        if(sessionMap.containsKey(username))
//            return sessionMap.get(username);
//        else{ UserSession session=new UserSession(username);
//            sessionMap.put(username,session);
//            return session;
//        }
    }

    public void deleteSession(){
        //sessionMap.remove(username);
        username="";
    }

    public void setSession(String s) {
        username=s;
    }

    public UserSessionService(){
        username=new String("");
    }
}
