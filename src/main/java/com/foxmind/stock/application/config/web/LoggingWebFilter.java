package com.foxmind.stock.application.config.web;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class LoggingWebFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingWebFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long startTime = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();

        logger.info("➡️ [{}] {}", request.getMethod(), request.getURI());

        return chain.filter(exchange)
            .doOnSuccess((done) -> {
                long duration = System.currentTimeMillis() - startTime;
                logger.info("⬅️ [{}] {} completed in {}ms", request.getMethod(), request.getURI(), duration);
            })
            .doOnError(error -> {
                long duration = System.currentTimeMillis() - startTime;

            });
    }
}