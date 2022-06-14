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
        return new ResponseEntity<>("...", HttpStatus.OK);
    }

    @GetMapping("contact")
    @ResponseBody
    public ResponseEntity<String> contact() {
        return new ResponseEntity<>("...", HttpStatus.OK);
    }

}
