package com.soda.controller;

import com.soda.model.Food;
import com.soda.model.User;
import com.soda.services.FoodService;
import com.soda.services.RestaurantService;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<?> searchFood
            (@RequestBody String name,
             @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getRestaurant(
            @RequestParam(required = false) boolean vegetarian,
            @RequestParam(required = false) boolean seasonal,
            @RequestParam(required = false) boolean nonveg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantFoods(restaurantId, vegetarian, seasonal, nonveg, food_category);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }
}
