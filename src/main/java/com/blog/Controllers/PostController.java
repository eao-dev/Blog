package com.blog.Controllers;

import com.blog.Controllers.Abstract.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/post/")
public class PostController extends BaseController {

    @GetMapping("add")
    @ResponseBody
    public ResponseEntity<Void> add() {
        return ResponseEntity.ok().build();
    }


}