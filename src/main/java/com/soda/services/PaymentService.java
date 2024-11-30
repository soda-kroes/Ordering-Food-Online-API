package com.soda.services;

import com.soda.model.Order;
import com.soda.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    PaymentResponse createPaymentLink(Order order) throws StripeException;

}
