package services;

public class TokenService {
    int lasttoken;

    public int getToken() {
        lasttoken++;
        return lasttoken;
    }

    public TokenService(){
        lasttoken=0;
    }
}
