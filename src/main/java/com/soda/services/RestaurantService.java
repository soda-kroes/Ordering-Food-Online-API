package com.soda.services;

import com.soda.dto.RestaurantDto;
import com.soda.model.Restaurant;
import com.soda.model.User;
import com.soda.request.RestaurantRequest;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantRequest request, User user);

    Restaurant updateRestaurant(Long restaurantId, RestaurantRequest request) throws Exception;

    void deleteRestaurant(Long restaurantId) throws Exception;

    List<Restaurant> getAllRestaurants();

    List<Restaurant> searchRestaurants(String keyword);

    Restaurant findRestaurantById(Long id) throws Exception;

    Restaurant getRestaurantByUserId(Long userId) throws Exception;

    RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception;

    Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
