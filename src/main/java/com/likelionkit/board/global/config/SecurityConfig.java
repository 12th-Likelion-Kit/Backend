package com.likelionkit.board.global.config;

import com.likelionkit.board.global.jwt.filter.JwtAuthenticationFilter;
import com.likelionkit.board.global.jwt.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers("/api/users/sign-up","/api/users/login").permitAll()
                        .requestMatchers("/api/**").authenticated())
                .sessionManagement((s) -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
