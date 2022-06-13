package com.blog.Services;

import com.blog.DAO.CategoryDAO;
import com.blog.Entities.Category;
import com.blog.Services.Abstract.BaseService;
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

    public List<Category> getListCategory(){
        return categoryDAO.readAll();
    }

}
