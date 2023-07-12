package services;

import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService{
    int lasttoken;

    public int getToken() {
        lasttoken++;
        return lasttoken;
    }

    public TokenServiceImpl(){
        lasttoken=0;
    }
}
