package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;

public class OrderLinesData implements DataHelper {

    private final RepositoryManager repositoryManager;
    private final CustomerRepository customerRepository;

    public OrderLinesData(RepositoryManager repositoryManager, CustomerRepository customerRepository) {
        this.repositoryManager = repositoryManager;
        this.customerRepository = customerRepository;
    }

    @Override
    public void create() throws Exception {
        //OrderLine
    }

    @Override
    public void print() throws Exception {

    }
}
