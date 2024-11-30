package com.soda.services;

import com.soda.model.IngredientCategory;
import com.soda.model.IngredientItem;

import java.util.List;

public interface IngredientsService {
    IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;
    IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    List<IngredientCategory> findAllIngredientCategories();
    IngredientItem createIngredientItem(String name, Long restaurantId,Long categoryId) throws Exception;
    List<IngredientItem> findRestaurantIngredientItems(Long restaurantId) throws Exception;
    IngredientItem updateStock(Long id) throws Exception;
    List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;
}
