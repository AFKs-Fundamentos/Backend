package com.pcmaster.AFK.advisorymanagement.google;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "google.meet")
public class GoogleMeetProperties {
    private Resource credentialsFile;
    private String tokensDir;
    private List<String> scopes;

    // getters y setters
    public Resource getCredentialsFile() { return credentialsFile; }
    public void setCredentialsFile(Resource credentialsFile) { this.credentialsFile = credentialsFile; }
    public String getTokensDir() { return tokensDir; }
    public void setTokensDir(String tokensDir) { this.tokensDir = tokensDir; }
    public List<String> getScopes() { return scopes; }
    public void setScopes(List<String> scopes) { this.scopes = scopes; }


}