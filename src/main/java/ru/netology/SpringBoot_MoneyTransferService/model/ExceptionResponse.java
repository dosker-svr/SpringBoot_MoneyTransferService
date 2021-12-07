package ru.netology.SpringBoot_MoneyTransferService.model;

import org.springframework.web.bind.annotation.ResponseBody;

public class ExceptionResponse {
    private String message;
    private Integer id;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, Integer id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
