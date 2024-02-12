package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryData implements DataHelper {

    private final CategoryRepository categoryRepository;


    public CategoryData(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create() {
        System.out.println("print createCategories called...");
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Electronics"));
        categories.add(new Category(2L, "Appliances"));
        categories.add(new Category(3L, "Furniture"));
        categories.add(new Category(4L, "Bathroom"));
        categoryRepository.createCategories(categories);
        System.out.println("print createCategories finished");
    }

    public void print() {
        System.out.println("printCategories called...");
        for(Category category: categoryRepository.getCategories()) {
            System.out.println(category);
        }
        System.out.println("printCategories finished");
    }
}
