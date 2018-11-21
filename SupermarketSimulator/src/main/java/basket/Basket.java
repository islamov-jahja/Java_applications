package basket;

import product.Product;
import product_residue.ProductResidue;

import java.util.HashMap;
import java.util.Map;

public class Basket {
  private Map<String, ProductResidue> m_products = new HashMap<String, ProductResidue>();

  public void PutInTheBasket(float count, Product product){
    ProductResidue residue = new ProductResidue(product, count);
    m_products.put(product.GetName(), residue);
  }

  public void EraseFromBasket(float count, Product product){
    m_products.remove(product.GetName());
  }

  public Map<String, ProductResidue> GetProducts(){
    Map<String, ProductResidue> tmp = new HashMap<String, ProductResidue>(m_products);
    return tmp;
  }
}
