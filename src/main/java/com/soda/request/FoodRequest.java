package com.soda.request;

import com.soda.model.Category;
import com.soda.model.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class FoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetrain;
    private boolean seasional;
    private List<IngredientItem> ingredients;
}
