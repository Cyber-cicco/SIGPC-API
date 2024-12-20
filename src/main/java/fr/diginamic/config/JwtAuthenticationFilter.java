package fr.diginamic.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    /**
     * Filtre permettant de créer une session si le cookie contenant le JWT est valide
     * TODO : changer si l'on veut gérer l'authentification via le header
     * @param req la requête
     * @param res la réponse
     * @param chain la chaine de middleware
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        if (req.getCookies() != null && Arrays.stream(req.getCookies()).anyMatch(cookie -> cookie.getName().equals(jwtService.getCookie()))) {
            Stream.of(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(jwtService.getCookie()) && !cookie.getValue().isBlank())
                    .map(Cookie::getValue)
                    .forEach(token -> {
                        try {
                            Claims body = Jwts
                                    .parserBuilder()
                                    .setSigningKey(jwtService.getSecretKey())
                                    .build()
                                    .parseClaimsJws(token)
                                    .getBody();
                            String username = body.getSubject();
                            Long credentials = body.get("id", Long.class);
                            String serializedRoles = body.get("role", String.class);
                            ObjectMapper objectMapper = new ObjectMapper();
                            List<String> roles;
                            roles = objectMapper.readValue(serializedRoles, List.class);
                            Authentication authentication = new UsernamePasswordAuthenticationToken(username, credentials,
                                    roles.stream()
                                            .map(SimpleGrantedAuthority::new)
                                            .toList()
                            );
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } catch (JsonProcessingException | JwtException e) {
                            res.addCookie(new Cookie("AUTH-TOKEN", null));
                        }
                    });
        }
        chain.doFilter(req, res);
    }
}
