import customer.Customer;
import customer.TypesOfPeople;
import payment_method.Methods;
import payment_method.PaymentMethod;
import product.Product;
import product.TypesOfProducts;
import product.UnitOfProduct;
import product_residue.ProductResidue;
import supermarket.Supermarket;

import java.util.*;

import static functions.FunctionsForSupermarket.*;

public class main {
  public static void main(String args[]) {
    Random myRandom = new Random(System.currentTimeMillis());
    Supermarket supermarketVirginia = new Supermarket(1000);
    List<String> productNameList = new LinkedList<String>();
    List<Customer> queueWithCustomers = new LinkedList<Customer>();
    int numberOfCustomer = 1;

    AddProductsToTheSupermarket(supermarketVirginia);
    AddProductsNameToTheList(productNameList, supermarketVirginia);
    PrintInfoAboutProducts(supermarketVirginia);
    long startTime = System.currentTimeMillis();
    Date now = new Date();
    long finishTime = startTime + supermarketVirginia.GetTimeToWork();
    supermarketVirginia.ToOpen();
    System.out.println("[8:00:00]Supermarket is opened");
    Customer customer;

    while (System.currentTimeMillis() <= finishTime) {
      if (queueWithCustomers.size() != 0) {
        Customer customerInCashDesk = ((LinkedList<Customer>) queueWithCustomers).removeFirst();
        ToInitializeValuesOfCashdesk(supermarketVirginia, customerInCashDesk);

        if (supermarketVirginia.cashdesk.BillWasPaid()) {
          TakeProductsFromMarket(supermarketVirginia, customerInCashDesk);
        }

        boolean bonusesAccrued = supermarketVirginia.cashdesk.BonusesAccrued();
        PrintInfoAboutSoldProducts(bonusesAccrued, startTime, customerInCashDesk, supermarketVirginia);
        PrintTime(startTime);
        PrintInfoAboutPayment(customerInCashDesk, supermarketVirginia.cashdesk.GetBill());
        supermarketVirginia.cashdesk.CleanBill();
      }

      if (myRandom.nextInt(1000000) == 0) {
        customer = GetRandomCustomer(numberOfCustomer);
        PrintTime(startTime);
        System.out.println("Customer" + numberOfCustomer + " arrived");
        numberOfCustomer++;
        ToFillTheBasket(customer, supermarketVirginia, productNameList);

        if (customer.GetBasket().GetProducts().size() != 0) {
          queueWithCustomers.add(customer);
          PrintTime(startTime);
          PrintListOfProductsInBasketCustomer(customer);
        } else {
          System.out.println("Customer" + customer.GetNumberOfCustomer() + " left");
        }
      }
    }

    if (queueWithCustomers.size() != 0) {
      PrintTime(startTime);
      System.out.println("Supermarket was closed for other buyers");
    }

    while (queueWithCustomers.size() != 0) {
      Customer customerInCashDesk = ((LinkedList<Customer>) queueWithCustomers).removeFirst();
      ToInitializeValuesOfCashdesk(supermarketVirginia, customerInCashDesk);

      if (supermarketVirginia.cashdesk.BillWasPaid()) {
        TakeProductsFromMarket(supermarketVirginia, customerInCashDesk);
      }

      boolean bonusesAccrued = supermarketVirginia.cashdesk.BonusesAccrued();
      PrintInfoAboutSoldProducts(bonusesAccrued, startTime, customerInCashDesk, supermarketVirginia);
      PrintTime(startTime);
      PrintInfoAboutPayment(customerInCashDesk, supermarketVirginia.cashdesk.GetBill());
      supermarketVirginia.cashdesk.CleanBill();
    }

    PrintTime(startTime);
    System.out.println("Supermarket was closed");
    PrintTime(startTime);
    System.out.println("Report:");
    supermarketVirginia.cashdesk.PrintReport();
  }
}