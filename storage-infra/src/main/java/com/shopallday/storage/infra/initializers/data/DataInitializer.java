package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.initializers.StorageInitializer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements StorageInitializer {

    private final DataHelper productData;
    private final DataHelper customerData;
    private final DataHelper categoryData;
    private final DataHelper productTypeData;
    private final DataHelper brandData;

    public DataInitializer(
            DataHelper productData,
            DataHelper customerData,
            DataHelper categoryData,
            DataHelper productTypeData,
            DataHelper brandData) {
        this.productData = productData;
        this.customerData = customerData;
        this.categoryData = categoryData;
        this.productTypeData = productTypeData;
        this.brandData = brandData;
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
    public void initialize() throws Exception {
        System.out.println("DataInitializer called");
        List<DataHelper> dataHelpers = new ArrayList<>();
        dataHelpers.add(customerData);
        dataHelpers.add(categoryData);
        dataHelpers.add(productTypeData);
        dataHelpers.add(brandData);
        dataHelpers.add(productData);

        for (DataHelper dataHelper: dataHelpers) {
            dataHelper.create();
            dataHelper.print();
        }

        System.out.println("DataInitializer finished");
    }
}
