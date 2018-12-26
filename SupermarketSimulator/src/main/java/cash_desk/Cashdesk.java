package cash_desk;

import basket.Basket;
import customer.Customer;
import customer.TypesOfPeople;
import payment_method.Methods;
import product_residue.ProductResidue;
import report.Report;
import src.bill.Bill;

import java.math.BigDecimal;
import java.util.Map;

public class Cashdesk {
    private final Bill m_bill = new Bill();
    private Basket m_basket;
    private Customer m_customer;
    private final Report m_report = new Report();

    public void SetBasket(Basket basket) {
        m_basket = basket;
    }

    public void SetCustomer(Customer customer) {
        m_customer = customer;
    }

    public void PrintReport() {
        m_report.PrintReport();
    }

    private float CalculateBill() {
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

    public BigDecimal GetBill() {
        if (m_bill.GetBill().compareTo(BigDecimal.valueOf(0)) != 0)
            m_bill.DeductFromBill(BigDecimal.valueOf(CalculateBill()));
        m_bill.AddToBill(BigDecimal.valueOf(CalculateBill()));
        return m_bill.GetBill();
    }

    public void CleanBill() {
        m_bill.clean();
    }

    public boolean BillWasPaid() {
        if (m_customer.GetMethodOfPay().getMethod() == Methods.WithCash)
            if (!m_customer.CashTaken(GetBill()))
                return false;
            else if (m_customer.GetMethodOfPay().getMethod() == Methods.WithBonuses)
                if (!m_customer.BonusesTaken(GetBill()))
                    return false;
                else if (m_customer.GetMethodOfPay().getMethod() == Methods.WithCreditCard)
                    if (!m_customer.CashFromCreditCardTaken(GetBill()))
                        return false;

        for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet())
            m_report.WriteInTheReport(myPair.getValue().GetCountOfProduct(), myPair.getValue().GetTypeOfProduct());
        return true;
    }

    private BigDecimal CalculateBonuses() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet())
            sum = sum.add(BigDecimal.valueOf(myPair.getValue().GetTypeOfProduct().GetBonuses()));

        return sum;
    }

    public boolean BonusesAccrued() {
        if (CalculateBonuses().compareTo(BigDecimal.valueOf(0)) != -1)
            m_customer.AccruedBonuses(CalculateBonuses());
        else
            return false;

        return true;
    }

    public BigDecimal GetCountOfBonuses() {
        return CalculateBonuses();
    }
}
