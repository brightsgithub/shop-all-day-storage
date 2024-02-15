package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CustomerData implements DataHelper {

    private static final String[] FIRST_NAMES = {"John", "Jane", "Bob", "Alice", "David", "Emily", "Michael", "Sarah", "William", "Mary"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};
    private static final String[] CITIES = {"London", "New York", "Paris", "Berlin", "Tokyo", "Rome", "Madrid", "Barcelona", "Sydney", "Los Angeles"};

    private final CustomerRepository customerRepository;

    private static final Random random = new Random();

    public CustomerData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void create() throws CreateCustomerException {
        System.out.println("createCustomers called...");
        List<Customer> randomCustomers = generateRandomCustomers(10);
        customerRepository.createCustomers(randomCustomers);
        System.out.println("createCustomers finished");
    }

    public void print() throws ReadCustomerException {
        System.out.println("printCustomers called...");
        for(Customer customer: customerRepository.getCustomers()) {
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
                random.nextBoolean() ? "Apartment " + random.nextInt(20) : null,
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
