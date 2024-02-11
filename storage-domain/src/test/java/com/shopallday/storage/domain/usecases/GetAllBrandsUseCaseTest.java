package com.shopallday.storage.domain.usecases;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllBrandsUseCaseTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private GetAllBrandsUseCase cut;

    @Test
    public void testThatFindAllBrandsWasCalledOnRepo() {

        List<Brand> expectedBrands = new ArrayList<>();
        expectedBrands.add(new Brand(1L, "Brand1"));
        expectedBrands.add(new Brand(2L, "Brand2"));

        when(brandRepository.findAllBrands()).thenReturn(expectedBrands);

        List<Brand> actualBrands = cut.execute();

        assertEquals(expectedBrands.size(), actualBrands.size());

        for (int i = 0; i < expectedBrands.size(); i++) {
            Brand expectedBrand = expectedBrands.get(i);
            Brand actualBrand = actualBrands.get(i);
            assertEquals(expectedBrand.getBrandId(), actualBrand.getBrandId());
            assertEquals(expectedBrand.getBrandName(), actualBrand.getBrandName());
        }
    }
}
