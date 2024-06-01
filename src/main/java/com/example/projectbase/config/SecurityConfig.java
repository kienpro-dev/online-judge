package com.example.projectbase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/cart/**","/orders/**","/wishlist/**")
                                .authenticated()
                                .anyRequest().permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/ui/v1/auth/login")
                                .loginProcessingUrl("/ui/v1/auth/login")
                                .defaultSuccessUrl("/ui/v1")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/ui/v1/auth/logout"))
                                .permitAll()
                                .logoutSuccessUrl("/ui/v1")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")

                )
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    if (request.isUserInRole("USER")) {
                                        response.sendRedirect("/not_found");
                                    } else {
                                        response.sendRedirect("/not_found");
                                    }
                                })
                )
        ;
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}