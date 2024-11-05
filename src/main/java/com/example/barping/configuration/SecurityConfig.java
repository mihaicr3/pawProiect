package com.example.barping.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Define the PasswordEncoder as a Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure SecurityFilterChain instead of extending WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/manager/**").hasRole("MANAGER")
                                .requestMatchers("/bartender/**").hasRole("BARTENDER")
                                .requestMatchers("/client/**").hasRole("CLIENT")
                                .requestMatchers("/login", "/register").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/home", true)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.maximumSessions(1) // Limit sessions per user
                );

        return http.build();
    }
}
