import javafx.util.Pair;

import java.util.Collections;
import java.util.Map;

public class Supermarket {
  private boolean m_isOpen;
  private float m_timeToWork;
  public Map<String, ProductResidue> m_rangeOfGoods;
  public Cashdesk cashdesk = new Cashdesk();

  public void ToOpen() {
    m_isOpen = true;
  }

  public void ToClose() {
    m_isOpen = false;
  }

  public Supermarket(float timeToWork){
    m_isOpen = false;
    m_timeToWork = timeToWork;
  }

  public Supermarket(float timeToWork, Map<String, ProductResidue> rangeOfGoods){
    m_isOpen = false;
    m_timeToWork = timeToWork;
    m_rangeOfGoods = rangeOfGoods;
  }

  boolean ProductTaken(String nameOfProduct, int count){
    if (m_rangeOfGoods.containsKey(nameOfProduct)){
      if (count <= m_rangeOfGoods.get(nameOfProduct).GetCountOfProduct())
      {
        ProductResidue productData =  m_rangeOfGoods.get(nameOfProduct);
        productData.SubstractCountOfProduct(count);
        m_rangeOfGoods.put(nameOfProduct, productData);
        return true;
      }else
        return false;
    }else
      return false;
  }
}
