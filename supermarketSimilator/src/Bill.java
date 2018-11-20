public class Bill {
  private float Sum = 0;

  public float GetBill() {
    return Sum;
  }

  public void AddToBill(float count) {
    Sum += count;
  }

  public void DeductFromBill(float count) {
    if (Sum >= count)
      Sum -= count;
  }
}
