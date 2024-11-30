package com.soda.services.impl;

import com.soda.model.Category;
import com.soda.model.Restaurant;
import com.soda.repository.CategoryRepository;
import com.soda.services.CategoryService;
import com.soda.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;




    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        categoryRepository.save(category);
        return category;
    }


    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> restaurantId = categoryRepository.findById(id);
        if(restaurantId.isEmpty()){
            throw new Exception("Category Id not found.");
        }else{
            return restaurantId.get();
        }

    }
}
