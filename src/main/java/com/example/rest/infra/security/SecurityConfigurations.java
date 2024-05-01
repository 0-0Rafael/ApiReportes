package com.example.rest.infra.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Permisos para las solicitudes de autenticación y registro
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                        // Otros permisos de acceso público
                        .requestMatchers(HttpMethod.GET, "/nodos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nodos/zona/{zona}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nodos/fase/{fase}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nodos/nodos/{nodo}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reportes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reportes/categoria/{categoria}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/reportes/fecha/{fecha}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/reportes/fecharango").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/inforepo").permitAll()
                        // Permisos para la gestión de nodos (ADMIN o USER)
                        .requestMatchers(HttpMethod.POST, "/nodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/nodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/nodos/{id}").hasAnyRole("ADMIN", "USER")
                        // Permisos para la gestión de reportes (ADMIN o USER)
                        .requestMatchers(HttpMethod.POST, "/reportes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/reportes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/reportes/{id}").hasAnyRole("ADMIN", "USER")
                        // Permisos para la gestión de inforepo (ADMIN o USER)
                        .requestMatchers(HttpMethod.POST, "/inforepo").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/inforepo").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/inforepo/{id}").hasAnyRole("ADMIN", "USER")
                        // Permisos para Swagger UI y documentación de la API
                        .requestMatchers(HttpMethod.GET, "swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        // Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
