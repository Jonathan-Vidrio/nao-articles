package org.main;

import models.AuthorResult;
import org.connection.serpapi.Search;

public class Main {
    public static void main(String[] args) {
        Search search = new Search();
        AuthorResult result = search.getAuthorsByUniversity("UNAM");
        result.authors().forEach(author -> System.out.println(
                "Author: " + author.getName() + "\n" +
                        "Author ID: " + author.getAuthorId() + "\n" +
                        "Link: " + author.getLink() + "\n" +
                        "Affiliations: " + author.getAffiliations() + "\n" +
                        "Email: " + author.getEmail() + "\n"
        ));
    }
}