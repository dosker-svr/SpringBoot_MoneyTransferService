package ru.netology.SpringBoot_MoneyTransferService.model;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Positive;

public class Amount {
    private String currency;
    @Positive
    private Integer value;

    public Amount() {
    }

    public Amount(String currency, Integer value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
