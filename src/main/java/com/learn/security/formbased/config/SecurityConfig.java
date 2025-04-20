package com.learn.security.formbased.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder customPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(http->

                http.requestMatchers("/api/register").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        httpSecurity.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return httpSecurity.build();
    }
//    @Bean
//    public UserDetailsService customUserDetailsService()
//    {
//        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority("USER");
//        SimpleGrantedAuthority adminRole = new SimpleGrantedAuthority("ADMIN");
//        UserDetails userDetails = new User("user1",
//                customPasswordEncoder().encode("pass1")
//                , List.of(userRole));
//        UserDetails adminDetails = new User("admin1",
//                customPasswordEncoder().encode("pass1")
//                ,List.of(adminRole));
//        return new InMemoryUserDetailsManager(userDetails,adminDetails);
//    }
}
