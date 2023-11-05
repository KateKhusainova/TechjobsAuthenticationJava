package org.launchcode.techjobsauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//TODO: 3.3. Register the filter with Spring.
// a) Create a class called WebApplicationConfig at the same level
//    as AuthenticationFilter with the following:

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    // Create spring-managed object to allow the app to access our filter
        @Bean
        public AuthenticationFilter authenticationFilter() {
            return new AuthenticationFilter();
        }

        // Register the filter with the Spring container
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor( authenticationFilter() );
        }

}
