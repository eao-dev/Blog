package com.blog.DAO;

import com.blog.DAO.Abstract.DAO;
import com.blog.Entities.Comment;
import com.blog.Entities.Post;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO extends DAO<Comment, Long> {

    @Override
    public Comment read(@NotNull Long privateKey) {
        return super.read(Comment.class, privateKey);
    }

}