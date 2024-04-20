package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.repository.delete.DeleteAllRepository;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.repository.customer.CustomerShippingAddRepository;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.repository.products.*;
import org.springframework.transaction.annotation.Transactional;

public class DeleteAllRepositoryImpl implements DeleteAllRepository {

    private final CustomerRepository customerRepository;
    private final CustomerShippingAddRepository customerShippingAddRepository;
    private final CustomerOrderDetailRepository customerOrderDetailRepository;
    private final OrderLinesRepository orderLinesRepository;
    private final OrdersRepository ordersRepository;
    private final OrderStatusTypeRepository orderStatusTypeRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductsRepository productsRepository;
    private final ProductStockRepository productStockRepository;
    private final ProductTypeRepository productTypeRepository;

    public DeleteAllRepositoryImpl(CustomerRepository customerRepository,
                                   CustomerShippingAddRepository customerShippingAddRepository,
                                   CustomerOrderDetailRepository customerOrderDetailRepository,
                                   OrderLinesRepository orderLinesRepository,
                                   OrdersRepository ordersRepository,
                                   OrderStatusTypeRepository orderStatusTypeRepository,
                                   BrandRepository brandRepository,
                                   CategoryRepository categoryRepository,
                                   ProductsRepository productsRepository,
                                   ProductStockRepository productStockRepository,
                                   ProductTypeRepository productTypeRepository) {
        this.customerRepository = customerRepository;
        this.customerShippingAddRepository = customerShippingAddRepository;
        this.customerOrderDetailRepository = customerOrderDetailRepository;
        this.orderLinesRepository = orderLinesRepository;
        this.ordersRepository = ordersRepository;
        this.orderStatusTypeRepository = orderStatusTypeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productsRepository = productsRepository;
        this.productStockRepository = productStockRepository;
        this.productTypeRepository = productTypeRepository;
    }


    @Override
    @Transactional
    public void deleteAllTables() {
        orderLinesRepository.deleteAll();
        ordersRepository.deleteAll();
        orderStatusTypeRepository.deleteAll();
        productStockRepository.deleteAll();
        productsRepository.deleteAll();
        brandRepository.deleteAll();
        productTypeRepository.deleteAll();
        categoryRepository.deleteAll();
        customerShippingAddRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
