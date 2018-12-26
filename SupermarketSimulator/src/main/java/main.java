import customer.Customer;
import supermarket.Supermarket;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static functions.FunctionsForSupermarket.*;


public class Main {
    private static final int probability = 100000;

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
        long finishTime = startTime + supermarketVirginia.GetTimeToWork();
        supermarketVirginia.ToOpen();
        System.out.println("[8:00:00]Supermarket is opened");
        Customer customer;

        while (System.currentTimeMillis() <= finishTime) {
            if (queueWithCustomers.size() != 0)
                ServeCustomer(startTime, supermarketVirginia, queueWithCustomers);

            if (myRandom.nextInt(probability) == 0) {
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

        while (queueWithCustomers.size() != 0)
            ServeCustomer(startTime, supermarketVirginia, queueWithCustomers);

        PrintTime(startTime);
        System.out.println("Supermarket was closed");
        PrintTime(startTime);
        System.out.println("Report:");
        supermarketVirginia.cashdesk.PrintReport();
    }
}