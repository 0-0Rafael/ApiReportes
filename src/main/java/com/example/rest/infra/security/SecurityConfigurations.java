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

    /*
        tipo de objeto para que implemente una autenticacion stateless
        con objeto/metodo nos referimos a un objeto public que sera el security builter chain

        En está clase le estamos indicando a Spring que nuestra clase con la anotación
        @Configuration será una clase encargada de crear beans a través de sus métodos
        marcados con la anotación @Bean.

 */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.csrf( csrf -> csrf.disable()).sessionManagement( sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests( auth -> auth.requestMatchers(HttpMethod.POST, "/login").permitAll()
                        //Permisos para que todos los usuarios puedan utilizar el metodo Get
                        .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nodos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nodos/zona/{zona}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nodos/fase/{fase}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nodos/nodos/{nodo}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reportes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reportes/categoria/{categoria}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/reportes/fecha/{fecha}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/reportes/fecharango").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/inforepo").permitAll()

                        //Permisos ADMIN nodos
                        .requestMatchers(HttpMethod.POST, "/nodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/nodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/nodos/{id}").hasAnyRole("ADMIN", "USER")

                        //Permisos ADMIN reportes
                        .requestMatchers(HttpMethod.POST, "/reportes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/reportes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/reportes/{id}").hasAnyRole("ADMIN", "USER")

                        //Permisos ADMIN inforepo
                        .requestMatchers(HttpMethod.POST, "/inforepo").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/inforepo").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/inforepo/{id}").hasAnyRole("ADMIN", "USER")


                        .requestMatchers("swagger-ui.html", "v3/api-docs/**", "swagger-ui/**").permitAll()
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


/*
    Se ha creado este metodo de authenticationManager para que en el controlador de los usuarios
    podamos utilizar AutoWired con la clase authenticationManager que permite los procesos de autenticacion en el controller

    Usamos Bean para que este disponible en el contexto de Spring como anotacion y
    retornamos un metodo de la clase AuthenticationConfiguration
    para obtener el authentication manager en el controller
     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

        /*
    este metodo de passwordencoder es el que retorna un encriptador, en este caso bcrypt. esto lo hacemos aqui porque asi con la anotacion BEAN
    spring lo puede utilizar. Lo usamos por ejemplo en la entidad de usuarios
     */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /*
    para permitir el modelado de lista a entidades y asi poder guardar listas de datos.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}