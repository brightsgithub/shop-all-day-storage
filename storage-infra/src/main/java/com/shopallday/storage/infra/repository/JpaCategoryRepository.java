package com.shopallday.storage.infra.repository;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.CategoryRepository;
import com.shopallday.storage.infra.entities.CategoryEntity;
import com.shopallday.storage.infra.mappers.CategoryMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long>, CategoryRepository {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Override
    default void createCategories(List<Category> categories) {
        saveAll(categoryMapper.categoriesToEntities(categories));
    }

    @Override
    default void createCategory(Category category) {
        save(categoryMapper.categoryToEntity(category));
    }

    @Override
    default List<Category> getCategories() {
        return categoryMapper.entitiesToCategories(findAll());
    }

    @Override
    default void updateCategories(List<Category> categories) {
        createCategories(categories);
    }

    @Override
    default void updateCategory(Category category) {
        List<Category> catList = new ArrayList<>();
        catList.add(category);
        createCategories(catList);
    }

    @Override
    default void deleteCategory(Category category) {
        delete(categoryMapper.categoryToEntity(category));
    }
}
