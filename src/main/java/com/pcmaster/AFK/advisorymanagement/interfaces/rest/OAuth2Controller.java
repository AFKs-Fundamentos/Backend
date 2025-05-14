package com.pcmaster.AFK.advisorymanagement.interfaces.rest;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.pcmaster.AFK.advisorymanagement.google.GoogleMeetProperties;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

/**
 * Controlador para manejar OAuth2 web flow.
 */
@RestController
@RequestMapping("/api/v1/meets")
public class OAuth2Controller {
    private final GoogleMeetProperties props;
    private final NetHttpTransport httpTransport = new NetHttpTransport();
    private final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    public OAuth2Controller(GoogleMeetProperties props) {
        this.props = props;
    }

    /**
     * Redirige al usuario a Google para autorizar la aplicación.
     */
    @GetMapping("/authorize")
    public void authorize(HttpServletResponse response) throws Exception {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                jsonFactory,
                new InputStreamReader(props.getCredentialsFile().getInputStream())
        );

        AuthorizationCodeRequestUrl authUrl = new GoogleAuthorizationCodeRequestUrl(
                clientSecrets.getDetails().getClientId(),
                "http://localhost:51843/Callback",
                props.getScopes()
        )
                .setAccessType("offline");

        response.sendRedirect(authUrl.build());
    }

    /**
     * Callback que recibe el código de autorización y canjea tokens.
     */
    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code) throws Exception {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                jsonFactory,
                new InputStreamReader(props.getCredentialsFile().getInputStream())
        );

        TokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                httpTransport,
                jsonFactory,
                clientSecrets.getDetails().getClientId(),
                clientSecrets.getDetails().getClientSecret(),
                code,
                "http://localhost:51843/Callback"
        ).execute();

        // Opcional: almacenar tokenResponse.getAccessToken() y getRefreshToken() en props.getTokensDir()
        // ...
        return ResponseEntity.ok("OAuth2 autorizado. Access token obtenido.");
    }
}
