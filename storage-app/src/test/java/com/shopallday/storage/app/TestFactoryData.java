package com.shopallday.storage.app;

import com.shopallday.storage.domain.models.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestFactoryData {

    public static List<Brand> createMockBrands(int count) {
        List<Brand> expectedBrands = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            expectedBrands.add(new Brand(null, "Test Brand_" + i));
        }
        return expectedBrands;
    }

    public static List<Category> createMockCategories(int count) {
        List<Category> expectedCategories = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            expectedCategories.add(new Category(null, "Category_" + i));
        }
        return expectedCategories;
    }

    public static List<Customer> createMockCustomers(int count) {
        List<Customer> expected = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            expected.add(
                    new Customer(null, "John_" + i, "Doe", "123 Main St",
                            "", "City", "12345", "johndoe_" + i, "password",
                            "true", "johndoe" + i + "@example.com", "123-456-7890"));
        }
        return expected;
    }

    public static List<CustomerShippingAddress> createMockCustomerShippingAddresses(int count) {
        List<Customer> customers = createMockCustomers(count);
        List<CustomerShippingAddress> expected = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            expected.add(
                    new CustomerShippingAddress(
                            null,
                            customers.get(i),
                            "Address_"+i,
                            "Address_2_"+i,
                            "City_"+i,
                            "PostCode_"+i
                    ));
        }
        return expected;
    }

    public static List<ProductType> createMockProductTypes(int count) {
        List<ProductType> expectedProductTypes = new ArrayList<>();
        List<Category> expectedCategories = createMockCategories(count);
        for (int i = 0; i < count; i++) {
            expectedProductTypes.add(new ProductType(null, expectedCategories.get(i), "Type"+i));
        }
        return expectedProductTypes;
    }

    public static List<Product> createMockProducts(int count) {
        List<Product> expectedProducts = new ArrayList<>();
        List<ProductType> expectedProductTypes = createMockProductTypes(count);
        List<Brand> expectedBrands = createMockBrands(count);
        for (int i = 0; i < count; i++) {
            expectedProducts.add(
                    new Product(null, expectedProductTypes.get(i), expectedBrands.get(i), "ShortTitle1_" + i, "LongTitle1_" + i, "ShortDescription1_" + i, "LongDescription1_" + i, null));
        }
        return expectedProducts;
    }

    public static List<ProductStock> createMockProductStock(int count) {
        List<ProductStock> expectedProductStocks = new ArrayList<>();
        List<Product> expectedProducts = createMockProducts(count);
        for (int i = 0; i < count; i++) {
            expectedProductStocks.add(
                    new ProductStock(null, expectedProducts.get(i), 10, "size_"+i, "color_"+i, 521f));
        }
        return expectedProductStocks;
    }

    public static List<OrderStatusType> createMockOrderStatusType(int count) {
        List<OrderStatusType> expectedOrderStatusTypes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            expectedOrderStatusTypes.add(new OrderStatusType(null, "Test_Status_"+i));
        }
        return expectedOrderStatusTypes;
    }

    public static List<Order> createMockOrders(int count) {
        List<Order> expectedOrders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            expectedOrders.add(
                    new Order(
                            null,
                            new Timestamp(Calendar.getInstance().getTimeInMillis()),
                            createMockCustomers(1).get(0),
                            createMockOrderStatusType(1).get(0)
                    )
            );
        }
        return expectedOrders;
    }

    public static List<OrderLine> createMockOrderLines(int count) {
        List<OrderLine> expectedOrderLines = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            expectedOrderLines.add(
                    new OrderLine(
                            null,
                            createMockOrders(1).get(0),
                            createMockProducts(1).get(0),
                            (i+1),
                            "size_"+i,
                            "color_"+i
                    )
            );
        }
        return expectedOrderLines;
    }
}
