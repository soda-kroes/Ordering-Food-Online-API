package com.soda.controller;

import com.soda.model.Category;
import com.soda.model.User;
import com.soda.services.CategoryService;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category, @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Category createCategory = categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }
 
    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<?> getRestaurantCategory(@PathVariable Long id, @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        List<Category> categoryList = categoryService.findCategoryByRestaurantId(id);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);

    }
}
