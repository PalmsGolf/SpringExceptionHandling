package com.javaclub.springexceptionhandlingdemo.configuration;

import com.javaclub.springexceptionhandlingdemo.filters.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    /*private final CustomAuthenticationFilter authEntryPoint;

    public SecurityConfiguration(CustomAuthenticationFilter authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }
*/
    @Bean
    protected SecurityFilterChain filterChain(final HttpSecurity http, final CustomAuthenticationFilter customAuthenticationFilter) throws Exception {
        http.exceptionHandling(Customizer.withDefaults());
        http.authorizeHttpRequests((authorize) -> authorize
                .anyRequest().permitAll());

        //.addFilterBefore(customAuthenticationFilter, AnonymousAuthenticationFilter.class);
        return http.build();
    }
}
