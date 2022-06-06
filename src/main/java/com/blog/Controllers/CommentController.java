package com.blog.Controllers;

import com.blog.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> create(@RequestParam("post") Long postPk,
                                       @RequestParam("name") String name,
                                       @RequestParam("source") String source) {
        commentService.create(postPk, name, source);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
