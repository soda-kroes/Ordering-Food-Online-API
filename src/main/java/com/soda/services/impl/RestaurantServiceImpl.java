package com.soda.services.impl;

import com.soda.dto.RestaurantDto;
import com.soda.model.Address;
import com.soda.model.Restaurant;
import com.soda.model.User;
import com.soda.repository.AddressRepository;
import com.soda.repository.RestaurantRepository;
import com.soda.repository.UserRepository;
import com.soda.request.RestaurantRequest;
import com.soda.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(RestaurantRequest request, User user) {
        Address address = addressRepository.save(request.getAddress());
        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, RestaurantRequest request) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(restaurant.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(restaurant.getDescription());
        }
        if (restaurant.getName() != null) {
            restaurant.setName(restaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> otp = restaurantRepository.findById(restaurantId);
        if (otp.isEmpty()) {
            throw new Exception("Restarant not found with id " + restaurantId);
        }
        return otp.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found with owner id = " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourites (Long restaurantId, User user) {
        try {

            Restaurant restaurant = findRestaurantById(restaurantId);

            System.out.println("-----------");
            System.out.println(restaurant);
            System.out.println("-----------");

            RestaurantDto restaurantDto = new RestaurantDto();

            restaurantDto.setDescription(restaurant.getDescription());
//            restaurantDto.setImages(restaurant.getImages());
            restaurantDto.setTitle(restaurant.getName());
            restaurantDto.setId(restaurantId);
            restaurantDto.setImages(restaurant.getImages());

            boolean isFavorited = false;
            List<RestaurantDto> favorites = user.getFavorites();

            for (RestaurantDto favorite : favorites) {
                if (favorite.getId().equals(restaurantId)) {
                    isFavorited = true;
                    break;
                }
            }

            if (isFavorited) {
                favorites.removeIf(favoriteDto -> favoriteDto.getId().equals(restaurantId));
            } else {
                favorites.add(restaurantDto);
            }
            userRepository.save(user);
            return restaurantDto;
        } catch (Exception e) {
            // Handle any specific exceptions related to serialization here
            e.printStackTrace(); // For debugging purposes
            return null; // Or handle the exception in an appropriate way
        }
    }


    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
