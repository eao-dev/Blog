package eao.dev.Controllers;

import eao.dev.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestParam("post") Long postPk,
                                       @RequestParam("name") String name,
                                       @RequestParam("comment") String comment) {
        commentService.create(postPk, name, comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<String> readAll() {
        String json = commentService.readAll();
        return new ResponseEntity<>(json, HttpStatus.OK);

    }
}
