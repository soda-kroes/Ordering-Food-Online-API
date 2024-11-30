package com.soda.repository;

import com.soda.model.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientItemRepository extends JpaRepository<IngredientItem, Long> {
    List<IngredientItem> findByRestaurantId(Long id);
}
