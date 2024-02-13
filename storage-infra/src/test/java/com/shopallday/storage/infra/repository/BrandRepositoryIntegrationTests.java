package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandRepositoryIntegrationTests extends BaseIntegrationTests {
    private BrandRepository brandRepository;

    @Autowired
    public BrandRepositoryIntegrationTests(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Test
    public void testThatBrandCanBeCreatedAndObtained() {
        brandRepository.createBrands(getBrands(3));

        List<Brand> brands = brandRepository.findAllBrands();

        assertEquals(brands.size() ,3);
    }

    private List<Brand> getBrands(int count) {
        List<Brand> brands = new ArrayList<>();

        for(int i=0; i<count; i++) {
            brands.add(new Brand(null, "Test Brand_"+i));
        }
        return brands;
    }
}
