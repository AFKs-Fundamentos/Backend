package com.pcmaster.AFK.advisorymanagement.google;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.apps.meet.v2.CreateSpaceRequest;
import com.google.apps.meet.v2.Space;
import com.google.apps.meet.v2.SpacesServiceClient;
import com.google.apps.meet.v2.SpacesServiceSettings;
import com.google.auth.Credentials;
import org.springframework.stereotype.Service;

@Service
public class GoogleMeetService {
    private final GoogleMeetAuthService auth;

    public GoogleMeetService(GoogleMeetAuthService auth) {
        this.auth = auth;
    }

    public String createMeetSpace() throws Exception {
        Credentials creds = auth.authorize();
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
}
