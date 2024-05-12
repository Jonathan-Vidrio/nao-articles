package models;

import java.util.List;

/**
 * AuthorResult record
 *
 * @param authors
 * @param message
 * @author Jugh
 * @version 1.0
 */
public record AuthorResult(List<Author> authors, String message) {
}
