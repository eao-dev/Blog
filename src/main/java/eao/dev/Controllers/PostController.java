package eao.dev.Controllers;

import eao.dev.Controllers.Abstract.BaseController;
import eao.dev.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController extends BaseController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<String> all() {
        String json = postService.readAll();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Void> create(@RequestParam(value = "title") String title,
                                       @RequestParam(value = "description") String description,
                                       @RequestParam(value = "tags") String tags,
                                       @RequestParam(value = "text") String text,
                                       @RequestParam(value = "categories") List<Long> categories) {
        postService.create(authCurrentUser(), title, description, tags, text, categories);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<String> read(@PathVariable("id") long id) {
        String json = postService.read(id);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    /*@PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, Post post) {
        postService.update(id, post);
        return ResponseEntity.ok().build();
    }*/

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }


}