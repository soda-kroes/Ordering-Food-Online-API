package com.soda.controller;

import com.soda.model.Order;
import com.soda.model.User;
import com.soda.services.OrderService;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<?> getOrderHistory(@PathVariable Long id,@RequestParam(required = false) String order_status, @RequestHeader("Authorization") String token) {
        User user = userService.findUserByJwtToken(token);
        List<Order> restaurantsOrder = orderService.getRestaurantsOrder(id, order_status);
        return new ResponseEntity<>(restaurantsOrder, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @PathVariable String orderStatus, @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Order order = orderService.updateOrder(id, orderStatus);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
