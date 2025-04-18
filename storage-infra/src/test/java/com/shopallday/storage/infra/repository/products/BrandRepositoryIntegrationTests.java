package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import com.shopallday.storage.infra.repository.BaseIntegrationTests;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.shopallday.storage.infra.repository.TestFactoryData.createMockBrands;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandRepositoryIntegrationTests extends BaseIntegrationTests {
    private BrandRepository brandRepository;

    @Autowired
    public BrandRepositoryIntegrationTests(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Test
    @Transactional
    public void testThatBrandCanBeCreatedAndObtained() {
        List<Brand> expectedBrands = createMockBrands(3);
        brandRepository.createBrands(expectedBrands);

        List<Brand> actualBrands = brandRepository.findAllBrands();

        assertEquals(actualBrands.size() ,expectedBrands.size());

        for (int i = 0; i < expectedBrands.size(); i++) {
            Brand expectedBrand = expectedBrands.get(i);
            Brand actualBrand = actualBrands.get(i);
            assertEquals(expectedBrand.getBrandName(), actualBrand.getBrandName());
        }
    }
}
