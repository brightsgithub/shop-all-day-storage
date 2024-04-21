package com.shopallday.storage.app.controllers.orders;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.models.OrderDto;
import com.shopallday.storage.app.services.orders.OrdersService;
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

@Tag(name = "Orders", description = "Endpoints for managing orders")
@RestController
@RequestMapping(path = "storage/v1/orders")
public class OrdersController extends BaseController {

    private final OrdersService service;

    public OrdersController(OrdersService service) {
        this.service = service;
    }

    @Operation(summary = "Get all orders", description = "Retrieve a list of all orders.")
    @GetMapping
    public List<OrderDto> get() {
        return service.getOrders();
    }

    @Operation(summary = "Get customer order details", description = "Retrieve details of orders belonging to a specific customer.")
    @GetMapping(path = "customer-orders/{id}")
    public ResponseEntity getCustomerOrderDetails(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getCustomerOrderDetailsById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID.")
    @GetMapping(path = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getOrderById(id), HttpStatus.OK);
        } catch (ReadException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Delete order by ID", description = "Delete an order by its ID.")
    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            service.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DeleteException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create order", description = "Create a new order.")
    @PostMapping
    public ResponseEntity create(@RequestBody final OrderDto orderDto) {
        try {
            return new ResponseEntity(service.createOrder(orderDto), HttpStatus.CREATED);
        } catch (CreateException e) {
            return getErrorResponse(e, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update order", description = "Update an existing order.")
    @PutMapping(path = "{id}")
    public ResponseEntity update(
            @PathVariable("id") Long id,
            @RequestBody final OrderDto orderDto
    ) {
        try {
            orderDto.setOrderId(id);
            return new ResponseEntity(service.updateOrder(orderDto), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Partially update order", description = "Partially update an existing order.")
    @PatchMapping(path = "{id}")
    public ResponseEntity partiallyUpdate(
            @PathVariable("id") Long id,
            @RequestBody final Map<String, Object> fields
    ) {
        try {
            return new ResponseEntity(service.partiallyUpdateOrder(id, fields), HttpStatus.OK);
        } catch (ReadException | UpdateException e) {
            return getErrorResponse(e, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
