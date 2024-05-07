package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.*;
import com.shopallday.storage.domain.repository.RepositoryManager;
import com.shopallday.storage.domain.repository.customer.CustomerRepository;
import com.shopallday.storage.domain.repository.orders.CustomerOrderDetailRepository;
import com.shopallday.storage.domain.repository.orders.OrderLinesRepository;
import com.shopallday.storage.domain.repository.orders.OrderStatusTypeRepository;
import com.shopallday.storage.domain.repository.orders.OrdersRepository;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class OrdersData implements DataHelper {
    private final RepositoryManager repositoryManager;
    private final CustomerOrderDetailRepository customerOrderDetailRepository;
    private final CustomerRepository customerRepository;
    private final OrderStatusTypeRepository orderStatusTypeRepository;
    private final ProductStockRepository productStockRepository;
    private final ProductsRepository productsRepository;
    private final OrdersRepository ordersRepository;
    private final OrderLinesRepository orderLinesRepository;

    public OrdersData(
            RepositoryManager repositoryManager,
            CustomerOrderDetailRepository customerOrderDetailRepository,
            CustomerRepository customerRepository,
            OrderStatusTypeRepository orderStatusTypeRepository,
            ProductStockRepository productStockRepository, ProductsRepository productsRepository, OrdersRepository ordersRepository, OrderLinesRepository orderLinesRepository) {

        this.repositoryManager = repositoryManager;
        this.customerOrderDetailRepository = customerOrderDetailRepository;
        this.customerRepository = customerRepository;
        this.orderStatusTypeRepository = orderStatusTypeRepository;
        this.productStockRepository = productStockRepository;
        this.productsRepository = productsRepository;
        this.ordersRepository = ordersRepository;
        this.orderLinesRepository = orderLinesRepository;
    }

    @Override
    public void create() throws Exception {
        final List<Customer> customers = customerRepository.getCustomers();
        final List<OrderStatusType> createdOrderStatusTypes = createMockOrderStatusTypes();
        final List<OrderStatusType> orderStatusTypesFromDB = orderStatusTypeRepository.createOrderStatusType(createdOrderStatusTypes);
        final List<Order> createdOrders = createMockOrders(customers, orderStatusTypesFromDB);
        final List<Order> ordersFromDB = ordersRepository.createOrder(createdOrders, repositoryManager);
        final List<Product> products = productsRepository.findAllProducts();
        final List<ProductStock> productStocks = productStockRepository.findAllProductStocks();
        final List<OrderLine> createdOrderLines = createMockOrderLines(products, productStocks, ordersFromDB);
        orderLinesRepository.createOrderLine(createdOrderLines, repositoryManager);
    }

    @Override
    public void print() throws Exception {

    }

    public  List<Customer> createMockCustomer() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(null, "John", "Doe", "123 Main St", "", "City", "12345", "johndoe_10", "password", "true", "johndoe10@example.com", "123-456-7890"));
        return customers;
    }

    public List<Brand> createMockBrands() {
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(null, "Samsung"));
        brands.add(new Brand(null, "Apple"));
        brands.add(new Brand(null, "Google"));
        brands.add(new Brand(null, "BOASTAD"));
        return brands;
    }

    public List<Category> createMockCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(null, "Electronics"));
        categories.add(new Category(null, "Mobile"));
        categories.add(new Category(null, "Furniture"));
        return categories;
    }

    public List<ProductType> createMockProductTypes(List<Category> categories) {
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(new ProductType(null, categories.get(2), "TV stand"));
        productTypes.add(new ProductType(null, categories.get(2),  "TV stand"));
        productTypes.add(new ProductType(null, categories.get(1),  "Mobile"));
        return productTypes;
    }


    public List<Product> createMockProducts(List<ProductType> productTypes, List<Brand> brands) {
        List<Product> products = new ArrayList<>();

        products.add(new Product(null, productTypes.get(0), brands.get(0), "Q60C QLED 4K HDR", "Q60C QLED 4K HDR Long Description", "Short Description", "Long Description", null));
        products.add(new Product(null, productTypes.get(1), brands.get(0), "N85C Neo QLED 4K HDR", "N85C Neo QLED 4K HDR Long Description", "Short Description", "Long Description",null));
        products.add(new Product(null, productTypes.get(2), brands.get(1), "BOASTAD TV bench, black/oak veneer", "BOASTAD TV bench, black/oak veneer Long Description", "Short Description", "Long Description",null));
        products.add(new Product(null, productTypes.get(2), brands.get(2), "APPLE iPhone 15", "APPLE iPhone 15 Long Description", "Short Description", "Long Description",null));
        products.add(new Product(null, productTypes.get(2), brands.get(2), "GOOGLE Pixel 7a", "GOOGLE Pixel 7a Long Description", "Short Description", "Long Description",null));

        return products;
    }

    public  List<ProductStock> createMockProductStock(List<Product> mockProducts) {
        List<ProductStock> productStocks = new ArrayList<>();
        productStocks.add(new ProductStock(null, mockProducts.get(0), 10, "Small", "Black", 1000.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(0), 10, "Large", "Black", 1100.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(0), 10, "Large", "White", 1100.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(1), 10, "XLarge", "White", 1400.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(2), 10, "Small", "Black", 300.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(2), 10, "Small", "Red", 300.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(2), 10, "Large", "White", 600.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(3), 10, "Small", "Black", 300.0f));
        productStocks.add(new ProductStock(null, mockProducts.get(4), 10, "Small", "Red", 300.0f));
        return productStocks;
    }

    public  List<OrderStatusType> createMockOrderStatusTypes() {
        List<OrderStatusType> orderStatusTypes = new ArrayList<>();
        orderStatusTypes.add(new OrderStatusType(null, "PENDING"));
        orderStatusTypes.add(new OrderStatusType(null, "SUCCESSFUL"));
        orderStatusTypes.add(new OrderStatusType(null, "FAILED"));
        return orderStatusTypes;
    }

    public  List<Order> createMockOrders(List<Customer> customers, List<OrderStatusType> orderStatusTypes) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            orders.add(new Order(null, new Timestamp(Calendar.getInstance().getTimeInMillis()), customers.get(0), orderStatusTypes.get(i % 3)));
        }
        return orders;
    }

    public  List<OrderLine> createMockOrderLines(List<Product> products, List<ProductStock> productStocks, List<Order> orders) {
        List<OrderLine> orderLines = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            orderLines.add(new OrderLine(null, orders.get(0), products.get(i % 5), (i + 1), productStocks.get(i).getSize(), productStocks.get(i).getColor()));
        }
        return orderLines;
    }
}
