package eao.dev.DAO;

import eao.dev.DAO.Abstract.DAO;
import eao.dev.Entities.Comment;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO extends DAO<Comment, Long> {

    @Override
    public Comment read(@NotNull Long privateKey) {
        return super.read(Comment.class, privateKey);
    }

}