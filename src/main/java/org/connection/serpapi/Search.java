package org.connection.serpapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.github.cdimascio.dotenv.Dotenv;
import models.Author;
import models.AuthorResult;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;


public class Search {
    private final HttpClient client;
    private final Gson gson;

    public Search() {
        this.client = buildHttpClient();
        this.gson = new Gson();
    }

    private HttpClient buildHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    private HttpRequest buildRequest(String university) {
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

    private HttpResponse<String> sendRequest(HttpRequest request) throws Exception {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private boolean isEmptyResponse(String responseBody) {
        return responseBody.isEmpty();
    }

    private JsonArray getProfilesArray(String responseBody) {
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        return jsonObject.getAsJsonArray("profiles");
    }

    private List<Author> convertJsonToAuthors(JsonArray profilesArray) {
        Type listType = new TypeToken<List<Author>>() {
        }.getType();
        return gson.fromJson(profilesArray, listType);
    }

    private AuthorResult parseResponse(String responseBody) {
        if (isEmptyResponse(responseBody)) {
            return new AuthorResult(null, "Empty response body");
        }

        JsonArray profilesArray = getProfilesArray(responseBody);

        if (profilesArray == null) {
            return new AuthorResult(null, "No profiles found");
        }

        List<Author> authors = convertJsonToAuthors(profilesArray);

        return new AuthorResult(authors, "Success");
    }

    public AuthorResult getAuthorsByUniversity(String university) {
        HttpRequest request = buildRequest(university);

        try {
            HttpResponse<String> response = sendRequest(request);
            return parseResponse(response.body());
        } catch (Exception e) {
            return new AuthorResult(null, e.getMessage());
        }
    }
}