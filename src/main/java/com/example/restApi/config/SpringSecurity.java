package com.example.restApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.restApi.filter.JwtFilter;
import com.example.restApi.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {    //it provides a way to configure how requests are secured. By ensuring how request matching should be done.
        
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/journal/**", "/user/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)   //if it is enabled then spring expects to recieve csrf token which we are not sending that's why it is disabled.
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //first run jwtFilter then run UsernamePasswordAuthenticationFilter(basic authentication of spring).
                .build();
    }

    //integrating everything
    //As soon as we give username and password in postman it will check whether we can bring user from userDetailService or not.
    //After bringing it , we will start password matching
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception{
        return auth.getAuthenticationManager();
    }
}

/*
http.authorizeRequests(): This tells Spring Security to start authorizing the requests.

.antMatchers("/hello").permitAll(): This part specifies that HTTP requests matching the path `/hello` should be permitted (allowed) for all users, whether they are authenticated or not.

.anyRequest().authenticated(): This is a more general matcher that specifies any request (not already matched by previous matchers) should be authenticated, meaning users have to provide valid credentials to access these endpoints.

.and(): This is a method to join several configurations. It helps to continue the configuration from the root (HttpSecurity).

.formLogin(): This enables form-based authentication. By default, it will provide a form for the user to enter their username and password. If the user is not authenticated and they try to access a secured endpoint, they'll be redirected to the default login form.
*/
