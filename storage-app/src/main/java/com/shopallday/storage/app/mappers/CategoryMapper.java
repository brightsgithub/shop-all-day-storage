package com.shopallday.storage.app.mappers;

import com.shopallday.storage.app.models.CategoryDto;
import com.shopallday.storage.domain.models.Category;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@org.mapstruct.Mapper
public interface CategoryMapper extends Mapper<Category, CategoryDto> {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Override
    CategoryDto mapFromDomainToDto(Category domain);

    @Override
    Category mapFromDtoToDomain(CategoryDto Dto);

    @Override
    List<CategoryDto> mapFromDomainToDto(List<Category> domains);

    @Override
    List<Category> mapFromDtoToDomain(List<CategoryDto> dtos);
}