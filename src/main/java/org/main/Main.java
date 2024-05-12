package org.main;

import controllers.AuthorController;
import controllers.AuthorResultController;
import models.Author;
import models.AuthorResult;

import java.util.List;
import java.util.Scanner;

/**
 * The Main class could be interpreted as the view in MVC architecture.
 * It is responsible for the interaction with the user.
 * It uses the controllers to interact with the model.
 * It is the entry point of the application.
 *
 * @author Jugh
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Author Management System!\n" +
                "Please insert the name of the university to get the authors from: ");
        String university = scanner.nextLine();

        AuthorResult authorsByUniversity = getAuthorsByUniversity(university);
        System.out.println("Authors from " + university + " extracted from the API: ");
        // show the authors from the API
        authorsByUniversity.authors().forEach(Main::printAuthor);

        List<Author> authors = getAuthors();
        if (authors.isEmpty()) {
            // create the authors in the database
            authorsByUniversity.authors().forEach(Main::createAuthor);

            // show the created authors
            getAuthors().forEach(Main::printAuthor);
        } else {
            System.out.println("Authors from the database: ");
            // replace the existing authors with the new ones
            for (int i = 0; i < authors.size(); i++) {
                replaceAuthor(authors.get(i), authorsByUniversity.authors().get(i));
            }

            // show the updated authors
            getAuthors().forEach(Main::printAuthor);
        }

    }

    /**
     * The method getAuthorsByUniversity is responsible for getting the authors from the API.
     * It uses the AuthorResultController to interact with the model.
     *
     * @param university the name of the university
     * @return the authors from the API
     */
    private static AuthorResult getAuthorsByUniversity(String university) {
        AuthorResultController resultController = new AuthorResultController();
        return resultController.getAuthorsByUniversity(university);
    }

    /**
     * The method createAuthor is responsible for creating an author in the database.
     * It uses the AuthorController to interact with the model.
     *
     * @param author the author to be created
     */
    private static void createAuthor(Author author) {
        AuthorController authorController = new AuthorController();
        authorController.createAuthor(author);
    }

    /**
     * The method replaceAuthor is responsible for replacing an existing author with a new one.
     * It uses the AuthorController to interact with the model.
     *
     * @param existingAuthor the existing author
     * @param newAuthor      the new author
     */
    private static void replaceAuthor(Author existingAuthor, Author newAuthor) {
        AuthorController authorController = new AuthorController();
        newAuthor.setId(existingAuthor.getId());
        authorController.updateAuthor(newAuthor);
    }

    /**
     * The method getAuthors is responsible for getting the authors from the database.
     * It uses the AuthorController to interact with the model.
     *
     * @return the authors from the database
     */
    private static List<Author> getAuthors() {
        AuthorController authorController = new AuthorController();
        return authorController.getAuthors();
    }

    /**
     * The method printAuthor is responsible for printing the author.
     *
     * @param author the author to be printed
     */
    private static void printAuthor(Author author) {
        if (author.getId() != 0) {
            System.out.println("Id: " + author.getId());
        }

        System.out.println("Author: " + author.getName());
        System.out.println("Author ID: " + author.getAuthorId());
        System.out.println("Link: " + author.getLink());
        System.out.println("Affiliations: " + author.getAffiliations());
        System.out.println("Email: " + author.getEmail() + "\n");
    }
}