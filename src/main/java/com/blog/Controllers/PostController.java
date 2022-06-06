package com.blog.Controllers;

import com.blog.Controllers.Abstract.BaseController;
import com.blog.Entities.Post;
import com.blog.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post/")
public class PostController extends BaseController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("create")
    public String create(Model model) {
        if (model.getAttribute("newPost") == null)
            model.addAttribute("newPost", new Post());
        return "/postCreate";
    }

    @GetMapping("edit")
    public String edit(Model model) {
        if (model.getAttribute("newPost") == null)
            model.addAttribute("newPost", new Post());
        return "/postEdit";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> create(@ModelAttribute("newPost") Post post) {
        postService.create(post, authCurrentUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<String> read(@PathVariable("id") long id) {
        String json = postService.read(id);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<Void> update(@PathVariable("id") long id, @ModelAttribute("newPost") Post post) {
        postService.update(id, post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }


}