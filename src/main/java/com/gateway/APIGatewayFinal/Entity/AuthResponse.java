package com.gateway.APIGatewayFinal.Entity;

import lombok.*;

import java.util.Collection;



@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {


    private String userId;

    private String accessToken;

    private String refreshToken;

    private long expiredAt;

    private Collection<String> authorities;
}
