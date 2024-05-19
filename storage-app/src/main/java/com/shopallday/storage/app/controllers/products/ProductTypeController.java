package com.shopallday.storage.app.controllers.products;

        import com.shopallday.storage.app.controllers.BaseController;
        import com.shopallday.storage.app.models.ProductTypeDto;
        import com.shopallday.storage.app.services.products.ProductTypeService;
        import com.shopallday.storage.domain.exceptions.crud.CreateException;
        import com.shopallday.storage.domain.exceptions.crud.DeleteException;
        import com.shopallday.storage.domain.exceptions.crud.ReadException;
        import com.shopallday.storage.domain.exceptions.crud.UpdateException;
        import io.swagger.v3.oas.annotations.Operation;
        import io.swagger.v3.oas.annotations.tags.Tag;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Map;

@RestController
@RequestMapping(path = "storage/v1/product-type")
@Tag(name = "Product Type", description = "Endpoints for managing product types")
public class ProductTypeController extends BaseController {

    private final ProductTypeService service;

    public ProductTypeController(ProductTypeService service) {
        this.service = service;
    }

    @Operation(summary = "Get all product types")
    @GetMapping
    public List<ProductTypeDto> get() {
        return service.getProductTypes();
    }

    @Operation(summary = "Get a product type by ID")
    @GetMapping(path = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getProductTypeById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Delete a product type by ID")
    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            service.deleteProductTypeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create a new product type")
    @PostMapping
    public ResponseEntity create(@RequestBody final ProductTypeDto productTypeDto) {
        try {
            return new ResponseEntity(service.createProductType(productTypeDto), HttpStatus.CREATED);
        } catch (CreateException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a product type by ID")
    @PutMapping(path = "{id}")
    public ResponseEntity update(
            @PathVariable("id") Long id,
            @RequestBody final ProductTypeDto productTypeDto
    ) {
        try {
            productTypeDto.setProductTypeId(id);
            return new ResponseEntity(service.updateProductType(productTypeDto), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Partially update a product type by ID")
    @PatchMapping(path = "{id}")
    public ResponseEntity partiallyUpdate(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(service.partiallyUpdateProductType(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get all product types by Category id")
    @GetMapping(path = "category/{id}")
    public ResponseEntity<List<ProductTypeDto>> getProductTypesByCategoryId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getProductTypesByCategoryId(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
