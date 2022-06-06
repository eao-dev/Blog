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

    /**
     * Returns a post instance by user.
     * @param privateKey user private key
     * */
    public Post readByUser(@NotNull Long privateKey) {
        return em.createQuery("select post from Post post where post.userId = ?1", Post.class)
                .setParameter(1, privateKey)
                .getResultStream().findFirst()
                .orElse(null);
    }

}
