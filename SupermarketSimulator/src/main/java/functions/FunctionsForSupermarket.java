package functions;

import customer.Customer;
import customer.TypesOfPeople;
import payment_method.Methods;
import payment_method.PaymentMethod;
import product.Product;
import product.TypesOfProducts;
import product.UnitOfProduct;
import product_residue.ProductResidue;
import supermarket.Supermarket;

import java.math.BigDecimal;
import java.util.*;

public class FunctionsForSupermarket {

    public static void PrintInfoAboutPayment(Customer customer, BigDecimal sum) {
        if (customer.GetMethodOfPay().getMethod() == Methods.WithCash)
            PrintMessage("cash", sum);
        else if (customer.GetMethodOfPay().getMethod() == Methods.WithCreditCard)
            PrintMessage("credit card", sum);
        else
            PrintMessage("bonuses", sum);
    }

    private static void PrintMessage(String message, BigDecimal sum) {
        System.out.println("Customer paid " + sum + " by " + message);
    }

    public static void TakeProductsFromMarket(Supermarket supermarket, Customer customer) {
        for (Map.Entry<String, ProductResidue> myPair : customer.GetBasket().GetProducts().entrySet())
            supermarket.ProductTaken(myPair.getKey(), myPair.getValue().GetCountOfProduct());
    }

    public static void PrintInfoAboutProducts(Supermarket supermarket) {
        System.out.println("[7:55:00]Supermarket products have been formed:");
        for (Map.Entry<String, ProductResidue> myPair : supermarket.GetProducts().entrySet())
            System.out.println(myPair.getKey() + ": price - " + myPair.getValue().GetTypeOfProduct().GetUnitPrice() + ", count - " + myPair.getValue().GetCountOfProduct());
    }

    public static void AddProductsNameToTheList(List<String> productNameList, Supermarket supermarket) {
        for (Map.Entry<String, ProductResidue> myPair : supermarket.GetProducts().entrySet())
            productNameList.add(myPair.getKey());
    }

    public static void PrintInfoAboutSoldProducts(boolean bonusesAccrued, long startTime, Customer customerInCashDesk, Supermarket supermarketVirginia) {
        if (bonusesAccrued) {
            PrintTime(startTime);
            System.out.println("Customer" + customerInCashDesk.GetNumberOfCustomer() + " at the cash desk, amount to pay: " + supermarketVirginia.cashdesk.GetBill());
        } else {
            PrintTime(startTime);
            System.out.println("Customer" + customerInCashDesk.GetNumberOfCustomer() +
                    " at the cash desk, amount to pay: " + supermarketVirginia.cashdesk.GetBill()
                    + "and got a lot of: " + supermarketVirginia.cashdesk.GetCountOfBonuses() + " bonuses");
        }
    }

    public static Customer GetRandomCustomer(int numberOfCustomer) {
        Random myRandom = new Random(System.currentTimeMillis());
        TypesOfPeople TypeOfCustomer = GetRandomTypeOfCustomer();
        PaymentMethod methodOfPay = new PaymentMethod(GetRandomMethodOfPay());
        BigDecimal cash = BigDecimal.valueOf(myRandom.nextInt(3500));
        cash = cash.add(BigDecimal.valueOf(3000));
        return new Customer(TypeOfCustomer, cash, cash, cash, methodOfPay, numberOfCustomer);
    }

    private static TypesOfPeople GetRandomTypeOfCustomer() {
        Random myRandom = new Random(System.currentTimeMillis());

        if (myRandom.nextInt(2) == 0)
            return TypesOfPeople.Child;
        else
            return TypesOfPeople.Adult;
    }

    private static Methods GetRandomMethodOfPay() {
        Random myRandom = new Random(System.currentTimeMillis());

        int type = myRandom.nextInt(2);

        if (type == 0)
            return Methods.WithCash;
        else if (type == 1)
            return Methods.WithCreditCard;
        else
            return Methods.WithBonuses;
    }

