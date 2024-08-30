package org.edralabs.url_shortner.controller;
import org.edralabs.url_shortner.model.CreateShortenURLRequest;
import org.edralabs.url_shortner.model.CreateShortenURLResponse;
import org.edralabs.url_shortner.model.URLAnalyticsResponse;
import org.edralabs.url_shortner.model.UpdateShortenURLRequest;
import org.edralabs.url_shortner.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UrlShorteningController {

    @Autowired
    private UrlShorteningService service;

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> testEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Test successful");
        return ResponseEntity.ok(response);
    }

    //what if it already exists?
    @PostMapping("/shorten")
    public ResponseEntity<CreateShortenURLResponse> createShortenUrl(
            @RequestBody CreateShortenURLRequest request,
            @RequestHeader("Host") String baseUrl) {
        return ResponseEntity.ok(service.createShortenUrl(request, "http://" + baseUrl));
    }

    @GetMapping("/analytics/{alias}")
    public ResponseEntity<URLAnalyticsResponse> getUrlAnalytics(@PathVariable String alias) {
        return ResponseEntity.ok(service.getUrlAnalytics(alias));
    }
//Doesn't affect the Shorten URL, just affects the Alias
    @PutMapping("/update/{alias}")
    public ResponseEntity<String> updateShortenUrl(@PathVariable String alias, @RequestBody UpdateShortenURLRequest request) {
        service.updateShortenUrl(alias, request);
        return ResponseEntity.ok("Successfully updated.");
    }

    @DeleteMapping("/delete/{alias}")
    public ResponseEntity<String> deleteShortenUrl(@PathVariable String alias) {
        service.deleteShortenUrl(alias);
        return ResponseEntity.ok("Successfully deleted.");
    }

    @GetMapping("/{alias}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String alias) {
        String redirectUrl = service.redirectUrl(alias);
        return ResponseEntity.status(302).header("Location", redirectUrl).build();
    }
}
