package models;

import java.util.List;

public record AuthorResult(List<Author> authors, String message) {
}
