package com.blog.Services;

import com.blog.DAO.PostDAO;
import com.blog.Entities.Post;
import com.blog.Entities.User;
import com.blog.Services.Abstract.BaseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends BaseService {

    private final PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public String readAll() {

        List<Post> posts = postDAO.readAll();
        JSONArray jsonArray = new JSONArray();
        for (final var post : posts)
            jsonArray.put(post.toJson());

        return jsonArray.toString();
    }

    public void create(Post post, User user) {
        post.setUserId(user.getId());
        postDAO.create(post);
    }

    public String read(Long privateKey) {
        Post post = postDAO.read(privateKey);
        JSONObject jsonObject = post.toJson();
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
