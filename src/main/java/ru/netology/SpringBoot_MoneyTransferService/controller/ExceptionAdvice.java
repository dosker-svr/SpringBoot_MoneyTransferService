package ru.netology.SpringBoot_MoneyTransferService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.SpringBoot_MoneyTransferService.model.ExceptionResponse;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.*;

@RestControllerAdvice
public class ExceptionAdvice {
    private final Integer SOME_ID_400 = 400;
    private final Integer SOME_ID_500 = 500;

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerCardNotFound(CardNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getLocalizedMessage(), SOME_ID_400));
    }

    @ExceptionHandler(CvvCodeInvalidException.class)
    public ResponseEntity<ExceptionResponse> handlerCvvCodeInvalid(CvvCodeInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getLocalizedMessage(), SOME_ID_400));
    }

    @ExceptionHandler(ExpirationDateNotFoundException.class)
    public ResponseEntity<Object> handlerExpirationDateNotFound(ExpirationDateNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getLocalizedMessage(), SOME_ID_400));
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ExceptionResponse> handlerNotEnoughMoney(NotEnoughMoneyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getLocalizedMessage(), SOME_ID_400));
    }

    @ExceptionHandler(ErrorTransferException.class)
    public ResponseEntity<ExceptionResponse> handlerErrorTransfer(ErrorTransferException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(ex.getLocalizedMessage(), SOME_ID_500));
    }
}
