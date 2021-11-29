package ru.netology.SpringBoot_MoneyTransferService.service;

public class CvvCodeInvalidException extends RuntimeException {
    public CvvCodeInvalidException(String message) {
        super(message);
    }
}
