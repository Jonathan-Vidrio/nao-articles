package models;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;

/**
 * Author model class
 * Represents the authors table in the database
 *
 * @author Jugh
 * @version 1.0
 */
@Entity
@Table(name = "authors")
public class Author {
    /**
     * Fields
     * id: int
     * authorId: String
     * name: String
     * link: String
     * affiliations: String
     * email: String
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "author_id", unique = true)
    @SerializedName("author_id")
    private String authorId;

    @Column(name = "name")
    @SerializedName("name")
    private String name;

    @Column(name = "link")
    @SerializedName("link")
    private String link;

    @Column(name = "affiliations")
    @SerializedName("affiliations")
    private String affiliations;

    @Column(name = "email")
    @SerializedName("email")
    private String email;

    public Author() {
    }

    /**
     * Constructor
     *
     * @param authorId
     * @param name
     * @param link
     * @param affiliations
     * @param email
     */
    public Author(String authorId, String name, String link, String affiliations, String email) {
        this.authorId = authorId;
        this.name = name;
        this.link = link;
        this.affiliations = affiliations;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
