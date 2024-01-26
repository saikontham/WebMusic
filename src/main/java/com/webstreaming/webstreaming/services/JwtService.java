//package com.webstreaming.webstreaming.services;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtService {
//    public final static String SECRET="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
//    // Method to generate a JWT token for a given username
//
//    public String generateToken(String username)
//    {
//        Map<String,Object> claims=new HashMap<>();
//
//        return createToken(claims,username);
//    }
//    // Private method to create a JWT token with claims, subject, issued at, expiration, and signing key
//
//    private String createToken(Map<String, Object> claims, String username) {
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//    }
//    // Private method to get the signing key from the secret
//
//    private Key getSignKey() {
//
//        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
////    public String getUsername(String token)
////    {
////        Claims claims=Jwts.parser().setSigningKey(SECRET)
////                .parseClaimsJws(token).getBody();
////        return claims.getSubject();
////    }
//    // Public method to extract the username from a JWT token
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//    // Public method to extract the expiration date from a JWT token
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//    // Public method to extract a specific claim from a JWT token using a claims resolver function
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    // Private method to extract all claims from a JWT token
//
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    // Private method to check if a JWT token is expired
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//    // Public method to validate a JWT token against a UserDetails object
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//}
package com.webstreaming.webstreaming.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    public final static String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    // Method to generate a JWT token for a given username (changed to email)
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    // Private method to create a JWT token with claims, subject, issued at, expiration, and signing key
    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    // Private method to get the signing key from the secret
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Public method to extract the email from a JWT token (changed from getUsername)
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Public method to extract the expiration date from a JWT token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Public method to extract a specific claim from a JWT token using a claims resolver function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Private method to extract all claims from a JWT token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Private method to check if a JWT token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Public method to validate a JWT token against a UserDetails object
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

