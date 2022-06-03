package com.blog.DAO;

import com.blog.DAO.Abstract.DAO;
import com.blog.Entities.Post;
import com.blog.Entities.User;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAO extends DAO<Post, Long> {

    @Override
    public Post read(@NotNull Long privateKey) {
        return super.read(Post.class, privateKey);
    }

    public Post readByUser(@NotNull Long privateKey) {
        // TODO: implement
        return null;
    }

}
