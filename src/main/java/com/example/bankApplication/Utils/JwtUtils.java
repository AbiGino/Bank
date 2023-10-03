package com.example.bankApplication.Utils;

import com.example.bankApplication.Common.JwtAccessDeniedException;
import com.example.bankApplication.Entity.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtils {

    final private static String secret = "This_is_secret";
    final private static long expiryDuration = 60 * 60 * 24 * 30;

    public String generateJwt(Customer customer) {
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);
// claims(payload)
        Claims claims = Jwts.claims()
                .setIssuer(customer.getAccount_number().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

// optional claims
        claims.put("name", customer.getName());
        claims.put("phone_number", customer.getPhone_number());


// generate jwt using claims
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Claims verify(String authorization) throws Exception {
//        System.out.println("authorization: "+ authorization);
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
        } catch (Exception e) {
            throw new JwtAccessDeniedException("Access Denied");
        }
        return claims;
    }
}
