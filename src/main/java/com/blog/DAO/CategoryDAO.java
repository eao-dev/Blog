package com.blog.DAO;

import com.blog.DAO.Abstract.DAO;
import com.blog.Entities.Category;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO extends DAO<Category, Long> {

    @Override
    public Category read(@NotNull Long privateKey) {
        return super.read(Category.class, privateKey);
    }

    public List<Category> readAll() {
        return em.createQuery("select category from Category category", Category.class).getResultList();
    }

}
