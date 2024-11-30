package com.soda.services;

import com.soda.model.Category;
import com.soda.model.Food;
import com.soda.model.Restaurant;
import com.soda.request.FoodRequest;

import java.util.List;

public interface FoodService {
    Food createFood(FoodRequest request, Category category, Restaurant restaurant);
    void deleteFood(Long foodId);
    List<Food> getRestaurantFoods(Long restaurantId,
                                  boolean isVegitarain,
                                  boolean isNonveg,
                                  boolean isSeasonal,
                                  String foodCategory
    );
    List<Food> searchFood(String keyword);
    Food findFoodById(Long id);
    Food updateAvailability(Long foodId);


}
