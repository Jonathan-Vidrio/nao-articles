package org.connection.serpapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Search class is responsible for building the HTTP client, request, and sending the request to the SERP API.
 *
 * @author Jugh
 * @version 1.0
 */

public class Search {
    private final HttpClient client;
    private final Gson gson;

    /**
     * Constructor for the Search class.
     */
    public Search() {
        this.client = buildHttpClient();
        this.gson = new Gson();
    }

    /**
     * Builds the HTTP client.
     *
     * @return HttpClient
     */
    public HttpClient buildHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    /**
     * Builds the HTTP request.
     *
     * @param university The university to search for.
     * @return HttpRequest
     */
    public HttpRequest buildRequest(String university) {
        String apiKey = Dotenv.load().get("API_KEY");
        String encodedUniversity = URLEncoder.encode(university, StandardCharsets.UTF_8);
        String url = "https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=" + encodedUniversity + "&api_key=" + apiKey;

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }

    /**
     * Sends the HTTP request.
     *
     * @param request The HTTP request to send.
     * @return HttpResponse<String>
     * @throws Exception If the request fails.
     */
    public HttpResponse<String> sendRequest(HttpRequest request) throws Exception {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Checks if the response body is empty.
     *
     * @param responseBody The response body to check.
     * @return boolean
     */
    public boolean isEmptyResponse(String responseBody) {
        return responseBody.isEmpty();
    }

    /**
     * Gets the profiles array from the response body.
     *
     * @param responseBody The response body to get the profiles array from.
     * @return JsonArray
     */
    public JsonArray getProfilesArray(String responseBody) {
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        return jsonObject.getAsJsonArray("profiles");
    }
}