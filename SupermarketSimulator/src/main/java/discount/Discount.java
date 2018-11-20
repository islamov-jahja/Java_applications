package discount;

public class Discount {
  private float m_percent;

  public Discount(float percent){
    if(percent >= 0 && percent <= 100)
      m_percent = percent;
  }
  public float GetPercent(){
    return m_percent;
  }
}
