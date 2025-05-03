package org.example.mychat.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.List;

@Configuration
@EnableWebSocket
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    private final List<String> OPEN_API = List.of(
            "/**",
            "/home.html",
            "/login",
            "/login.html",
            "/register",
            "/register.html",
            "/home.html"
    );


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(auth -> {auth
                        .requestMatchers(OPEN_API.toArray(new String[0]))
                        .permitAll()
                        .anyRequest()
                        .authenticated();
                })

                .formLogin(loginForm -> loginForm
                        .defaultSuccessUrl("/index.html",true)
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }
}
