package com.github.javatests.payments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

  private PaymentGateway paymentGateway;
  private PaymentProcessor paymentProcessor;

  @Before //Se utiliza before en todos los metodos que queremos que se ejecuten antes de cada test.
  public void setup() {
    paymentGateway = Mockito.mock(PaymentGateway.class);
    paymentProcessor = new PaymentProcessor(paymentGateway);
  }

  @Test
  public void payment_is_correct() {
    //Preparacion de los objetos
    Mockito.when(paymentGateway.requestPayment(Mockito.any()))
        .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK));
    //Ejecucion del metodo a testear
    boolean result = paymentProcessor.makePayment(1000);
    //Comprobacion del resultado
    assertTrue(result);
  }

  @Test
  public void payment_is_wrong() {
    //Preparacion de los objetos
    Mockito.when(paymentGateway.requestPayment(Mockito.any()))
        .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR));
    //Llamada al metodo a probar (misma forma que arriba pero en la misma linea
    assertFalse(paymentProcessor.makePayment(1000));
  }
}