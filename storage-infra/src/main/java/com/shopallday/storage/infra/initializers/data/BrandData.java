package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.usecases.CreateBrandsUseCase;
import com.shopallday.storage.domain.usecases.GetAllBrandsUseCase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandData implements DataHelper {

    private final GetAllBrandsUseCase getAllBrandsUseCase;

    private final CreateBrandsUseCase createBrandsUseCase;

    public BrandData(GetAllBrandsUseCase getAllBrandsUseCase, CreateBrandsUseCase createBrandsUseCase) {
        this.getAllBrandsUseCase = getAllBrandsUseCase;
        this.createBrandsUseCase = createBrandsUseCase;
    }


    @Override
    public void create() throws Exception {
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(null, "Samsung"));
        brands.add(new Brand(null, "HP"));
        brands.add(new Brand(null, "Apple"));
        brands.add(new Brand(null, "ACER"));
        brands.add(new Brand(null, "LENOVO"));
        brands.add(new Brand(null, "Google"));
        brands.add(new Brand(null, "BOSCH"));
        brands.add(new Brand(null, "PANASONIC"));
        brands.add(new Brand(null, "BOASTAD"));
        brands.add(new Brand(null, "HEMNES"));
        brands.add(new Brand(null, "LANEBERG"));
        createBrandsUseCase.execute(brands);
    }

    @Override
    public void print() {
        System.out.println("print getAllBrandsUseCase called...");
        for(Brand brand: getAllBrandsUseCase.execute()) {
            System.out.println(brand);
        }
        System.out.println("getAllBrandsUseCase finished");
    }
}
