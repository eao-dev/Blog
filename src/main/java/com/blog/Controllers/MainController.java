package com.blog.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class MainController {

    @GetMapping
    public String main() {
        return "/index";
    }

    @GetMapping("about")
    @ResponseBody
    public ResponseEntity<String> about() {
        final String aboutHTML = "This is my personal IT blog. Here I share my solutions and interesting articles.";
        return new ResponseEntity<>(aboutHTML, HttpStatus.OK);
    }

    @GetMapping("contact")
    @ResponseBody
    public ResponseEntity<String> contact() {
        final String contactHTML = "Email: mail@mail.com <br> Phone:88888888888";
        return new ResponseEntity<>(contactHTML, HttpStatus.OK);
    }

}
