package product;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

  @Test
  public void getName() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, UnitOfProduct.byThePriece);
    String actual = product.GetName();
    String expected = "milk";
    assertEquals(actual, expected);
  }

  @Test
  public void getUnitPrice() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, UnitOfProduct.byThePriece);
    float actual = product.GetUnitPrice();
    float expected = 35;
    assertEquals(actual, expected, 0.0);
  }

  @Test
  public void getCategory() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, UnitOfProduct.byThePriece);
    TypesOfProducts actual = product.GetCategory();
    TypesOfProducts expected = TypesOfProducts.ForAll;
    assertEquals(actual, expected);
  }

  @Test
  public void getDiscount() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, 4.4f, UnitOfProduct.byThePriece);
    float actual = product.GetDiscount().GetPercent();
    float expected = 4.4f;
    assertEquals(actual, expected, 0.0f);
  }

  @Test
  public void getBonuses() {
    Product product = new Product("milk", TypesOfProducts.ForAll, 35, 4.4f, UnitOfProduct.byThePriece, 30);
    float actual = product.GetBonuses();
    float expected = 30;
    assertEquals(actual, expected, 0.0f);
  }
}