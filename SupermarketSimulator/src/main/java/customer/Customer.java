package customer;

import basket.Basket;
import payment_method.Methods;
import payment_method.PaymentMethod;
import product.Product;
import product.TypesOfProducts;
import product_residue.ProductResidue;

import java.math.BigDecimal;
import java.util.Map;

public class Customer {
    private final TypesOfPeople m_category;
    private BigDecimal m_cash;
    private BigDecimal  m_bonuses;
    private BigDecimal m_cashInCreditCard;
    private final PaymentMethod m_methodOfPay;
    private final Basket m_basket = new Basket();
    private final int m_numberOfCustomer;

    public Basket GetBasket() {
        Basket tmp = new Basket();
        for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet())
            tmp.PutInTheBasket(myPair.getValue().GetCountOfProduct(), myPair.getValue().GetTypeOfProduct());

        return tmp;
    }

    public int GetNumberOfCustomer() {
        return m_numberOfCustomer;
    }

    public PaymentMethod GetMethodOfPay() {
        return m_methodOfPay;
    }

    public Customer(TypesOfPeople category, BigDecimal cash, BigDecimal bonuses, BigDecimal cashInCreditCard, PaymentMethod methodOfPay, int numberOfCustomer) {
        m_category = category;
        m_cash = cash;
        m_bonuses = bonuses;
        m_cashInCreditCard = cashInCreditCard;
        m_methodOfPay = methodOfPay;
        m_numberOfCustomer = numberOfCustomer;
    }

    public ResultAddedToBasket AddProductToBasket(float count, Product product) {
        if (m_category == TypesOfPeople.Child && product.GetCategory() == TypesOfProducts.ForAdult)
            return ResultAddedToBasket.ProhibitedGoods;

        BigDecimal cash = BigDecimal.valueOf(0);
        if (m_methodOfPay.getMethod() == Methods.WithBonuses)
            cash = m_bonuses;
        else if (m_methodOfPay.getMethod() == Methods.WithCash)
            cash = m_cash;
        else if (m_methodOfPay.getMethod() == Methods.WithCreditCard)
            cash = m_cashInCreditCard;

        BigDecimal sumOfShopping = CalculateSumOfShopping();

        if (m_category == TypesOfPeople.Retired)
            sumOfShopping = sumOfShopping.add(BigDecimal.valueOf(count * (product.GetUnitPrice() * (100 - product.GetDiscount().GetPercent()))));
        else
            sumOfShopping = sumOfShopping.add(BigDecimal.valueOf(count * product.GetUnitPrice()));

        if (sumOfShopping.compareTo(cash) == -1 || sumOfShopping.compareTo(cash) == 0)
            m_basket.PutInTheBasket(count, product);
        else
            return ResultAddedToBasket.LackOfMoney;

        return ResultAddedToBasket.SuccesfullyAdded;
    }

    private BigDecimal CalculateSumOfShopping() {
        BigDecimal sum = new BigDecimal(0);
        for (Map.Entry<String, ProductResidue> myPair : m_basket.GetProducts().entrySet()) {
            if (m_category == TypesOfPeople.Retired)
                sum = sum.add(BigDecimal.valueOf(myPair.getValue().GetCountOfProduct() * (myPair.getValue().GetTypeOfProduct().GetUnitPrice() *
                        (100 - myPair.getValue().GetTypeOfProduct().GetDiscount().GetPercent()))));
            else
                sum = sum.add(BigDecimal.valueOf(myPair.getValue().GetCountOfProduct() * myPair.getValue().GetTypeOfProduct().GetUnitPrice()));
        }

        return sum;
    }

    public boolean CashTaken(BigDecimal count) {
        if (count.compareTo(BigDecimal.valueOf(0)) == -1)
            return false;
        if (m_cash.compareTo(count) != -1) {
            m_cash = m_cash.subtract(count);
            return true;
        } else
            return false;
    }

    public boolean BonusesTaken(BigDecimal count) {
        if (count.compareTo(BigDecimal.valueOf(0)) == -1)
            return false;
        if (m_bonuses.compareTo(count) != -1) {
            m_bonuses = m_bonuses.subtract(count);
            return true;
        } else
            return false;
    }

    public boolean CashFromCreditCardTaken(BigDecimal count) {
        if (count.compareTo(BigDecimal.valueOf(0)) == -1)
            return false;
        if (m_cashInCreditCard.compareTo(count) != -1) {
            m_cashInCreditCard = m_cashInCreditCard.subtract(count);
            return true;
        } else
            return false;
    }

    public void AccruedBonuses(BigDecimal count) {
        if (count.compareTo(BigDecimal.valueOf(0)) != -1)
            m_bonuses = m_bonuses.add(count);
    }

    public TypesOfPeople GetCategory() {
        return m_category;
    }
}
