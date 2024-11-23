package com.rushi.apigateway.configs;


import com.rushi.apigateway.helpers.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {

    @Autowired
    RoutingRequestValidator routingRequestValidator;

    @Autowired
    JwtUtils jwtUtils;

    public JwtFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if(routingRequestValidator.isAuthenticated.test(exchange.getRequest())){

                if(!exchange.getRequest().getHeaders().containsKey((HttpHeaders.AUTHORIZATION))){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is missing");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if(authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }

                try {
                    jwtUtils.validateToken(authHeader);
                }catch (Exception ex){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or Expired JWT Token");
                }

            }
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }

}
