package fr.diginamic.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.beans.Customizer;
import java.util.List;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    /**
     * Permet de gérer la suppression du cookie lorsque l'utilisateur se déconnecte
     */
    private final CustomLogoutHandler customLogoutHandler;

    /**
     * Bean permettant de hasher le mot de passe dans les services d'authentification
     * @return le bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure la sécurité pour l'application
     *
     * @param http outil permettant de configurer les middlewares
     * @param jwtAuthenticationFilter middleware custom permettant de créer une session à partir du cookie jwt
     * @param mvc utilitaire permettant de gérer le matching des routes
     * @param configurationSource bean permettant de gérer les CORS
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter, MvcRequestMatcher.Builder mvc, @Qualifier(value = "corsConfigurationSource") CorsConfigurationSource configurationSource) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvc.pattern("api/v1/auth/compte")).permitAll()
                        .requestMatchers(mvc.pattern("api/v1/auth/login")).permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable
                )
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                        .configurationSource(configurationSource)
                )
                .headers(
                        headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .logout(logoutConfigurer ->
                        logoutConfigurer.addLogoutHandler(customLogoutHandler)
                                .logoutUrl("/logout")
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Configure  les CORS pour accepter les requêtes depuis localhost
     * TODO: Changer cela si l'on souhaite ajouter un front
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost", "//localhost"));
        configuration.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-XSRF-TOKEN"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Permet de gérer le matching des routes et de les sécuriser
     * @param introspector object contenant des informations sur les routes de l'application
     * @return
     */
    @Scope("prototype")
    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
        return new MvcRequestMatcher.Builder(introspector);
    }


}
