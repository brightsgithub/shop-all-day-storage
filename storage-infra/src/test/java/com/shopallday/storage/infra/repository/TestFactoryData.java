package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.*;

import java.util.ArrayList;
import java.util.List;

public class TestFactoryData {

    static List<Brand> createMockBrands(int count) {
        List<Brand> expectedBrands = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            expectedBrands.add(new Brand(null, "Test Brand_" + i));
        }
        return expectedBrands;
    }

    static List<Category> createMockCategories(int count) {
        List<Category> expectedCategories = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            expectedCategories.add(new Category(null, "Category_" + i));
        }
        return expectedCategories;
    }

    static List<Customer> createMockCustomers(int count) {
        List<Customer> expected = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            expected.add(
                    new Customer(null, "John_" + i, "Doe", "123 Main St",
                            "", "City", "12345", "johndoe_" + i, "password",
                            "true", "johndoe" + i + "@example.com", "123-456-7890"));
        }
        return expected;
    }

    static List<ProductType> createMockProductTypes(int count) {
        List<ProductType> expectedProductTypes = new ArrayList<>();
        List<Category> expectedCategories = createMockCategories(count);
        for (int i = 0; i < count; i++) {
            expectedProductTypes.add(new ProductType(null, expectedCategories.get(i), "Type"+i));
        }
        return expectedProductTypes;
    }

    static List<Product> createMockProducts(int count) {
        List<Product> expectedProducts = new ArrayList<>();
        List<ProductType> expectedProductTypes = createMockProductTypes(count);
        List<Brand> expectedBrands = createMockBrands(count);
        for (int i = 0; i < count; i++) {
            expectedProducts.add(
                    new Product(null, expectedProductTypes.get(i), expectedBrands.get(i), "ShortTitle1_" + i, "LongTitle1_" + i, "ShortDescription1_" + i, "LongDescription1_" + i));
        }
        return expectedProducts;
    }

    static List<ProductStock> createMockProductStock(int count) {
        List<ProductStock> expectedProductStocks = new ArrayList<>();
        List<Product> expectedProducts = createMockProducts(count);
        for (int i = 0; i < count; i++) {
            expectedProductStocks.add(
                    new ProductStock(null, expectedProducts.get(i), 10, "size_"+i, "color_"+i, 521f));
        }
        return expectedProductStocks;
    }
}
