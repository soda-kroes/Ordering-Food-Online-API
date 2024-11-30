package com.soda.controller;

import com.soda.model.Order;
import com.soda.model.User;
import com.soda.request.OrderRequest;
import com.soda.response.PaymentResponse;
import com.soda.services.OrderService;
import com.soda.services.PaymentService;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/orders")
    public ResponseEntity<? > createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req, user);
//        PaymentResponse response = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> userOrders = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }
}
