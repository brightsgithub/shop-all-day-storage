package com.shopallday.storage.app.controllers.customer;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.models.CustomerShippingAddressDto;
import com.shopallday.storage.app.services.customer.CustomerShippingAddressService;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "storage/v1/shipping-address")
public class CustomerShippingAddressController extends BaseController {

    private final CustomerShippingAddressService service;

    public CustomerShippingAddressController(CustomerShippingAddressService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerShippingAddressDto> get() {
        return service.getGetAll();
    }

    @GetMapping(path = "{id}")
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
    public ResponseEntity partiallyUpdate(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(service.partiallyUpdate(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
