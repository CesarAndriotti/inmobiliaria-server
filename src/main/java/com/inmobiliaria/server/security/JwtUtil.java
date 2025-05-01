package com.inmobiliaria.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {

    //JWTs es una clase estatica de io.jsonwebtoken

    //Preparación de la clave para la firma
    private String secretKey = "ElGordoMotoneta234%#1212NoPuedeParar";
    private Key key = new SecretKeySpec(secretKey.getBytes(), "HMACSHA256");

    //Generador de token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) //Usuario
                .setIssuedAt(new Date()) //Fecha y hora
                .setExpiration(new Date(System.currentTimeMillis() + 
                    Duration.ofMinutes(10).toMillis())
                ) 
                .signWith(key) //Firma
                .compact(); //Devuelve el token como un String
    }

    //Extrae el nombre de usuario
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    //Verifica si el token es válido
    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    //Verifica si el token expiró
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //Extrae toda la información (claims) del token. Claims Es una interfaz de la librería io.jsonwebtoken
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) //Se usa la misma clave para verificar la firma
                .build()
                .parseClaimsJws(token) //Parsea el JWT y lo valida
                .getBody(); //Devuelve el contenido (claims) del token
    }
}