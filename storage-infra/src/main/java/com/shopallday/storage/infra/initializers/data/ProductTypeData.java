package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.domain.repository.products.ProductTypeRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTypeData implements DataHelper {

    private final CategoryRepository categoryRepository;
    private final ProductTypeRepository productTypeRepository;
    private final RepositoryManager repositoryManager;

    public ProductTypeData(
            CategoryRepository categoryRepository,
            ProductTypeRepository productTypeRepository,
            RepositoryManager repositoryManager) {
        this.categoryRepository = categoryRepository;
        this.productTypeRepository = productTypeRepository;
        this.repositoryManager = repositoryManager;
    }


    public void create() {
        List<Category> categories = categoryRepository.getCategories();
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

        //productTypeRepository.createProductTypes(productTypes, categoryRepository);
        productTypeRepository.createProductTypes(productTypes, repositoryManager);
    }

    public void print() {
        System.out.println("printProductTypes called...");
        for(ProductType productType: productTypeRepository.findAllProductTypes()) {
            System.out.println(productType);
        }
        System.out.println("printProductTypes finished");
    }
}
