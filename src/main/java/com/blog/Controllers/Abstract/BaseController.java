package com.blog.Controllers.Abstract;

import com.blog.Entities.User;
import com.blog.Exceptions.ForbiddenException;
import com.blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Void> forbiddenExceptionHandler(ForbiddenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    protected void checkAccess() throws ForbiddenException {
        // TODO: IMPLEMENT METHOD
    }

    protected User authCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User)
            return (User) principal;

        return null;
    }

}
