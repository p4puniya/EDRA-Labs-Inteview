package org.edralabs.url_shortner.model;


import lombok.Data;

@Data
public class UpdateShortenURLRequest {
    private String customAlias;
    private int ttlSeconds;

    //getters
    public String getCustomAlias() {
        return customAlias;
    }

    public int getTtlSeconds() {
        return ttlSeconds;
    }
}
