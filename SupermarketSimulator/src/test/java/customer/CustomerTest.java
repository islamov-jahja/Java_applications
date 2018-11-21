package customer;

import org.junit.Test;
import payment_method.Methods;
import payment_method.PaymentMethod;
import product.Product;
import product.TypesOfProducts;
import product.UnitOfProduct;

import static org.junit.Assert.*;

public class CustomerTest {

  @Test
  public void getMethodOfPay() {
    PaymentMethod method = new PaymentMethod(Methods.WithBonuses);
    Customer customer = new Customer(TypesOfPeople.Adult, 0, 100, 0, method, 0);
    Methods except = Methods.WithBonuses;
    Methods actual = customer.GetMethodOfPay().getMethod();
    assertEquals(except, actual);
  }

  @Test
  public void addProductToBasket() {
    PaymentMethod method = new PaymentMethod(Methods.WithBonuses);
    Customer customer = new Customer(TypesOfPeople.Child, 0, 100, 0, method, 0);
    Product product = new Product("сигарета", TypesOfProducts.ForAdult, 85, 0, UnitOfProduct.byThePriece);
    ResultAddedToBasket exceptResult = customer.AddProductToBasket(1, product);
    ResultAddedToBasket actualResult = ResultAddedToBasket.ProhibitedGoods;
    assertEquals(exceptResult, actualResult);
    Customer customer2 = new Customer(TypesOfPeople.Adult, 0, 100, 0, method, 0);
    exceptResult = customer2.AddProductToBasket(1, product);
    actualResult = ResultAddedToBasket.SuccesfullyAdded;
    assertEquals(exceptResult, actualResult);
    exceptResult = customer2.AddProductToBasket(1, product);
    actualResult = ResultAddedToBasket.LackOfMoney;
    assertEquals(exceptResult, actualResult);
    PaymentMethod method2 = new PaymentMethod(Methods.WithCash);
    Customer customer3 = new Customer(TypesOfPeople.Retired, 100, 0, 0, method, 0);
    exceptResult = customer3.AddProductToBasket(1, product);
    actualResult = customer3.AddProductToBasket(1, product);
    assertEquals(exceptResult, actualResult);
    PaymentMethod method3 = new PaymentMethod(Methods.WithCreditCard);
    Customer customer4 = new Customer(TypesOfPeople.Retired, 0, 0, 100, method, 0);
    exceptResult = customer3.AddProductToBasket(1, product);
    actualResult = customer3.AddProductToBasket(1, product);
    assertEquals(exceptResult, actualResult);
  }

  @Test
  public void cashTaken() {
  }

  @Test
  public void bonusesTaken() {
  }

  @Test
  public void cashFromCreditCardTaken() {
  }

  @Test
  public void accruedBonuses() {
  }

  @Test
  public void getCategory() {
  }
}