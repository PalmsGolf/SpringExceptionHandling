package com.javaclub.springexceptionhandlingdemo.filters;

import com.javaclub.springexceptionhandlingdemo.exceptions.CustomAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;

    public CustomAuthenticationFilter(@Qualifier("handlerExceptionResolver") final HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws ServletException, IOException {
        // throwExceptionWithResolver(httpServletRequest, httpServletResponse);
        // throwJustException();
         filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void noException(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void throwJustException() {
        throw new CustomAuthenticationException("No no no");
    }

    private void throwExceptionWithResolver(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        try {
            throw new CustomAuthenticationException("No no no");
        } catch (final CustomAuthenticationException e) {
            this.handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, e);
        }
    }
}