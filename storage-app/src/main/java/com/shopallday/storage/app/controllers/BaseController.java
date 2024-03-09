package com.shopallday.storage.app.controllers;

import com.shopallday.storage.domain.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

     public ResponseEntity getErrorResponse(BusinessException e, HttpStatus httpStatus) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(e);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}
