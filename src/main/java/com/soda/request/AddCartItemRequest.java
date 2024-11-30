package com.soda.request;

import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {
    private Long foodId;
    private int quantity;
    private long price;
    private List<String> ingredients;
}
