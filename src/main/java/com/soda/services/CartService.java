package com.soda.services;

import com.soda.model.Cart;
import com.soda.model.CartItem;
import com.soda.request.AddCartItemRequest;

public interface CartService {
    CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;
    CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;
    Cart removeItemFromCart(Long cartItemId,String jwt) throws Exception;
    Long calculateCartTotals(Cart cart) throws Exception;
    Cart findCartById(Long cartId) throws Exception;
    Cart findCartByUserId(Long userId) throws Exception;
    Cart clearCart (Long userId) throws Exception;

}
