package supermarket;

import cash_desk.Cashdesk;
import product.Product;
import product_residue.ProductResidue;

import java.util.HashMap;
import java.util.Map;

public class Supermarket {
  private boolean m_isOpen;
  private long m_timeToWork;
  private Map<String, ProductResidue> m_rangeOfGoods = new HashMap<String, ProductResidue>();
  public Cashdesk cashdesk = new Cashdesk();

  public void ToOpen() {
    m_isOpen = true;
  }

  public void ToClose() {
    m_isOpen = false;
  }

  public Supermarket(long timeToWork) {
    m_isOpen = false;
    m_timeToWork = timeToWork;
  }

  public void AddProductToSupermarket(float count, Product product) {
    ProductResidue productResidue = new ProductResidue(product, count);
    m_rangeOfGoods.put(productResidue.GetTypeOfProduct().GetName(), productResidue);
  }

  public long GetTimeToWork() {
    return m_timeToWork;
  }

  public boolean ProductTaken(String nameOfProduct, float count) {
    if (m_rangeOfGoods.containsKey(nameOfProduct)) {
      if (count <= m_rangeOfGoods.get(nameOfProduct).GetCountOfProduct()) {
        ProductResidue productData = m_rangeOfGoods.get(nameOfProduct);
        productData.SubstractCountOfProduct(count);
        m_rangeOfGoods.put(nameOfProduct, productData);
        return true;
      } else
        return false;
    } else
      return false;
  }

  public Map<String, ProductResidue> GetProducts()
  {
    Map<String, ProductResidue> tmp = new HashMap<String, ProductResidue>(m_rangeOfGoods);
    return tmp;
  }
}
