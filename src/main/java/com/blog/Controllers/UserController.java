package com.blog.Controllers;

import com.blog.Controllers.Abstract.BaseController;
import com.blog.Entities.User;
import com.blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("registration")
    public String registration(Model model) throws Exception {
        if (userService.getCount() > 0)
            throw new Exception("User already exists");

        model.addAttribute("newUser", new User());

        return "/registration";
    }

    @PostMapping("registration")
    @ResponseBody
    public ResponseEntity<Void> registration(@ModelAttribute("newUser") User user) {
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}