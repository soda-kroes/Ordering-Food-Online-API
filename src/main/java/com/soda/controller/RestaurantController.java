package com.soda.controller;

import com.soda.dto.RestaurantDto;
import com.soda.model.Restaurant;
import com.soda.model.User;
import com.soda.services.RestaurantService;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping("/search")
    public ResponseEntity<?> addRestaurant(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurants = restaurantService.searchRestaurants(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllRestaurants(
            @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<?> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto res = restaurantService.addToFavourites(id, user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
