package com.gateway.APIGatewayFinal.Controllers;


import com.gateway.APIGatewayFinal.Entity.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class OauthController {

    private Logger logger= LoggerFactory.getLogger(OauthController.class);


    @GetMapping("/login")
    public ResponseEntity<AuthResponse> getLoginresponse(
   @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
   @AuthenticationPrincipal OidcUser user,
   Model model
    )
    {
        AuthResponse authResponse=new AuthResponse();
        authResponse.setUserId(user.getEmail());
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());
        authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        authResponse.setExpiredAt(client.getAccessToken().getExpiresAt().getEpochSecond());
        List<String> authorities= user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());
        authResponse.setAuthorities(authorities);
        return ResponseEntity.ok(authResponse);

    }


}
