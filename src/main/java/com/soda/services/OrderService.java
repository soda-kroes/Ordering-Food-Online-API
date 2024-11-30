package com.soda.services;

import com.soda.model.Order;
import com.soda.model.User;
import com.soda.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest order, User user) throws Exception;
    Order updateOrder(Long orderId, String orderStatus) throws Exception;
    void cancelOrder(Long orderId) throws Exception;
    List<Order> getUserOrders(Long userId);
    List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus);
    Order findOrderById(Long orderId) throws Exception;
}

