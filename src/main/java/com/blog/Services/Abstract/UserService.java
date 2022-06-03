package com.blog.Services.Abstract;

import com.blog.DAO.UserDAO;
import com.blog.Entities.User;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {

    private UserDAO userDAO;

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

    public void createNewUser(User user) {
        final String rawPassword = user.getPassword();
        final String hashPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        user.setPassword(hashPassword);
        userDAO.create(user);
    }

}
