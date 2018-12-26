package customer;

import org.junit.Test;
import payment_method.Methods;
import payment_method.PaymentMethod;
import product.Product;
import product.TypesOfProducts;
import product.UnitOfProduct;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void getBasket() {
    }

    @Test
    public void getNumberOfCustomer() {
    }

    @Test
    public void getMethodOfPay() {
    }

    @Test
    public void addProductToBasket() {
        PaymentMethod method = new PaymentMethod(Methods.WithCash);
        Customer customerChild = new Customer(TypesOfPeople.Child, BigDecimal.valueOf(100), BigDecimal.valueOf(100), BigDecimal.valueOf(100), method, 0);
        Customer customerAdult = new Customer(TypesOfPeople.Adult, BigDecimal.valueOf(100), BigDecimal.valueOf(100), BigDecimal.valueOf(100), method, 0);
        Product product5 = new Product("paperos", TypesOfProducts.ForAdult, 85, 12, UnitOfProduct.byTheKilo, 9);
        customerChild.AddProductToBasket(1 ,product5);
        customerAdult.AddProductToBasket(1, product5);
        assertEquals(customerChild.GetBasket().GetProducts().size(), 0);
        assertEquals(customerAdult.GetBasket().GetProducts().size(), 1);
        Product product4 = new Product("cheese", TypesOfProducts.ForAll, 49.9f, 0, UnitOfProduct.byTheKilo, 5);
        customerChild.AddProductToBasket(1, product4);
        assertEquals(customerChild.GetBasket().GetProducts().size(), 1);
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