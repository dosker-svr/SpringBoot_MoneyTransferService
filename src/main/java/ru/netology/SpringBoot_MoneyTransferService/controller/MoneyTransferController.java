package ru.netology.SpringBoot_MoneyTransferService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.SpringBoot_MoneyTransferService.model.Card;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransaction;
import ru.netology.SpringBoot_MoneyTransferService.service.CardService;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
@Validated
public class MoneyTransferController {
    @Autowired
    private CardService cardService;

    @PostMapping("/transfer")
    public String postCardTransfer(@Valid @RequestBody TransferTransaction transaction) {
        System.out.print("Какая карта пришла:");
        System.out.println(transaction.getCardFromNumber() + "|||" + transaction.getCardFromValidTill() + "|||" + transaction.getCardFromCVV() + "|||" + transaction.getCardToNumber() + "|||" + transaction.getAmount().getValue());
        Card cardSender = cardService.checkSenderCard(transaction);
        Card cardRecipient = cardService.checkRecipientCard(transaction.getCardToNumber());

        if (!cardService.transferOfAmount(cardSender, cardRecipient, transaction)) {
            throw new ErrorTransferException("Operation failed");
        }

        return "0000";
    }

    @PostMapping("/confirmOperation")
    public void postConfirmOperation() {
    }
}
