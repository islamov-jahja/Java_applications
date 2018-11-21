package cash_desk;

import basket.Basket;
import customer.Customer;
import customer.TypesOfPeople;
import payment_method.Methods;
import product_residue.ProductResidue;
import report.Report;
import src.bill.Bill;

import java.util.Map;

public class Cashdesk {
  private Bill m_bill= new Bill();
  private Basket m_basket;
  private Customer m_customer;
  private Report m_report = new Report();

  public void SetBasket(Basket basket) {
    m_basket = basket;
  }

  public void SetCustomer(Customer customer) {
    m_customer = customer;
  }

  public void PrintReport(){
    m_report.PrintReport();
  }

  private float CalculateBill(){
    float sum = 0;
    for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet()) {
      if (m_customer.GetCategory() == TypesOfPeople.Retired)
        sum += myPair.getValue().GetCountOfProduct() * (myPair.getValue().GetTypeOfProduct().GetUnitPrice() *
                (100 - myPair.getValue().GetTypeOfProduct().GetDiscount().GetPercent()));
      else
        sum += myPair.getValue().GetCountOfProduct() * myPair.getValue().GetTypeOfProduct().GetUnitPrice();
    }

    return sum;
  }

  public float GetBill(){
    if (m_bill.GetBill() != 0)
      m_bill.DeductFromBill(CalculateBill());
    m_bill.AddToBill(CalculateBill());
    return m_bill.GetBill();
  }

  public void CleanBill(){
    m_bill.clean();
  }

  public boolean BillWasPaid(){
    if (m_customer.GetMethodOfPay().getMethod() == Methods.WithCash)
      m_customer.CashTaken(GetBill());
    else if(m_customer.GetMethodOfPay().getMethod() == Methods.WithBonuses)
      m_customer.BonusesTaken(GetBill());
    else if(m_customer.GetMethodOfPay().getMethod() == Methods.WithCreditCard)
      m_customer.CashFromCreditCardTaken(GetBill());

    for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet())
      m_report.WriteInTheReport(myPair.getValue().GetCountOfProduct(), myPair.getValue().GetTypeOfProduct());
    return true;
  }

  private float CalculateBonuses(){
    float sum = 0;
    for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet())
      sum += myPair.getValue().GetTypeOfProduct().GetBonuses();

    return sum;
  }

  public boolean BonusesAccrued(){
    if (CalculateBonuses() >= 0)
      m_customer.AccruedBonuses(CalculateBonuses());
    else
      return false;

    return true;
  }

  public float GetCountOfBonuses(){
    return CalculateBonuses();
  }
}
