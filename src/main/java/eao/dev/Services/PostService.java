package eao.dev.Services;

import eao.dev.DAO.CategoryDAO;
import eao.dev.DAO.PostDAO;
import eao.dev.Entities.Post;
import eao.dev.Entities.User;
import eao.dev.Services.Abstract.BaseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends BaseService {

    private final PostDAO postDAO;
    private final CategoryDAO categoryDAO;

    @Autowired
    public PostService(PostDAO postDAO, CategoryDAO categoryDAO) {
        this.postDAO = postDAO;
        this.categoryDAO = categoryDAO;
    }

    public String readAll() {

        List<Post> posts = postDAO.readAll();
        JSONArray jsonArray = new JSONArray();
        for (final var post : posts)
            jsonArray.put(post.toJson());

        return jsonArray.toString();
    }

    public void create(User user, String title, String description, String tags, String text, List<Long> categories) {
        Post post = new Post();
        post.setTitle(title);
        post.setTags(tags);
        post.setDescription(description);
        post.setPost(text);
        post.setUserId(user.getId());

        for (final var id : categories)
            post.addCategories(categoryDAO.read(id));

        postDAO.create(post);
    }

    public String read(Long privateKey) {
        Post post = postDAO.read(privateKey);
        JSONObject jsonObject = post.toJson();
        return jsonObject.toString();
    }

    /*public void update(Long postPk, Post newPost) {
        Post post = postDAO.read(postPk);
        post.setTitle(newPost.getTitle());
        post.setPost(newPost.getPost());
        post.setTags(newPost.getTags());
        postDAO.update(post);
    }*/

    public void delete(long privateKey) {
        postDAO.delete(postDAO.read(privateKey));
    }

}
