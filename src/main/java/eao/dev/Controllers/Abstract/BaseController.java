package eao.dev.Controllers.Abstract;

import eao.dev.Entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    protected User authCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User)
            return (User) principal;

        return null;
    }

}
