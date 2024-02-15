package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.exceptions.customer.CreateCustomerException;
import com.shopallday.storage.domain.exceptions.customer.ReadCustomerException;
import com.shopallday.storage.domain.initializers.StorageInitializer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private final DataHelper productStockData;

    @Autowired
    private Environment environment;

    public DataInitializer(
            DataHelper productData,
            DataHelper customerData,
            DataHelper categoryData,
            DataHelper productTypeData,
            DataHelper brandData,
            DataHelper productStockData
    ) {
        this.productData = productData;
        this.customerData = customerData;
        this.categoryData = categoryData;
        this.productTypeData = productTypeData;
        this.brandData = brandData;
        this.productStockData = productStockData;
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
    @Transactional(rollbackOn = { Exception.class })
    @Override
    public void initialize() throws Exception {
        if (isDdlAutoCreate()) {
            System.out.println("DataInitializer called");
            List<DataHelper> dataHelpers = new ArrayList<>();
            dataHelpers.add(customerData);
            dataHelpers.add(categoryData);
            dataHelpers.add(productTypeData);
            dataHelpers.add(brandData);
            dataHelpers.add(productData);
            dataHelpers.add(productStockData);

            for (DataHelper dataHelper: dataHelpers) {
                dataHelper.create();
                //dataHelper.print();
            }

            System.out.println("DataInitializer finished");
        }
    }

    /**
     * Only add data via jpa hibernate if we have the environment variable has been set to create tables
     * @return
     */
    public boolean isDdlAutoCreate() {
        String ddlAuto = environment.getProperty("spring.jpa.hibernate.ddl-auto");
        return ddlAuto != null && ddlAuto.equalsIgnoreCase("create");
    }
}
