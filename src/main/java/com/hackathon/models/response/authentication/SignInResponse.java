/**
 * 
 */
package com.hackathon.models.response.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vivekhs
 *
 */
public class SignInResponse {

    @JsonProperty("access_token")
    private String accessToken;
    private String error;

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     *            the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

}
