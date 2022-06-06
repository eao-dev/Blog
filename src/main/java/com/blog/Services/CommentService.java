package com.blog.Services;

import com.blog.DAO.CommentDAO;
import com.blog.DAO.PostDAO;
import com.blog.Entities.Comment;
import com.blog.Services.Abstract.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends BaseService {

    private final CommentDAO commentDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void create(Long postPk, String name, String commentSource) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setComment(commentSource);
        comment.setPostId(postPk);
        commentDAO.create(comment);
    }

}
