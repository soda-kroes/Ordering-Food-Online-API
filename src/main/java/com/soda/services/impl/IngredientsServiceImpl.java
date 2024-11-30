package com.soda.services.impl;

import com.soda.model.IngredientCategory;
import com.soda.model.IngredientItem;
import com.soda.model.Restaurant;
import com.soda.repository.IngredientCategoryRepository;
import com.soda.repository.IngredientItemRepository;
import com.soda.services.IngredientsService;
import com.soda.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }else{
            throw new Exception("Ingredient category not found.");
        }
    }

    @Override
    public List<IngredientCategory> findAllIngredientCategories() {
        return List.of();
    }

    @Override
    public IngredientItem createIngredientItem(String name, Long restaurantId, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setName(name);
        ingredientItem.setRestaurant(restaurant);
        ingredientItem.setCategory(category);
        IngredientItem ingredient = ingredientItemRepository.save(ingredientItem);
        category.getIngredients().add(ingredient);

        return ingredient;
    }


    @Override
    public List<IngredientItem> findRestaurantIngredientItems(Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
        Optional<IngredientItem> optionalIngredientItem = ingredientItemRepository.findById(id);
        if (optionalIngredientItem.isEmpty()){
            throw new Exception("Ingredient not found.");
        }
        IngredientItem ingredientItem = optionalIngredientItem.get();
        ingredientItem.setStock(!ingredientItem.isStock());
        return ingredientItemRepository.save(ingredientItem);
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }
}
