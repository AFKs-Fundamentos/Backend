package com.pcmaster.AFK.advisorymanagement.google;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.apps.meet.v2.CreateSpaceRequest;
import com.google.apps.meet.v2.Space;
import com.google.apps.meet.v2.SpacesServiceClient;
import com.google.apps.meet.v2.SpacesServiceSettings;
import com.google.auth.Credentials;
import com.google.auth.oauth2.UserAuthorizer;
import com.google.auth.oauth2.ClientId;
import com.google.auth.oauth2.TokenStore;
import com.google.auth.oauth2.DefaultPKCEProvider;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.util.Collections;
import java.util.List;


@Component
public class GoogleMeetAuthService {


    private static final String USER = "default";
    private final GoogleMeetProperties props;


    public GoogleMeetAuthService(GoogleMeetProperties props) {
        this.props = props;
    }

    public Credentials authorize() throws Exception {
        // Carga las credenciales del JSON
        try (InputStream in = props.getCredentialsFile().getInputStream()) {
            ClientId clientId = ClientId.fromStream(in);
            TokenStore store = new DiskTokenStore(props.getTokensDir());

            // Configuración explícita del receptor OAuth2 con host, puerto y callback path fijos
            LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                    .setHost("localhost")             // Host donde se sirve el callback
                    .setPort(51843)                   // Puerto fijo (debes registrar este puerto)
                    .build();                         // :contentReference[oaicite:0]{index=0}

            URI redirectUri = URI.create(receiver.getRedirectUri()); // e.g. http://localhost:51843/Callback

            UserAuthorizer authorizer = UserAuthorizer.newBuilder()
                    .setClientId(clientId)
                    .setScopes(props.getScopes())
                    .setCallbackUri(redirectUri)     // Usamos la URI fija
                    .setPKCEProvider(new DefaultPKCEProvider() {
                        @Override
                        public String getCodeChallenge() {
                            // Elimina cualquier '=' de padding para cumplir con RFC 7636
                            return super.getCodeChallenge().replace("=", "");
                        }
                    })
                    .setTokenStore(store)
                    .build();                         // :contentReference[oaicite:1]{index=1}

            // Si ya está autorizado, devuelve credenciales
            Credentials creds = authorizer.getCredentials(USER);
            if (creds != null) {
                receiver.stop();
                return creds;
            }

            // Lanza la URL de autorización y espera el código
            System.out.println("Abre esta URL para autorizar: " + authorizer.getAuthorizationUrl(USER, "", null));
            String code = receiver.waitForCode(); // Bloquea hasta recibir el código

            // Intercambia el código por credenciales y las almacena
            Credentials newCreds = authorizer.getAndStoreCredentialsFromCode(
                    USER, code, redirectUri);
            receiver.stop();
            return newCreds;
        }
    }

    /**
     * Crea un espacio de Google Meet y devuelve el enlace de la reunión.
     */
    public String createMeetLink() throws Exception {
        Credentials creds = authorize();
        SpacesServiceSettings settings = SpacesServiceSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(creds))
                .build();

        try (SpacesServiceClient client = SpacesServiceClient.create(settings)) {
            CreateSpaceRequest req = CreateSpaceRequest.newBuilder()
                    .setSpace(Space.newBuilder().build())
                    .build();
            Space space = client.createSpace(req);
            return space.getMeetingUri();
        }
    }

    // Implementación de TokenStore que guarda JSON en disco
    static class DiskTokenStore implements TokenStore {
        private final Path base;
        DiskTokenStore(String dir) { this.base = Paths.get(dir); }
        private Path path(String id) throws Exception {
            Files.createDirectories(base);
            return base.resolve(id + ".json");
        }
        public String load(String id) throws IOException {
            Path p = null;
            try {
                p = path(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Files.exists(p) ? Files.readString(p) : null;
        }
        public void store(String id, String token) {
            try {
                Files.writeString(path(id), token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        public void delete(String id) {
            try {
                Files.deleteIfExists(path(id));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
