package com.soda.repository;

import com.soda.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {
    List<IngredientCategory> findByRestaurantId(Long restaurantId);
}