    public static void PrintTime(long startTime) {
        int hours;
        int minutes;
        int seconds;
        long now = System.currentTimeMillis();
        long difference = now - startTime;
        hours = (int) Math.floor(difference / 60);
        minutes = (int) Math.floor(difference % 60);
        seconds = (int) Math.floor((difference % 60) % 60);
        System.out.print("[" + hours + ":" + minutes + ":" + seconds + "]");
    }

    public static void ToFillTheBasket(Customer customer, Supermarket supermarket, List<String> products) {
        Random myRandom = new Random(System.currentTimeMillis());
        Set<Integer> choosedProducts = new HashSet<Integer>();
        int countOfProductsInBasket = myRandom.nextInt(4) + 1;
        int randomIndexOfProduct;

        for (int i = 0; i < countOfProductsInBasket; i++) {
            int countProductForBuy = myRandom.nextInt(2) + 1;

            while (true) {
                randomIndexOfProduct = myRandom.nextInt(products.size());
                if (!choosedProducts.contains(randomIndexOfProduct)) {
                    choosedProducts.add(randomIndexOfProduct);
                    break;
                }
            }

            String nameOfProduct = products.get(randomIndexOfProduct);
            float countOfProductsInMarket = supermarket.GetProducts().get(nameOfProduct).GetCountOfProduct();
            if ((countOfProductsInMarket != 0) && (countOfProductsInMarket < countProductForBuy))
                customer.AddProductToBasket(countOfProductsInMarket, supermarket.GetProducts().get(nameOfProduct).GetTypeOfProduct());
            else
                customer.AddProductToBasket(countProductForBuy, supermarket.GetProducts().get(nameOfProduct).GetTypeOfProduct());
        }
    }

    public static void ToInitializeValuesOfCashdesk(Supermarket supermarketVirginia, Customer customerInCashDesk) {
        supermarketVirginia.cashdesk.SetCustomer(customerInCashDesk);
        supermarketVirginia.cashdesk.SetBasket(customerInCashDesk.GetBasket());
    }

    public static void PrintListOfProductsInBasketCustomer(Customer customer) {
        System.out.println("Customer " + customer.GetNumberOfCustomer() + " picked up:");
        for (Map.Entry<String, ProductResidue> myPair : customer.GetBasket().GetProducts().entrySet())
            System.out.println(myPair.getValue().GetCountOfProduct() + " units of " + myPair.getKey());
    }

    public static void ServeCustomer(long startTime, Supermarket supermarketVirginia, List<Customer> queueWithCustomers){
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

    public static void AddProductsToTheSupermarket(Supermarket supermarket) {
        Product product1 = new Product("butter", TypesOfProducts.ForAll, 85, 0, UnitOfProduct.byThePriece);
        Product product2 = new Product("milk", TypesOfProducts.ForAll, 25, 0, UnitOfProduct.byThePriece, 9);
        Product product3 = new Product("fish", TypesOfProducts.ForAll, 140, 5, UnitOfProduct.byTheKilo, 30);
        Product product4 = new Product("cheese", TypesOfProducts.ForAll, 49.9f, 0, UnitOfProduct.byTheKilo, 5);
        Product product5 = new Product("paperos", TypesOfProducts.ForAdult, 85, 12, UnitOfProduct.byTheKilo, 9);
        Product product6 = new Product("vodka", TypesOfProducts.ForAdult, 120, UnitOfProduct.byThePriece);
        Product product7 = new Product("vine", TypesOfProducts.ForAdult, 350, 0, UnitOfProduct.byThePriece, 100);
        supermarket.AddProductToSupermarket(1000, product1);
        supermarket.AddProductToSupermarket(700, product2);
        supermarket.AddProductToSupermarket(200, product3);
        supermarket.AddProductToSupermarket(1500, product4);
        supermarket.AddProductToSupermarket(2500, product5);
        supermarket.AddProductToSupermarket(400, product6);
        supermarket.AddProductToSupermarket(300, product7);
    }
}
