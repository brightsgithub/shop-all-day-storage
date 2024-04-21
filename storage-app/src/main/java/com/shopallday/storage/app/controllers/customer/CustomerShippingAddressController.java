package com.shopallday.storage.app.controllers.customer;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.models.CustomerShippingAddressDto;
import com.shopallday.storage.app.services.customer.CustomerShippingAddressService;
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
@RequestMapping(path = "storage/v1/shipping-address")
@Tag(name = "Customer Shipping Address", description = "Endpoints for managing multiple customer addresses")
public class CustomerShippingAddressController extends BaseController {

    private final CustomerShippingAddressService service;

    public CustomerShippingAddressController(CustomerShippingAddressService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all customer shipping addresses", description = "Retrieve a list of all customer shipping addresses.")
    public List<CustomerShippingAddressDto> get() {
        return service.getGetAll();
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Get customer shipping address by ID", description = "Retrieve a customer shipping address by its ID.")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete customer shipping address by ID", description = "Delete a customer shipping address by its ID.")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create customer shipping address", description = "Create a new customer shipping address.")
    public ResponseEntity create(@RequestBody final CustomerShippingAddressDto dto) {
        try {
            return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
        } catch (CreateException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(path = "{id}")
    @Operation(summary = "Update customer shipping address", description = "Update an existing customer shipping address.")
    public ResponseEntity update(
            @PathVariable("id") Long id,
            @RequestBody final CustomerShippingAddressDto dto
    ) {
        try {
            dto.setShippingAddressId(id);
            return new ResponseEntity(service.update(dto), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "{id}")
    @Operation(summary = "Partially update customer shipping address", description = "Partially update an existing customer shipping address.")
    public ResponseEntity partiallyUpdate(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(service.partiallyUpdate(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
