package com.blog.Config.Security;

import com.blog.Entities.User;
import com.blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Custom authorization provider.
 **/
@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public AuthProvider(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns user {@link User} from DB by login.
     */
    User getUserByLogin(String login) {
        try {
            return userService.getUserByLogin(login);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Authenticates the user in the system; If successful, an authentication token is set,
     * otherwise an exception is thrown with an error message.
     *
     * @param authentication object {@link Authentication}
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        final String loginErrorMessage = "Login failure!";
        final var credentials = authentication.getCredentials();

        User user = getUserByLogin(authentication.getName());
        if (user != null) {
            if (BCrypt.checkpw(credentials.toString(), user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user, credentials,
                        authentication.getAuthorities());
            }
        }
        throw new BadCredentialsException(loginErrorMessage); // Invalid credentials
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}