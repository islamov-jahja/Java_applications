package src.bill;

import static org.junit.Assert.*;

public class BillTest {

  @org.junit.Test
  public void getBill() {
    Bill bill = new Bill();
    float actual = bill.GetBill();
    float expected = 0;
    assertEquals(actual, expected, 0.0);
  }

  @org.junit.Test
  public void addToBill() {
    Bill bill = new Bill();
    bill.AddToBill(5);
    float actual = bill.GetBill();
    float expected = 5;
    assertEquals(actual, expected, 0.0);
  }

  @org.junit.Test
  public void deductFromBill() {
    Bill bill = new Bill();
    bill.AddToBill(5);
    bill.DeductFromBill(3);
    float actual = bill.GetBill();
    float expected = 2;
    assertEquals(actual, expected, 0.0);
  }
}