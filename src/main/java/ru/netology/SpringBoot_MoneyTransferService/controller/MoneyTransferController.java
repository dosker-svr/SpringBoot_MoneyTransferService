package ru.netology.SpringBoot_MoneyTransferService.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import ru.netology.SpringBoot_MoneyTransferService.model.Card;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransaction;
import ru.netology.SpringBoot_MoneyTransferService.service.CardService;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@RestController
@CrossOrigin
@Validated
public class MoneyTransferController {
    @Autowired
    private CardService cardService;

    /*@PostMapping("/transfer")
    public String getGet(@RequestBody String text) {
        System.out.println(text);
        return "String";
    }*/

    @PostMapping("/transfer")
    public String postCardTransfer(@Valid @RequestBody TransferTransaction transaction) {
        System.out.print("Какая карта пришла:");
        System.out.println(transaction.getCardFromNumber() + "|||" + transaction.getCardFromValidTill() + "|||" + transaction.getCardFromCVV() + "|||" + transaction.getCardToNumber() + "|||" + transaction.getAmount().getValue());
        Card cardSender = null;
        Card cardRecipient = null;
        try {
            cardSender = cardService.checkSenderCard(transaction);
            cardRecipient = cardService.checkRecipientCard(transaction.getCardToNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (!cardService.transferOfAmount(cardSender, cardRecipient, transaction.getAmount().getValue())) {
                return "Operation failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "0000";
    }

    @PostMapping("/confirmOperation")
    public void postConfirmOperation() {
        //return "ConfirmOperation";
    }
}
