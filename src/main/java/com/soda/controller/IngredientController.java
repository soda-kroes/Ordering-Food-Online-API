package com.soda.controller;

import com.soda.model.IngredientCategory;
import com.soda.model.IngredientItem;
import com.soda.request.IngredientCategoryRequest;
import com.soda.request.IngredientRequest;
import com.soda.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest request) throws Exception {
        IngredientCategory item = ingredientsService.createIngredientCategory(request.getName(),request.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientItem> createIngredientItems(@RequestBody IngredientRequest request) throws Exception {
        IngredientItem ingredientItem = ingredientsService.createIngredientItem(request.getName(),request.getRestaurantId(),  request.getCategoryId());
        return new ResponseEntity<>(ingredientItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientItem> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientItem> restaurantIngredientItems = ingredientsService.findRestaurantIngredientItems(id);
        return new ResponseEntity<>(restaurantIngredientItems, HttpStatus.OK);
    }

    @PutMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategory> item = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
