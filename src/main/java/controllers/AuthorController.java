package controllers;

import models.Author;
import org.connection.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * AuthorController class is responsible for handling the CRUD operations for the Author entity.
 *
 * @author Jugh
 * @version 1.0
 */
public class AuthorController {

    /**
     * Create a new author.
     *
     * @param author The author object to be created.
     * @return The created author object.
     */
    public Author createAuthor(Author author) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(author);
            transaction.commit();

            return author;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;

        }
    }

    /**
     * Get all authors from the database.
     *
     * @return The list of authors.
     */
    public List<Author> getAuthors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Author", Author.class).list();
        }
    }

    /**
     * Get an author by its ID.
     *
     * @param id The ID of the author.
     * @return The author object.
     */
    public Author getAuthorById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Author.class, id);
        }
    }

    /**
     * Update an existing author.
     *
     * @param author The author object to be updated.
     * @return The updated author object.
     */
    public Author updateAuthor(Author author) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Author existingAuthor = session.get(Author.class, author.getId());
            System.out.println("replace author: " + existingAuthor.getAuthorId() + " with " + author.getAuthorId());
            existingAuthor.setAuthorId(author.getAuthorId());
            existingAuthor.setName(author.getName());
            existingAuthor.setLink(author.getLink());
            existingAuthor.setAffiliations(author.getAffiliations());
            existingAuthor.setEmail(author.getEmail());

            transaction.commit();

            return existingAuthor;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;

        }
    }

    /**
     * Delete an author by its ID.
     *
     * @param id The ID of the author.
     */
    public void deleteAuthor(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Author author = session.get(Author.class, id);
            if (author != null) {
                session.remove(author);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;

        }
    }
}
