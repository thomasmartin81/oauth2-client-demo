package ch.duerri.oauth2.client.github;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GitHubAccessResponse {

    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("token_type")
    private String tokenType;

    private String scope;

    @Override
    public String toString() {
        return "GitHubAccessResponse{" + "accessToken='" + accessToken + '\'' + ", tokenType='" + tokenType + '\'' + ", scope='" + scope + '\'' + '}';
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
