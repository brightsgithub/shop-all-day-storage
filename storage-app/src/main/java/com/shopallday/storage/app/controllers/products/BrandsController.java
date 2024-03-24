package com.shopallday.storage.app.controllers.products;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.BrandDto;
import com.shopallday.storage.app.services.products.BrandsService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(path = "storage/v1/brands")
public class BrandsController extends BaseController {

    private final BrandsService brandsService;
    private final Mapper<Brand, BrandDto> mapper;

    public BrandsController(BrandsService brandsService, Mapper<Brand, BrandDto> mapper) {
        this.brandsService = brandsService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<BrandDto> getBrands() {
        return brandsService.getBrands();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getBrandById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(brandsService.getBrandById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteBrandById(@PathVariable("id") Long id) {
        try {
            brandsService.deleteBrandById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity createBrand(@RequestBody final BrandDto brandDto) {
        try {
            return new ResponseEntity(brandsService.createBrand(brandDto), HttpStatus.CREATED);
        } catch (CreateException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateBrand(
            @PathVariable("id") Long id,
            @RequestBody final BrandDto brandDto
    ) {
        try {
            brandDto.setBrandId(id);
            return new ResponseEntity(brandsService.updateBrand(brandDto), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity partiallyUpdateBrand(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(brandsService.partialUpdateBrand(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
