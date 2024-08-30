package org.edralabs.url_shortner.model;

public class CreateShortenURLResponse {
    private String shortUrl;

    public CreateShortenURLResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    // Getter for shortUrl
    public String getShortUrl() {
        return shortUrl;
    }

    // Setter for shortUrl
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
