package ro.starstreet.lesson_9_rest.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.List;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            if (Objects.nonNull(authorizationHeaderValue) && authorizationHeaderValue.startsWith("Bearer ")) {
                String token = authorizationHeaderValue.substring(7);
                String username = jwtService.getUsername(token);
                System.out.println(username);
                List<? extends GrantedAuthority> authorityList = jwtService.getAuthorities(token);

                if (Objects.nonNull(username)) {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(username, null, authorityList)
                    );
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
