package ru.netology.SpringBoot_MoneyTransferService.service;

public class ExpiredDateException extends RuntimeException {
    public ExpiredDateException(String message) {
        super(message);
    }
}
