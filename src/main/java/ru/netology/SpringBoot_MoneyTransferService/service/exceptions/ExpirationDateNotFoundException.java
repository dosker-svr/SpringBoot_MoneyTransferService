package ru.netology.SpringBoot_MoneyTransferService.service.exceptions;

public class ExpirationDateNotFoundException extends RuntimeException {
    public ExpirationDateNotFoundException(String message) {
        super(message);
    }
}
