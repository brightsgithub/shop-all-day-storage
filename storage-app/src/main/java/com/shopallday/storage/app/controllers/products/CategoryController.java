package com.shopallday.storage.app.controllers.products;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CategoryDto;
import com.shopallday.storage.app.services.products.CategoryService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "storage/v1/category")
@Tag(name = "Category", description = "Endpoints for managing categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final Mapper<Category, CategoryDto> mapper;

    public CategoryController(CategoryService categoryService, Mapper<Category, CategoryDto> mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

    @Operation(summary = "Get a category by ID")
    @GetMapping(path = "{id}")
    public ResponseEntity getCategoryById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Delete a category by ID")
    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteCategoryById(@PathVariable("id") Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    public ResponseEntity createCategory(@RequestBody final CategoryDto categoryDto) {
        try {
            return new ResponseEntity(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
        } catch (CreateException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a category by ID")
    @PutMapping(path = "{id}")
    public ResponseEntity updateCategory(
            @PathVariable("id") Long id,
            @RequestBody final CategoryDto categoryDto
    ) {
        try {
            categoryDto.setCategoryId(id);
            return new ResponseEntity(categoryService.updateCategory(categoryDto), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Partially update a category by ID")
    @PatchMapping(path = "{id}")
    public ResponseEntity partiallyUpdateCategory(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(categoryService.partialUpdateCategory(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
