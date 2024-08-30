package org.edralabs.url_shortner.model;

public class CreateShortenURLRequest {
    private String longUrl;
    private String customAlias;
    private int ttlSeconds = 120;

    // Getter for longUrl
    public String getLongUrl() {
        return longUrl;
    }

    // Setter for longUrl
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    // Getter for customAlias
    public String getCustomAlias() {
        return customAlias;
    }

    // Setter for customAlias
    public void setCustomAlias(String customAlias) {
        this.customAlias = customAlias;
    }

    // Getter for ttlSeconds
    public int getTtlSeconds() {
        return ttlSeconds;
    }

    // Setter for ttlSeconds
    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }
}
