package com.example.websocket_server.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Value("${clientIp}")
    private String clientIp;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Configured clientIp for CORS: {}", clientIp);
        registry.addMapping("/**")
                .allowedOriginPatterns(clientIp) // 명시적인 origin만 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // preflight 포함
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }
}

