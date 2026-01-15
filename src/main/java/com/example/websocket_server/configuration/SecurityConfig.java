package com.example.websocket_server.configuration;

import com.example.websocket_server.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    public SecurityConfig(CustomUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // DaoAuthenticationProvider를 빈으로 등록하여 userDetailsService와 passwordEncoder를 연결
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // AuthenticationManager를 AuthenticationConfiguration에서 가져오도록 변경
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 시큐리티 필터 체인 설정 (Spring Security 6.1 권장 스타일)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // React 연동 시 csrf 토큰 없이 테스트하려면 disable (실서비스시 재설정 필요)
                // CorsConfigurationSource를 명시적으로 사용하여 CORS 필터가 시큐리티 체인에 적용되도록 함
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                // DaoAuthenticationProvider 등록
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        // preflight OPTIONS 허용
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 인증 없이 허용할 엔드포인트만 명시적으로 허용
                        .requestMatchers("/auth/login", "/users", "/auth/me", "/h2-console/**").permitAll()
                        // 나머지는 인증 필요
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())); // h2-console 화면을 위한 설정

        return http.build();
    }
}
