package at.aschowurscht.dev.saadi.erp.backend.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static at.aschowurscht.dev.saadi.erp.backend.security.Role.ADMIN;
import static at.aschowurscht.dev.saadi.erp.backend.security.Role.PUB;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/api/token").permitAll()
                        .antMatchers("/api/credentials").permitAll()
                        .antMatchers(GET, "/api/products").hasAnyAuthority(ADMIN.getRole(), PUB.getRole())
                        .antMatchers(GET, "/api/products/**").hasAnyAuthority(ADMIN.getRole(), PUB.getRole())
                        .antMatchers("/api/products/**").hasAuthority(ADMIN.getRole())
                        .antMatchers(PUT, "/api/demands/**").hasAnyAuthority(ADMIN.getRole(), PUB.getRole())
                        .antMatchers(POST, "/api/demands/**").hasAnyAuthority(ADMIN.getRole(), PUB.getRole())
                        .antMatchers(GET, "/api/demands/**").hasAuthority(ADMIN.getRole())
                        .antMatchers("/api/vendors/**").hasAuthority(ADMIN.getRole())
                        .antMatchers("/api/pubs/**").hasAuthority(ADMIN.getRole())
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .build();

    }
}