package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.initializers.StorageInitializer;
import com.shopallday.storage.domain.models.Customer;
import com.shopallday.storage.domain.usecases.CreateCustomersUseCase;
import com.shopallday.storage.domain.usecases.GetAllCustomersUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements StorageInitializer {

    private final CreateCustomersUseCase createCustomersUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;

    public DataInitializer(
            CreateCustomersUseCase createCustomersUseCase,
                           GetAllCustomersUseCase getAllCustomersUseCase) {
        this.createCustomersUseCase = createCustomersUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
    }


    /**
     * By default, Spring's declarative transaction management only rolls back transactions for unchecked exceptions
     * (runtime exceptions).
     * If CreateCustomerException is a checked exception, it won't trigger a rollback unless you explicitly configure
     * it using rollbackOn attributes in the @Transactional annotation.
     * The below will now rollback on a Runtime exception or CreateCustomerException
     * @throws CreateCustomerException
     * @throws ReadCustomerException
     */
    @Transactional(rollbackOn = { CreateCustomerException.class })
    @Override
    public void initialize() throws CreateCustomerException, ReadCustomerException {
        System.out.println("DataInitializer called");
        createCustomers();
        //testRollBack();
        printCustomers();
        System.out.println("DataInitializer finished");
    }

    private void createCustomers() throws CreateCustomerException {
        System.out.println("createCustomers called...");
        createCustomersUseCase.execute(getTestCustomers());
        System.out.println("createCustomers finished");
    }

    private void testRollBack() throws CreateCustomerException {
        throw new CreateCustomerException("Testing rollback");
    }

    private void printCustomers() throws ReadCustomerException {
        System.out.println("printCustomers called...");
        for(Customer customer: getAllCustomersUseCase.execute()) {
            System.out.println("Customer is: "+ customer);
        }
        System.out.println("printCustomers finished");
    }

    private List<Customer> getTestCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer customer  = new Customer(
                -1L,
                "firstName",
                "lastName",
                "address1",
                "address2",
                "city",
                "postCode",
                "username",
                "password",
                "enabled",
                "email",
                "phoneNumber"
        );
        customers.add(customer);
        return customers;
    }
}
