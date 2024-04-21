package com.shopallday.storage.app.controllers.orders;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.models.OrderLineDto;
import com.shopallday.storage.app.services.orders.OrderLinesService;
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
@RequestMapping(path = "storage/v1/order-lines")
@Tag(name = "Order lines", description = "Endpoints for managing customers order lines")
public class OrderLinesController extends BaseController {

    private final OrderLinesService service;

    public OrderLinesController(OrderLinesService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all order lines", description = "Retrieve a list of all order lines.")
    public List<OrderLineDto> get() {
        return service.getOrderLines();
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Get order line by ID", description = "Retrieve an order line by its ID.")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getOrderLineById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete order line by ID", description = "Delete an order line by its ID.")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            service.deleteOrderLineById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create order line", description = "Create a new order line.")
    public ResponseEntity create(@RequestBody final OrderLineDto orderLineDto) {
        try {
            return new ResponseEntity(service.createOrderLine(orderLineDto), HttpStatus.CREATED);
        } catch (CreateException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(path = "{id}")
    @Operation(summary = "Update order line", description = "Update an existing order line.")
    public ResponseEntity update(
            @PathVariable("id") Long id,
            @RequestBody final OrderLineDto orderLineDto
    ) {
        try {
            orderLineDto.setOrderLinesId(id);
            return new ResponseEntity(service.updateOrderLine(orderLineDto), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "{id}")
    @Operation(summary = "Partially update order line", description = "Partially update an existing order line.")
    public ResponseEntity partiallyUpdate(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(service.partiallyUpdateOrderLine(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
