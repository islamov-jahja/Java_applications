package product_residue;

import product.Product;

public class ProductResidue {
  private float m_countOfProduct = 0;
  private Product m_product;

  public ProductResidue(Product product){
    m_product = product;
    m_countOfProduct = 0;
  }

  public ProductResidue(Product product, float count){
    m_product = product;
    m_countOfProduct = count;
  }

  public float GetCountOfProduct() {
    return m_countOfProduct;
  }

  public Product GetTypeOfProduct() {
    return m_product;
  }

  public void SubstractCountOfProduct(float count) {
    if (count <= m_countOfProduct)
      m_countOfProduct -= count;
  }
}
