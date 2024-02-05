package com.shopallday.storage.infra.mappers;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.infra.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryEntity categoryToEntity(Category category);
    Category entityToCategory(CategoryEntity entity);

    List<CategoryEntity> categoriesToEntities(List<Category> categories);
    List<Category> entitiesToCategories(List<CategoryEntity> entities);

}
