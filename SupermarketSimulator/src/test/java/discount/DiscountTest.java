package discount;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountTest {

  @Test
  public void getPercent() {
    Discount discount = new Discount(4.4f);
    float actual = discount.GetPercent();
    float expected = 4.4f;
    assertEquals(actual, expected, 0.0);
    Discount discount1 = new Discount(101f);
    float actual1 = discount1.GetPercent();
    float expected1 = 0;
    assertEquals(actual1, expected1, 0.0);
  }
}