package org.skypro.skyshop.exception;

import org.skypro.skyshop.controller.ShopController;
import org.skypro.skyshop.model.dto.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.NoSuchProviderException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchProviderException.class)
    public ResponseEntity<ShopError> handleNoSuchProduct(NoSuchProviderException e) {
        ShopError error = new ShopError(
                "PRODUCT_NOT_FOUND",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ShopError> handleAllExceptions(Exception e) {
        ShopError error = new ShopError(
                "INTERNAL_SERVER_ERROR",
                "Произошла непредвиденная ошибка"
        );
        return ResponseEntity.internalServerError().body(error);
    }
}
