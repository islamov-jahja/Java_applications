package payment_method;

import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentMethodTest {

  @Test
  public void getMethod() {
    Methods method = Methods.WithBonuses;
    PaymentMethod methodC = new PaymentMethod(method);
    assertEquals(methodC.getMethod(), method);
  }
}