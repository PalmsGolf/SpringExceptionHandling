package com.javaclub.springexceptionhandlingdemo.configuration;

import com.javaclub.springexceptionhandlingdemo.filters.CustomAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;

public class FiltersConfiguration {

    @Bean
    protected FilterRegistrationBean<CustomAuthenticationFilter> customAuthenticationFilterBean(final CustomAuthenticationFilter customAuthenticationFilter) {
        final FilterRegistrationBean<CustomAuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>(customAuthenticationFilter);
        filterRegistrationBean.setOrder(SecurityWebFiltersOrder.FIRST.getOrder());

        return filterRegistrationBean;
    }
}
