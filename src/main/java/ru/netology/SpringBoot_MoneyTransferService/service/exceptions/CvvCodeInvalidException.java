package ru.netology.SpringBoot_MoneyTransferService.service.exceptions;

public class CvvCodeInvalidException extends RuntimeException {
    public CvvCodeInvalidException(String message) {
        super(message);
    }
}
