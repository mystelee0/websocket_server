package com.example.websocket_server.configuration;

import com.example.websocket_server.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager를 직접 빈으로 등록해서 컨트롤러에서 주입 가능하게 함
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    // 시큐리티 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // React 연동 시 csrf 토큰 없이 테스트하려면 disable (실서비스시 재설정 필요)
                .cors()           // Cors 설정 활성화
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**","/auth/login", "/h2-console/**").permitAll() // 인증 없이 접근 허용 경로
                        .requestMatchers("/auth/me").authenticated()
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .headers().frameOptions().disable(); // h2-console 화면을 위한 설정

        return http.build();
    }
}
