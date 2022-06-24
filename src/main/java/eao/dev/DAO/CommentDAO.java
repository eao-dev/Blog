package eao.dev.DAO;

import eao.dev.DAO.Abstract.DAO;
import eao.dev.Entities.Comment;
import com.sun.istack.NotNull;
import eao.dev.Entities.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO extends DAO<Comment, Long> {

    @Override
    public Comment read(@NotNull Long privateKey) {
        return super.read(Comment.class, privateKey);
    }

    public List<Comment> readAll() {
        return em.createQuery("select comment from Comment comment", Comment.class).getResultList();
    }
}