package com.soda.services.impl;

import com.soda.model.*;
import com.soda.repository.AddressRepository;
import com.soda.repository.OrderItemRepository;
import com.soda.repository.OrderRepository;
import com.soda.repository.UserRepository;
import com.soda.request.OrderRequest;
import com.soda.services.CartService;
import com.soda.services.OrderService;
import com.soda.services.RestaurantService;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippAddress = order.getDeliveryAddress();
        Address saveAddress = addressRepository.save(shippAddress);
        if (!user.getAddresses().contains(saveAddress)) {
            user.getAddresses().add(saveAddress);
            userRepository.save(user);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
        Order createOrder = new Order();

        createOrder.setCustomer(user);
        createOrder.setDeliveryAddress(saveAddress);
        createOrder.setRestaurant(restaurant);
        createOrder.setCreateAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setRestaurant(restaurant);
        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem saveOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(saveOrderItem);
        }
        Long totalPrice = cartService.calculateCartTotals(cart);
        createOrder.setItems(orderItems);
        createOrder.setTotalPrice(totalPrice);
        Order saveOrder = orderRepository.save(createOrder);
        restaurant.getOrders().add(saveOrder);

        return createOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);

    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        orderRepository.findByCustomerId(userId);
        return List.of();
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        if (orderStatus != null) {
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()){
            throw new Exception("order not found.");
        }
        return optionalOrder.get();
    }
}
