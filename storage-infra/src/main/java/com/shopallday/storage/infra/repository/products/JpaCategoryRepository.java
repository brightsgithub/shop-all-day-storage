package com.shopallday.storage.infra.repository.products;

import com.shopallday.storage.domain.models.Category;
import com.shopallday.storage.domain.repository.products.CategoryRepository;
import com.shopallday.storage.infra.entities.CategoryEntity;
import com.shopallday.storage.infra.mappers.CategoryMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long>, CategoryRepository {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Override
    default void createCategories(List<Category> categories) {
        saveAll(categoryMapper.mapToEntity(categories));
    }

    @Override
    default Category createCategory(Category category) {
        return categoryMapper.mapToDomain(save(categoryMapper.mapToEntity(category)));
    }

    @Override
    default List<Category> getCategories() {
        return categoryMapper.mapToDomain(findAll());
    }

    default List<Category> findCategoryById(Long id) {
        return categoryMapper
                .mapToDomain(findAllById(List.of(id)));
    }

    @Override
    default boolean isExists(Long id) {
        return existsById(id);
    }

    @Override
    default void deleteCategoryById(Long id) {
        final List<Long> productIds = getProductIdConstraints(id);
        deleteOrderLinesByProductIds(productIds);
        deleteProductStocksByProductIds(productIds);
        deleteProductsByIds(productIds);
        deleteProductTypeByCategoryIds(id);
        deleteById(id);
    }

    @Query("SELECT " +
            "p.productId " +
            "FROM CategoryEntity c," +
            "ProductTypeEntity pt," +
            "ProductEntity p," +
            "ProductStockEntity ps " +
            "WHERE c.categoryId = pt.categoryEntity.categoryId "+
            "AND pt.productTypeId = p.productTypeEntity.productTypeId " +
            "AND ps.productEntity.productId = p.productId " +
            "AND c.categoryId = :categoryId")
    List<Long> getProductIdConstraints(@Param("categoryId") Long categoryId);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM OrderLineEntity ol WHERE ol.productEntity.productId in :productIds")
    void deleteOrderLinesByProductIds(@Param("productId") List<Long> productIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductStockEntity ps WHERE ps.productEntity.productId in :productIds")
    void deleteProductStocksByProductIds(@Param("productId") List<Long> productIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductEntity p WHERE p.productId in :productIds")
    void deleteProductsByIds(@Param("categoryId") List<Long> productIds);
    @Modifying // needed since this is not a select statement
    @Query("DELETE FROM ProductTypeEntity pte WHERE pte.categoryEntity.categoryId in :categoryId")
    void deleteProductTypeByCategoryIds(@Param("categoryId") Long categoryId);

    @Override
    default void updateCategories(List<Category> categories) {
        createCategories(categories);
    }

    @Override
    default Category updateCategory(Category category) {
        return createCategory(category);
    }

    @Override
    default void deleteCategory(Category category) {
        delete(categoryMapper.mapToEntity(category));
    }
}
