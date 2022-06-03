package com.blog.Controllers.Abstract;

import com.blog.Exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Void> forbiddenExceptionHandler(ForbiddenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    protected void checkAccess() throws ForbiddenException {
      // TODO: IMPLEMENT METHOD
    }

}
