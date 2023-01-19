package ro.starstreet.lesson_9_rest.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtService {
private final long LIFE_TIME = 1000*60*5L;
private final String SECRET = "jkakjarg;nvneIr;kqenqen";
    public String generateToken(UserDetails user) {
        String username = user.getUsername();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        Map<String, Object> claims = new HashMap<>(Map.of("authorities", authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + LIFE_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getUsername(String token) {
        return parse(token).getSubject();
    }

    public List<? extends GrantedAuthority> getAuthorities(String token) {
        List<String> authorities = (List<String>) parse(token).get("authorities");
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    private Claims parse(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
