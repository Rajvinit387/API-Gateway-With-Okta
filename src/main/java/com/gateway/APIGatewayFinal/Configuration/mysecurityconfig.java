package com.gateway.APIGatewayFinal.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class mysecurityconfig  {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity)
    {
        serverHttpSecurity
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                ).oauth2Client(Customizer.withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                ;
               return serverHttpSecurity.build();

// serverHttpSecurity.
//         authorizeExchange().anyExchange().
//         authenticated().and().oauth2Client().and().oauth2ResourceServer().jwt();




    }

}
