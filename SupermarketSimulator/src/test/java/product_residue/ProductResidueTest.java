package product_residue;

import org.junit.Test;
import product.Product;
import product.TypesOfProducts;

import static org.junit.Assert.*;

public class ProductResidueTest {

  @Test
  public void getCountOfProduct() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, 4.4f);
    ProductResidue productResidue = new ProductResidue(product, 30);
    float actual = productResidue.GetCountOfProduct();
    float expected = 30;
    assertEquals(actual, expected, 0.0);
  }

  @Test
  public void getTypeOfProduct() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, 4.4f);
    ProductResidue productResidue = new ProductResidue(product, 30);
    String actual = productResidue.GetTypeOfProduct().GetName();
    String expected = "milk";
    assertEquals(actual, expected);
  }

  @Test
  public void substractCountOfProduct() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, 4.4f);
    ProductResidue productResidue = new ProductResidue(product, 30);
    productResidue.SubstractCountOfProduct(15);
    float actual = productResidue.GetCountOfProduct();
    float expected = 15;
    assertEquals(actual, expected, 0.0);
    productResidue.SubstractCountOfProduct(100);
    actual = productResidue.GetCountOfProduct();
    expected = 15;
    assertEquals(actual, expected, 0.0);
  }
}