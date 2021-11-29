package ru.netology.SpringBoot_MoneyTransferService.service;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
