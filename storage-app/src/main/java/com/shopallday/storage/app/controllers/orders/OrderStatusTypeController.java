package com.shopallday.storage.app.controllers.orders;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.models.OrderStatusTypeDto;
import com.shopallday.storage.app.services.orders.OrderStatusTypeService;
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

@Tag(name = "Order Status Type", description = "Endpoints for managing order status types")
@RestController
@RequestMapping(path = "storage/v1/order-status-type")
public class OrderStatusTypeController extends BaseController {
    private final OrderStatusTypeService service;

    public OrderStatusTypeController(OrderStatusTypeService service) {
        this.service = service;
    }

    @Operation(summary = "Get all order status types", description = "Retrieve a list of all order status types.")
    @GetMapping
    public List<OrderStatusTypeDto> get() {
        return service.getOrderStatusTypes();
    }

    @Operation(summary = "Get order status type by ID", description = "Retrieve an order status type by its ID.")
    @GetMapping(path = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getOrderStatusTypeById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Delete order status type by ID", description = "Delete an order status type by its ID.")
    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            service.deleteOrderStatusTypeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create order status type", description = "Create a new order status type.")
    @PostMapping
    public ResponseEntity create(@RequestBody final OrderStatusTypeDto orderStatusTypeDto) {
        try {
            return new ResponseEntity(service.createOrderStatusType(orderStatusTypeDto), HttpStatus.CREATED);
        } catch (CreateException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update order status type", description = "Update an existing order status type.")
    @PutMapping(path = "{id}")
    public ResponseEntity update(
            @PathVariable("id") Long id,
            @RequestBody final OrderStatusTypeDto orderStatusTypeDto
    ) {
        try {
            orderStatusTypeDto.setOrderStatusTypeId(id);
            return new ResponseEntity(service.updateOrderStatusType(orderStatusTypeDto), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Partially update order status type", description = "Partially update an existing order status type.")
    @PatchMapping(path = "{id}")
    public ResponseEntity partiallyUpdate(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(service.partiallyUpdateOrderStatusType(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}