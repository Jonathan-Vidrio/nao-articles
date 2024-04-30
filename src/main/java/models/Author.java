package models;

import com.google.gson.annotations.SerializedName;

public class Author {
    @SerializedName("name")
    private String name;

    @SerializedName("author_id")
    private String authorId;

    @SerializedName("link")
    private String link;

    @SerializedName("affiliations")
    private String affiliations;

    @SerializedName("email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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
