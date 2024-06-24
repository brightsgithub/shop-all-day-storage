package com.shopallday.storage.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Enabling Cross Origin Requests for a RESTful Web Service
 *
 * To allow your Spring Boot application to accept requests from your React app, you need to address the
 * CORS (Cross-Origin Resource Sharing) issue. CORS is a security feature implemented by web browsers that restricts
 * HTTP requests that are initiated from scripts running in the browser to resources in a different origin.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // using * However, be cautious with this approach as it can potentially open up your server to security
                // vulnerabilities if not configured properly.
                //.allowedOrigins("*") // Replace this with your React app URL
                .allowedOrigins("http://localhost", "http://localhost:5173") // React app URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
