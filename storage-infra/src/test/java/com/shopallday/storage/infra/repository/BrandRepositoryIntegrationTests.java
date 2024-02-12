package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest(classes = TestInfraConfig.class)
//@ExtendWith(SpringExtension.class)
public class BrandRepositoryIntegrationTests {

    private BrandRepository underTest;

    //@Autowired
    public BrandRepositoryIntegrationTests(BrandRepository brandRepository) {
        this.underTest = brandRepository;
    }

    //@Test
    public void testThatBrandCanBeCreatedAndObtained() {
        underTest.createBrands(getBrands(3));

        List<Brand> brands = underTest.findAllBrands();

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
