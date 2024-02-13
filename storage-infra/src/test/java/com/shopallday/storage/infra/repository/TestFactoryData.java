package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.models.Category;

import java.util.ArrayList;
import java.util.List;

public class TestFactoryData {

    static List<Brand> createMockBrands(int count) {
        List<Brand> brands = new ArrayList<>();

        for(int i=0; i<count; i++) {
            brands.add(new Brand(null, "Test Brand_"+i));
        }
        return brands;
    }

    static List<Category> createMockCategories(int count) {
        List<Category> categories = new ArrayList<>();

        for(int i=0; i<count; i++) {
            categories.add(new Category(null, "Category_"+i));
        }
        return categories;
    }
}
