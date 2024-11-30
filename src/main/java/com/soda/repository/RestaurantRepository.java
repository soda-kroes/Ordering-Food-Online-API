package com.soda.repository;

import com.soda.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE  lower(concat('%', :query , '%')) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%', :query , '%')) ")
    public List<Restaurant> findBySearchQuery(String query);

//    Optional<Restaurant> findById(Long restaurantId);
    Restaurant findByOwnerId (long userId);
}
