package org.msh.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class MyJwtUtil {

    @Value("${my.jwt.secret.key}")
    private String myJwtSecretKey;

    @Value("${my.jwt.expiration.ms}")
    private Long myJwtExpiry;

    //private java.security.Key keySecurity;
    private javax.crypto.SecretKey keySecret;

    //region initiate jwt
    @PostConstruct
    private void init()
    {
        //this code line is needed to initiate jwt
        this.keySecret = io.jsonwebtoken.security.Keys.hmacShaKeyFor(myJwtSecretKey.getBytes(StandardCharsets.UTF_8));
        //
         //Note:
         //for a shorter secret key definition use below lines:
        //String strSecret = Encoders.BASE64.encode(myJwtSecretKey.getBytes(StandardCharsets.UTF_8));
        //this.keySecret = io.jsonwebtoken.security.Keys.hmacShaKeyFor(strSecret.getBytes(StandardCharsets.UTF_8));
    }
    //endregion




    public String generateToken(String username)
    {
        Date now =  new Date();
        Date exp = new Date(now.getTime()+myJwtExpiry);
        //
        return Jwts
                .builder()
                .subject(username)
//                .id(username)
                .expiration(exp)
                .issuedAt(now)
                .signWith(keySecret)
                .compact();
    }


    public String getUsernameFromJwtToken(String token)
    {
        Claims claims= Jwts
                .parser()
                .verifyWith(keySecret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }


    public Boolean validateJwtToken(String token)
    {
        try {
            Jwts
                .parser()
                .verifyWith(keySecret)
                .build()
                .parseSignedClaims(token);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
