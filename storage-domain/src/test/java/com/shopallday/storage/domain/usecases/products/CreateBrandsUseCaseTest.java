package com.shopallday.storage.domain.usecases.products;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.repository.products.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateBrandsUseCaseTest {
    @Mock
    BrandRepository brandRepository;

    @Mock
    Brand brand;

    @Mock
    List<Brand> brandList;
    @InjectMocks
    CreateBrandsUseCase underTest;

    @Test
    public void testThatCreateBrandsOnRepo() {
        underTest.execute(brandList);
        verify(brandRepository).createBrands(brandList);

    }
}
