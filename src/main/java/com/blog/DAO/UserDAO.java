package com.blog.DAO;

import com.blog.DAO.Abstract.DAO;
import com.blog.Entities.User;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends DAO<User, Long> {

    @Override
    public User read(@NotNull Long privateKey) {
        return super.read(User.class, privateKey);
    }

    /**
     * Returns the number of users.
     * */
    public Long count() {
        return (Long) em
                .createQuery("select count(*) from User").getSingleResult();
    }

    /**
     * Returns a user instance by login.
     * @param login - user login.
     * */
    public User readByLogin(@NotNull String login) {
        return em
                .createQuery("select user from User user where user.login = ?1", User.class)
                .setParameter(1, login)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

}