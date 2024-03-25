package com.shopallday.storage.infra.repository.orders;

import com.shopallday.storage.domain.models.*;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.repository.products.*;
import com.shopallday.storage.infra.initializers.data.DataHelper;
import com.shopallday.storage.infra.initializers.data.OrdersData;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class CustomerOrderDetRepoIntegrationTests extends BaseIntegrationTests {

    private CustomerOrderDetailRepository customerOrderDetailRepository;
    private final ProductStockRepository productStockRepository;
    private final ProductsRepository productsRepository;
    private final OrderStatusTypeRepository orderStatusTypeRepository;
    private final OrdersRepository ordersRepository;
    private final BrandRepository brandRepository;
    private final ProductTypeRepository productTypeRepository;
    private final CategoryRepository categoryRepository;
    private final OrderLinesRepository orderLinesRepository;

    private CustomerRepository customerRepository;

    private RepositoryManager repositoryManager;

    private DataHelper ordersData;

    @Autowired
    public CustomerOrderDetRepoIntegrationTests(
            CustomerOrderDetailRepository customerOrderDetailRepository,
            ProductStockRepository productStockRepository,
            ProductsRepository productsRepository,
            OrderStatusTypeRepository orderStatusTypeRepository,
            OrdersRepository ordersRepository,
            BrandRepository brandRepository,
            ProductTypeRepository productTypeRepository,
            CategoryRepository categoryRepository, OrderLinesRepository orderLinesRepository, CustomerRepository customerRepository,
            RepositoryManager repositoryManager,
            @Qualifier("getOrdersData") DataHelper ordersData) {

        this.customerOrderDetailRepository = customerOrderDetailRepository;
        this.productsRepository = productsRepository;
        this.orderStatusTypeRepository = orderStatusTypeRepository;
        this.ordersRepository = ordersRepository;
        this.brandRepository = brandRepository;
        this.productTypeRepository = productTypeRepository;
        this.categoryRepository = categoryRepository;
        this.orderLinesRepository = orderLinesRepository;
        this.customerRepository = customerRepository;
        this.repositoryManager = repositoryManager;
        this.ordersData = ordersData;
        this.productStockRepository = productStockRepository;
    }

    @Test
    @Transactional
    public void testThatCustomerOrderDetailCanBeObtained() throws Exception {
        final OrdersData ordersDataHelper = (OrdersData) ordersData;

        // Create customer
        Customer customer = ordersDataHelper.createMockCustomer().get(0);
        Customer customerFromDB = customerRepository.createCustomers(List.of(customer)).get(0);

        // Create Categories
        List<Category> expectedCategories = ordersDataHelper.createMockCategories();
        categoryRepository.createCategories(expectedCategories);
        List<Category> categoriesFromDB = categoryRepository.getCategories();

        // Create Brands
        brandRepository.createBrands(ordersDataHelper.createMockBrands());
        List<Brand> brandsFromDBands = brandRepository.findAllBrands();

        // Create ProductType
        List<ProductType> expectedProductTypes = ordersDataHelper.createMockProductTypes(categoriesFromDB);
        productTypeRepository.createProductTypes(expectedProductTypes, repositoryManager);
        List<ProductType> productTypesFromDb = productTypeRepository.findAllProductTypes();

        // Create Products
        List<Product> mockProducts = ordersDataHelper.createMockProducts(productTypesFromDb, brandsFromDBands);
        productsRepository.createProducts(mockProducts, repositoryManager);
        List<Product> productsFromDb = productsRepository.findAllProducts();

        // Create Product Stock
        List<ProductStock> productStocks = ordersDataHelper.createMockProductStock(productsFromDb);
        productStockRepository.createProductStock(productStocks, repositoryManager);
        List<ProductStock> productStocksFromDB = productStockRepository.findAllProductStocks();


        // Create order statuses
        orderStatusTypeRepository.createOrderStatusType(ordersDataHelper.createMockOrderStatusTypes(), repositoryManager);
        List<OrderStatusType> orderStatusTypesFromDB = orderStatusTypeRepository.getAllOrderStatusTypes();

        // Create orders
        List<Order> orders = ordersDataHelper.createMockOrders(List.of(customerFromDB),orderStatusTypesFromDB);
        ordersRepository.createOrder(orders, repositoryManager);
        List<Order> ordersFromDB = ordersRepository.getAllOrders();

        // Create Order lines
        List<OrderLine> expectedOrderLines = ordersDataHelper.createMockOrderLines(productsFromDb, productStocksFromDB, ordersFromDB);
        orderLinesRepository.createOrderLine(expectedOrderLines, repositoryManager);

        List<CustomerOrderDetail> customerOrderDetails = customerOrderDetailRepository.getOrdersByCustomerId(customerFromDB.getCustomerId());
        for (CustomerOrderDetail customerOrderDetail : customerOrderDetails) {
            System.out.println(customerOrderDetail);
        }
    }
}
