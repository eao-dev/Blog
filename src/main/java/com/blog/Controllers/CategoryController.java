package com.blog.Controllers;

import com.blog.Controllers.Abstract.BaseController;
import com.blog.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<String> readAll() {
        final String json = categoryService.getJsonListCategory();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> read(@PathVariable("id") long id) {
        final String json = categoryService.read(id);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}