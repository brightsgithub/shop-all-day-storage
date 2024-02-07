package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.usecases.CreateCategoryUseCase;
import com.shopallday.storage.domain.usecases.GetCategoryUseCase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryData {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;


    public CategoryData(CreateCategoryUseCase createCategoryUseCase, GetCategoryUseCase getCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
    }

    void createCategories() {
        System.out.println("print createCategories called...");
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Electronics"));
        categories.add(new Category(2L, "Appliances"));
        categories.add(new Category(3L, "Furniture"));
        categories.add(new Category(4L, "Bathroom"));
        createCategoryUseCase.execute(categories);
        System.out.println("print createCategories finished");
    }

    void print() {
        System.out.println("printCategories called...");
        for(Category category: getCategoryUseCase.execute()) {
            System.out.println(category);
        }
        System.out.println("printCategories finished");
    }
}
