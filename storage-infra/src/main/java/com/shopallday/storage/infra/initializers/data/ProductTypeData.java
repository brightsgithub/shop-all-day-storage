package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.usecases.CreateProductTypeUseCase;
import com.shopallday.storage.domain.usecases.GetAllProductTypesUseCase;
import com.shopallday.storage.domain.usecases.GetCategoryUseCase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTypeData implements DataHelper {

    private final GetCategoryUseCase getCategoryUseCase;
    private final CreateProductTypeUseCase createProductTypeUseCase;
    private final GetAllProductTypesUseCase getAllProductTypesUseCase;

    public ProductTypeData(
            GetCategoryUseCase getCategoryUseCase,
            CreateProductTypeUseCase createProductTypeUseCase,
            GetAllProductTypesUseCase getAllProductTypesUseCase
    ) {
        this.getCategoryUseCase = getCategoryUseCase;
        this.createProductTypeUseCase = createProductTypeUseCase;
        this.getAllProductTypesUseCase = getAllProductTypesUseCase;
    }

    public void create() {
        List<Category> categories = getCategoryUseCase.execute();
        List<ProductType> productTypes = new ArrayList<>();

        // Electronics
        productTypes.add(new ProductType(null, categories.get(0), "TV"));
        productTypes.add(new ProductType(null, categories.get(0), "Desktop PC"));
        productTypes.add(new ProductType(null, categories.get(0), "Laptop"));
        productTypes.add(new ProductType(null, categories.get(0), "Mobile"));

        // Appliances
        productTypes.add(new ProductType(null, categories.get(1), "Microwave"));
        productTypes.add(new ProductType(null, categories.get(1), "Oven"));
        productTypes.add(new ProductType(null, categories.get(1), "Washing machine"));
        productTypes.add(new ProductType(null, categories.get(1), "Dishwasher"));

        // Furniture
        productTypes.add(new ProductType(null, categories.get(2), "Dining table"));
        productTypes.add(new ProductType(null, categories.get(2), "TV stand"));

        // Bathroom
        productTypes.add(new ProductType(null, categories.get(3), "Shower"));
        productTypes.add(new ProductType(null, categories.get(3), "Sink"));
        productTypes.add(new ProductType(null, categories.get(3), "Bath"));

        createProductTypeUseCase.execute(productTypes);
    }

    public void print() {
        System.out.println("printProductTypes called...");
        for(ProductType productType: getAllProductTypesUseCase.execute()) {
            System.out.println(productType);
        }
        System.out.println("printProductTypes finished");
    }
}
