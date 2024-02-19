package com.github.javatests.payments;

public interface PaymentGateway {

  PaymentResponse requestPayment(PaymentRequest paymentRequest);

}
