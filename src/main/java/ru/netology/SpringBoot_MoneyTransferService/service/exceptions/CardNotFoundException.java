package ru.netology.SpringBoot_MoneyTransferService.service.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
