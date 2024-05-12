package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import models.Author;
import models.AuthorResult;
import org.connection.serpapi.Search;

import java.lang.reflect.Type;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * AuthorController class is responsible for handling the author's data.
 *
 * @author Jugh
 * @version 1.0
 */
public class AuthorResultController {
    private final Gson gson;
    private final Search search;

    /**
     * Constructor for the AuthorController class.
     */
    public AuthorResultController() {
        this.gson = new Gson();
        this.search = new Search();
    }

    /**
     * Convert the JsonArray to a list of authors.
     *
     * @param profilesArray The JsonArray to convert.
     * @return List<Author>
     */
    private List<Author> convertJsonToAuthors(JsonArray profilesArray) {
        Type listType = new TypeToken<List<Author>>() {
        }.getType();
        return gson.fromJson(profilesArray, listType);
    }

    /**
     * Parse the response body and return the AuthorResult.
     *
     * @param responseBody The response body to parse.
     * @return AuthorResult
     */
    private AuthorResult parseResponse(String responseBody) {
        if (search.isEmptyResponse(responseBody)) {
            return new AuthorResult(null, "Empty response body");
        }

        JsonArray profilesArray = search.getProfilesArray(responseBody);

        if (profilesArray == null) {
            return new AuthorResult(null, "No profiles found");
        }

        List<Author> authors = convertJsonToAuthors(profilesArray);

        return new AuthorResult(authors, "Success");
    }

    /**
     * Get authors by university.
     *
     * @param university The university to search for.
     * @return AuthorResult
     */
    public AuthorResult getAuthorsByUniversity(String university) {
        HttpRequest request = search.buildRequest(university);

        try {
            HttpResponse<String> response = search.sendRequest(request);
            return parseResponse(response.body());
        } catch (Exception e) {
            return new AuthorResult(null, e.getMessage());
        }
    }
}
