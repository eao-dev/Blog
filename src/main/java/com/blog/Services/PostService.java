package com.blog.Services;

import com.blog.DAO.CategoryDAO;
import com.blog.DAO.PostDAO;
import com.blog.Entities.Post;
import com.blog.Entities.User;
import com.blog.Services.Abstract.BaseService;
import com.sun.istack.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends BaseService {

    private final PostDAO postDAO;

    private final CategoryDAO categoryDAO;

    public PostService(PostDAO postDAO, CategoryDAO categoryDAO) {
        this.postDAO = postDAO;
        this.categoryDAO = categoryDAO;
    }

    private JSONObject getJsonPost(@NotNull Post post) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", post.getId());
        jsonObject.put("author", post.getUser().getLogin());
        jsonObject.put("description", post.getDescription());
        jsonObject.put("title", post.getTitle());
        jsonObject.put("post", post.getPost());
        jsonObject.put("tags", post.getTags());
        jsonObject.put("timestamp", post.getTimestamp().toString());

        final var comments = post.getComments();
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
        final var categories = post.getCategories();
        for (final var category : categories) {
            JSONObject categoryObject = new JSONObject();
            categoryObject.put("id", category.getId());
            categoryObject.put("name", category.getName());
            categoriesJson.put(categoryObject);
        }
        jsonObject.put("categories", categoriesJson);

        return jsonObject;
    }

    public String readAll() {

        List<Post> posts = postDAO.readAll();
        JSONArray jsonArray = new JSONArray();
        for (final var post : posts)
            jsonArray.put(getJsonPost(post));

        return jsonArray.toString();
    }

    public void create(Post post, User user) {
        post.setUserId(user.getId());
        postDAO.create(post);
    }

    public String read(Long privateKey) {
        Post post = postDAO.read(privateKey);
        JSONObject jsonObject = getJsonPost(post);
        return jsonObject.toString();
    }

    public void update(Long postPk, Post newPost) {
        Post post = postDAO.read(postPk);
        post.setTitle(newPost.getTitle());
        post.setPost(newPost.getPost());
        post.setTags(newPost.getTags());
        postDAO.update(post);
    }

    public void delete(long privateKey) {
        postDAO.delete(postDAO.read(privateKey));
    }

}
