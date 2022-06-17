package com.blog.Services;

import com.blog.DAO.CategoryDAO;
import com.blog.Entities.Category;
import com.blog.Services.Abstract.BaseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getListCategory() {
        return categoryDAO.readAll();
    }

    public String getJsonListCategory() {
        final var list = getListCategory();
        JSONArray arr = new JSONArray();
        for (final var category : list) {
            JSONObject object = new JSONObject();
            object.put("id", category.getId());
            object.put("name", category.getName());
            arr.put(object);
        }
        return arr.toString();
    }

    public String read(long id) {
        Category category = categoryDAO.read(id);
        JSONObject object = new JSONObject();
        object.put("id", category.getId());
        object.put("name", category.getName());
        JSONArray arr = new JSONArray();
        for (final var post : category.getPosts())
            arr.put(post.toJson());
        object.put("posts", arr);
        return object.toString();
    }

    public void create(Category category) {
        categoryDAO.create(category);
    }
}
