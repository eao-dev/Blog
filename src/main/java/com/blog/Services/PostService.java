package com.blog.Services;


import com.blog.DAO.PostDAO;
import com.blog.Entities.Post;
import com.blog.Entities.User;
import com.blog.Services.Abstract.BaseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostService extends BaseService {

    private final PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public void create(Post post, User user) {
        post.setUserId(user.getId());
        postDAO.create(post);
    }

    public String read(Long privateKey) {
        Post post = postDAO.read(privateKey);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", post.getTitle());
        jsonObject.put("post", post.getPost());
        jsonObject.put("tags", post.getTags());
        jsonObject.put("timestamp", post.getTimestamp().toString());

        var comments = post.getComments();
        JSONArray commentsJson = new JSONArray();
        for (final var comment : comments) {
            JSONObject commentObject = new JSONObject();
            commentObject.put("name", comment.getName());
            commentObject.put("comment", comment.getComment());
            commentObject.put("timestamp", comment.getTimestamp().toString());
            commentsJson.put(commentObject);
            jsonObject.put("comments", commentsJson);
        }

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
