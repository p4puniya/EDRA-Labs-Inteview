package org.edralabs.url_shortner.service;

import org.edralabs.url_shortner.model.*;
import org.edralabs.url_shortner.utils.Utils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlShorteningService {
    private final Map<String, ShortenedURL> masterUrlShortenData = new HashMap<>();

    public CreateShortenURLResponse createShortenUrl(CreateShortenURLRequest request, String baseUrl) {
        //What if it pre-exists?
        if(masterUrlShortenData.containsKey(request.getCustomAlias())){
            throw new RuntimeException("Alias already exist");
        }

        if (request.getCustomAlias() == null || request.getCustomAlias().isEmpty()) {
            request.setCustomAlias(Utils.randomAliasString(5));
        }

        ShortenedURL shortenedUrl = new ShortenedURL();
        shortenedUrl.setAlias(request.getCustomAlias());
        shortenedUrl.setLongUrl(request.getLongUrl());
        shortenedUrl.setTtlSeconds(request.getTtlSeconds());
        shortenedUrl.setCreatedAt(LocalDateTime.now());

        masterUrlShortenData.put(request.getCustomAlias(), shortenedUrl);

        CreateShortenURLResponse response = new CreateShortenURLResponse(baseUrl + "/" + request.getCustomAlias());
        return response;
    }

    public URLAnalyticsResponse getUrlAnalytics(String alias) {
        ShortenedURL shortenedUrl = masterUrlShortenData.get(alias);
        if (shortenedUrl == null) {
            throw new RuntimeException("Alias does not exist");
        }
        return new URLAnalyticsResponse(shortenedUrl.getAlias(), shortenedUrl.getLongUrl(),
                shortenedUrl.getAccessCount(), shortenedUrl.getAccessTimes());
    }

    public void updateShortenUrl(String alias, UpdateShortenURLRequest request) {
        ShortenedURL shortenedUrl = masterUrlShortenData.get(alias);
        if (shortenedUrl == null) {
            throw new RuntimeException("Alias does not exist");
        }
        if (shortenedUrl.getCreatedAt().plusSeconds(shortenedUrl.getTtlSeconds()).isBefore(LocalDateTime.now())) {
            masterUrlShortenData.remove(alias);
            throw new RuntimeException("Alias has expired");
        }
        String newAlias = request.getCustomAlias();
        if (newAlias != null && !newAlias.isEmpty() && !newAlias.equals(alias)) {
            masterUrlShortenData.remove(alias); // Remove the old entry
            shortenedUrl.setAlias(newAlias); // Set the new alias
            alias = newAlias; // Update the alias reference
        }

        shortenedUrl.setAlias(request.getCustomAlias());
        shortenedUrl.setTtlSeconds(request.getTtlSeconds());

        masterUrlShortenData.put(alias, shortenedUrl);
    }

    public void deleteShortenUrl(String alias) {
        ShortenedURL shortenedUrl = masterUrlShortenData.get(alias);
        if (shortenedUrl == null) {
            throw new RuntimeException("Alias does not exist");
        }
        if (shortenedUrl.getCreatedAt().plusSeconds(shortenedUrl.getTtlSeconds()).isBefore(LocalDateTime.now())) {
            masterUrlShortenData.remove(alias);
            throw new RuntimeException("Alias has expired");
        }
        masterUrlShortenData.remove(alias);
    }

    public String redirectUrl(String alias) {
        ShortenedURL shortenedUrl = masterUrlShortenData.get(alias);
        if (shortenedUrl == null) {
            throw new RuntimeException("Alias does not exist");
        }
        if (shortenedUrl.getCreatedAt().plusSeconds(shortenedUrl.getTtlSeconds()).isBefore(LocalDateTime.now())) {
            masterUrlShortenData.remove(alias);
            throw new RuntimeException("Alias has expired");
        }
        shortenedUrl.setAccessCount(shortenedUrl.getAccessCount() + 1);
        shortenedUrl.getAccessTimes().add(LocalDateTime.now());
        return shortenedUrl.getLongUrl();
    }
}
