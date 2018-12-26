package src.bill;

import java.math.BigDecimal;

public class Bill {
  private BigDecimal Sum = new BigDecimal(0);

  public BigDecimal GetBill() {
    return Sum;
  }

  public void AddToBill(BigDecimal count) {
    Sum = Sum.add(count);
  }

  public void DeductFromBill(BigDecimal count) {
    if (Sum.compareTo(count) != -1)
      Sum = Sum.subtract(count);
  }

  public void clean(){
    Sum = BigDecimal.valueOf(0);
  }
}
