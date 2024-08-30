package org.edralabs.url_shortner.model;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;

public class ShortenedURL {
    private String alias;
    private String longUrl;
    private int ttlSeconds;
    private int accessCount = 0;
    private Deque<LocalDateTime> accessTimes = new LinkedList<>();
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getter for alias
    public String getAlias() {
        return alias;
    }

    // Setter for alias
    public void setAlias(String alias) {
        this.alias = alias;
    }

    // Getter for longUrl
    public String getLongUrl() {
        return longUrl;
    }

    // Setter for longUrl
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    // Getter for ttlSeconds
    public int getTtlSeconds() {
        return ttlSeconds;
    }

    // Setter for ttlSeconds
    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    // Getter for accessCount
    public int getAccessCount() {
        return accessCount;
    }

    // Setter for accessCount
    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    // Getter for accessTimes
    public Deque<LocalDateTime> getAccessTimes() {
        return accessTimes;
    }

    // Setter for accessTimes
    public void setAccessTimes(Deque<LocalDateTime> accessTimes) {
        this.accessTimes = accessTimes;
    }

    // Getter for createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter for createdAt
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
