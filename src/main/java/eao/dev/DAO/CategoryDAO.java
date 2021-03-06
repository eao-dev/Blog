package eao.dev.DAO;

import eao.dev.DAO.Abstract.DAO;
import eao.dev.Entities.Category;
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

    @Override
    public void delete(Category category) {
        em.createNativeQuery(
                "DELETE FROM POST WHERE POST.id IN (SELECT post_id FROM postcategories where categories_id=?1)")
                .setParameter(1, category.getId())
                .executeUpdate();
        super.delete(category);
    }
}
