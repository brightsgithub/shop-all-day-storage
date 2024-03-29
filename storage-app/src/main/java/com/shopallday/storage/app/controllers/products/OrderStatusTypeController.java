package com.shopallday.storage.app.controllers.products;

import com.shopallday.storage.app.controllers.BaseController;
import com.shopallday.storage.app.models.OrderStatusTypeDto;
import com.shopallday.storage.app.services.products.OrderStatusTypeService;
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
@RequestMapping(path = "storage/v1/order-status-type")
public class OrderStatusTypeController extends BaseController {
        private final OrderStatusTypeService service;

        public OrderStatusTypeController(OrderStatusTypeService service) {
            this.service = service;
        }

        @GetMapping
        public List<OrderStatusTypeDto> get() {
            return service.getOrderStatusTypes();
        }

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
