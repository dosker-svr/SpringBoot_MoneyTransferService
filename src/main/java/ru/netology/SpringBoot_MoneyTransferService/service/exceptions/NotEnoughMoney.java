package ru.netology.SpringBoot_MoneyTransferService.service.exceptions;

public class NotEnoughMoney extends RuntimeException {
    public NotEnoughMoney(String message) {
        super(message);
    }
}
