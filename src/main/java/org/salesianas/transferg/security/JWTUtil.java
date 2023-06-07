package org.salesianas.transferg.security;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    
    /**
     * Genera el token
     * @param email
     * @return
     * @throws IllegalArgumentException
     * @throws JWTCreationException
     */
    public String generateToken( String rol, Long id, String email) throws IllegalArgumentException, JWTCreationException {
    	
        Calendar calendar = Calendar.getInstance();

              calendar.setTime(new Date());  
              calendar.add(Calendar.MILLISECOND, 120000000);  
              Date date= calendar.getTime();
    	
        return JWT.create()
                .withSubject("User Details")
                .withExpiresAt(date)   //Para darle caducidad
                .withClaim("id", id)
                .withClaim("rol", rol)
                .withClaim("email",email)
                .withIssuedAt(new Date())
                .withIssuer("LaNube")
                .sign(Algorithm.HMAC256(secret));
    }


    
    
    /**
     * Valida el token
     * @param token
     * @return
     * @throws JWTVerificationException
     */
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("LaNube")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}