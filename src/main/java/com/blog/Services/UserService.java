package com.blog.Services;

import com.blog.DAO.UserDAO;
import com.blog.Entities.User;
import com.blog.Services.Abstract.BaseService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService extends BaseService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByLogin(@NotNull String login) {
        return userDAO.readByLogin(login);
    }

    public Long getCount(){
        return userDAO.count();
    }

    public void create(User user) {
        final String rawPassword = user.getPassword();
        final String hashPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        user.setPassword(hashPassword);
        userDAO.create(user);
    }

}
