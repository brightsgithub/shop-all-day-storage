package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.initializers.StorageInitializer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements StorageInitializer {

    private final ProductData productData;
    private final CustomerData customerData;
    private final CategoryData categoryData;

    public DataInitializer(ProductData productData, CustomerData customerData, CategoryData categoryData) {
        this.productData = productData;
        this.customerData = customerData;
        this.categoryData = categoryData;
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
        customerData.createCustomers();
        customerData.printCustomers();

        categoryData.createCategories();
        categoryData.printCategories();
        System.out.println("DataInitializer finished");
    }
}
