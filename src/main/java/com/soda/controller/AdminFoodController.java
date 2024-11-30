package com.soda.controller;

import com.soda.model.Category;
import com.soda.model.Food;
import com.soda.model.Restaurant;
import com.soda.model.User;
import com.soda.request.FoodRequest;
import com.soda.response.MessageResponse;
import com.soda.services.CategoryService;
import com.soda.services.FoodService;
import com.soda.services.RestaurantService;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Food> createFood(@RequestBody FoodRequest request, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        // Check if the Category is already persisted or not
        Category category = categoryService.findCategoryById(2L);

        System.out.println("===================");
        System.out.println(category);
        System.out.println("===================");

        if (category == null) {
            throw new Exception("Category with id " + request.getCategory().getId() + " not found.");
        }

        Food food = foodService.createFood(request, category, restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Food deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateFoodAvailabilityStatus(@PathVariable long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailability(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
