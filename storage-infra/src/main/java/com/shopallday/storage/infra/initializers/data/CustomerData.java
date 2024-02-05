package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.usecases.CreateCustomersUseCase;
import com.shopallday.storage.domain.usecases.GetAllCustomersUseCase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CustomerData {

    private static final String[] FIRST_NAMES = {"John", "Jane", "Bob", "Alice", "David", "Emily", "Michael", "Sarah", "William", "Mary"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};
    private static final String[] CITIES = {"London", "New York", "Paris", "Berlin", "Tokyo", "Rome", "Madrid", "Barcelona", "Sydney", "Los Angeles"};

    private final CreateCustomersUseCase createCustomersUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;

    private static final Random random = new Random();

    public CustomerData(CreateCustomersUseCase createCustomersUseCase, GetAllCustomersUseCase getAllCustomersUseCase) {
        this.createCustomersUseCase = createCustomersUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
    }

    void createCustomers() throws CreateCustomerException {
        System.out.println("createCustomers called...");
        List<Customer> randomCustomers = generateRandomCustomers(10);
        createCustomersUseCase.execute(randomCustomers);
        System.out.println("createCustomers finished");
    }

    void printCustomers() throws ReadCustomerException {
        System.out.println("printCustomers called...");
        for(Customer customer: getAllCustomersUseCase.execute()) {
            System.out.println("Customer is: "+ customer);
        }
        System.out.println("printCustomers finished");
    }

    public static List<Customer> generateRandomCustomers(int count) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            customers.add(createRandomCustomer(i));
        }
        return customers;
    }

    private static Customer createRandomCustomer(int count) {
        return new Customer(
                null,  // Customer ID will be generated later
                getRandomElement(FIRST_NAMES),
                getRandomElement(LAST_NAMES),
                "Street " + random.nextInt(100),
                random.nextBoolean() ? "Apt " + random.nextInt(20) : null,
                getRandomElement(CITIES),
                "12345",
                "username" + count,
                "password",
                "true",
                "email" + count + "@example.com",
                "123-456-7890"
        );
    }

    private static String getRandomElement(String[] elements) {
        return elements[random.nextInt(elements.length)];
    }
}
