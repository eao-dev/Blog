package com.blog.Entities;

import com.blog.Entities.Abstract.BaseEntity;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToMany(mappedBy = "posts", fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(name = "title")
    private String title;
    @Column(name = "post")
    private String post;
    @Column(name = "tags")
    private String tags;

    @Column(name = "description")
    private String description;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategories(Category category) {
        categories.add(category);
        category.getPosts().add(this);
    }

    public void removeCategories(Category category) {
        categories.add(category);
        category.getPosts().remove(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "timestamp", insertable = false, updatable = false)
    private Timestamp timestamp;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("author", user.getLogin());
        jsonObject.put("description", description);
        jsonObject.put("title", title);
        jsonObject.put("post", post);
        jsonObject.put("tags", tags);
        jsonObject.put("timestamp", timestamp.toString());

        JSONArray commentsJson = new JSONArray();
        for (final var comment : comments) {
            JSONObject commentObject = new JSONObject();
            commentObject.put("name", comment.getName());
            commentObject.put("comment", comment.getComment());
            commentObject.put("timestamp", comment.getTimestamp().toString());
            commentsJson.put(commentObject);
            jsonObject.put("comments", commentsJson);
        }

        JSONArray categoriesJson = new JSONArray();
        for (final var category : categories) {
            JSONObject categoryObject = new JSONObject();
            categoryObject.put("id", category.getId());
            categoryObject.put("name", category.getName());
            categoriesJson.put(categoryObject);
        }
        jsonObject.put("categories", categoriesJson);

        return jsonObject;
    }
}
