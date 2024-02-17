package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.infra.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper extends StorageMapper<CategoryEntity, Category>{

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Override
    CategoryEntity mapToEntity(Category category);

    @Override
    Category mapToDomain(CategoryEntity categoryEntity);

    @Override
    List<CategoryEntity> mapToEntity(List<Category> categories);

    @Override
    List<Category> mapToDomain(List<CategoryEntity> categoryEntities);
}
