package eao.dev.Services;

import eao.dev.DAO.CommentDAO;
import eao.dev.Entities.Comment;
import eao.dev.Services.Abstract.BaseService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class CommentService extends BaseService {

    private final CommentDAO commentDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void create(Long postPk, String name, String commentSource) {
        if (name.isEmpty() || commentSource.isEmpty())
            throw new InvalidParameterException("Name or comment is empty!");
        Comment comment = new Comment();
        comment.setName(name);
        comment.setComment(commentSource);
        comment.setPostId(postPk);
        commentDAO.create(comment);
    }

    public String readAll() {
        List<Comment> comments = commentDAO.readAll();
        JSONArray jsonArray = new JSONArray();
        for (final var comment : comments)
            jsonArray.put(comment.toJson());
        return jsonArray.toString();
    }
}
