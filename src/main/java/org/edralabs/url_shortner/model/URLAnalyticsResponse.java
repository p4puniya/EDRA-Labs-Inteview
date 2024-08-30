package org.edralabs.url_shortner.model;

import java.time.LocalDateTime;
import java.util.Deque;

public class URLAnalyticsResponse {
    private String alias;
    private String longUrl;
    private int accessCount;
    private Deque<LocalDateTime> accessTimes;

    // Constructor
    public URLAnalyticsResponse(String alias, String longUrl, int accessCount, Deque<LocalDateTime> accessTimes) {
        this.alias = alias;
        this.longUrl = longUrl;
        this.accessCount = accessCount;
        this.accessTimes = accessTimes;
    }

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
}
