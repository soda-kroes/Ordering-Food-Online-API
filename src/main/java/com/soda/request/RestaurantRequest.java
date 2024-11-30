package com.soda.request;

import com.soda.model.Address;
import com.soda.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
