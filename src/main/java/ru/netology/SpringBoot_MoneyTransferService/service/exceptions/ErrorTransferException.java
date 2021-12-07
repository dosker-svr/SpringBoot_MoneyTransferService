package ru.netology.SpringBoot_MoneyTransferService.service.exceptions;

public class ErrorTransferException extends RuntimeException {
    public ErrorTransferException(String message) {
        super(message);
    }
}
