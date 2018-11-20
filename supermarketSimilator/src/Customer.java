import javafx.util.Pair;
import jdk.jfr.Category;

import java.util.Map;

enum TypesOfPeople {
  Child, Adult, Retired
}

enum ResultAddedToBasket{
  ProhibitedGoods, LackOfMoney, SuccesfullyAdded
}

public class Customer {
  private TypesOfPeople m_category;
  private float m_cash;
  private float m_bonuses;
  private float m_cashInCreditCard;
  private PaymentMethod m_methodOfPay;
  private Basket m_basket = new Basket();

  public PaymentMethod GetMethodOfPay(){
    return m_methodOfPay;
  }

  public Customer(TypesOfPeople category, float cash, float bonuses, float cashInCreditCard, PaymentMethod methodOfPay) {
    m_category = category;
    m_cash = cash;
    m_bonuses = bonuses;
    m_cashInCreditCard = cashInCreditCard;
    m_methodOfPay = methodOfPay;
  }

  public ResultAddedToBasket AddProductToBasket(float count, Product product) {
    if (m_category == TypesOfPeople.Child && product.GetCategory() == TypesOfProducts.ForAdult)
      return ResultAddedToBasket.ProhibitedGoods;

    float cash = 0;
    if (m_methodOfPay.getMethod() == Methods.WithBonuses)
      cash = m_bonuses;
    else if (m_methodOfPay.getMethod() == Methods.WithCash)
      cash = m_cash;
    else if (m_methodOfPay.getMethod() == Methods.WithCreditCard)
      cash = m_cashInCreditCard;

    float sumOfShopping = CalculateSumOfShopping();

    if (m_category == TypesOfPeople.Retired)
      sumOfShopping += count * (product.GetUnitPrice() * (100- product.GetDiscount().GetPercent()));

    if (sumOfShopping <= cash)
      m_basket.PutInTheBasket(count, product);
    else
      return ResultAddedToBasket.LackOfMoney;

    return ResultAddedToBasket.SuccesfullyAdded;
  }

  private float CalculateSumOfShopping() {
    float sum = 0;
    for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet()) {
      if (m_category == TypesOfPeople.Retired)
        sum += myPair.getValue().GetCountOfProduct() * (myPair.getValue().GetTypeOfProduct().GetUnitPrice() *
                (100 - myPair.getValue().GetTypeOfProduct().GetDiscount().GetPercent()));
      else
        sum += myPair.getValue().GetCountOfProduct() * myPair.getValue().GetTypeOfProduct().GetUnitPrice();
    }

    return sum;
  }

  public boolean CashTaken(float count) {
    if (count < 0)
      return false;
    if (m_cash >= count) {
      m_cash -= count;
      return true;
    } else
      return false;
  }

  public boolean BonusesTaken(float count) {
    if (count < 0)
      return false;
    if (m_bonuses >= count) {
      m_bonuses -= count;
      return true;
    } else
      return false;
  }

  public boolean CashFromCreditCardTaken(float count) {
    if (count < 0)
      return false;
    if (m_cashInCreditCard >= count) {
      m_cashInCreditCard -= count;
      return true;
    } else
      return false;
  }

  public void AccruedBonuses(float count) {
    if (count >= 0)
      m_bonuses += count;
  }

  public TypesOfPeople GetCategory(){
    return m_category;
  }
}
