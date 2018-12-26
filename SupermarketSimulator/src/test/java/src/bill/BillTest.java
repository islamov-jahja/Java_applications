package src.bill;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BillTest {

  @org.junit.Test
  public void getBill() {
    Bill bill = new Bill();
    BigDecimal actual = bill.GetBill();
    BigDecimal expected = BigDecimal.valueOf(0);
    assertEquals(actual, expected);
  }

  @org.junit.Test
  public void addToBill() {
    Bill bill = new Bill();
    bill.AddToBill(BigDecimal.valueOf(5));
    BigDecimal actual = bill.GetBill();
    BigDecimal expected = BigDecimal.valueOf(5);
    assertEquals(actual, expected);
  }

  @org.junit.Test
  public void deductFromBill() {
    Bill bill = new Bill();
    bill.AddToBill(BigDecimal.valueOf(5));
    bill.DeductFromBill(BigDecimal.valueOf(3));
    BigDecimal actual = bill.GetBill();
    BigDecimal expected = BigDecimal.valueOf(2);
    assertEquals(actual, expected);
  }
}