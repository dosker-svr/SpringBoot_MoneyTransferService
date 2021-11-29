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
import java.util.Map;

@RestController
@Validated
public class MoneyTransferController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public String getInterface() {
        return "card-transfer";
    }

    @PostMapping
    public String postCardTransfer(@Valid @RequestBody TransferTransaction transaction) {
        /*@Valid @CardArgument Card card
                                   @RequestParam(name = "cardFromNumber") String cardFromNumber,
                                   @RequestParam(name = "cardFromValidTill") String cardFromValidTill,
                                   @RequestParam(name = "cardFromCVV") String cardFromCVV,
                                   @RequestParam(name = "cardToNumber") String cardToNumber,
                                   @RequestParam(name = "amount") Integer amount*/


        System.out.println("Какая карта пришла:");
        System.out.println(transaction.getCardFromNumber() + "|||" + transaction.getCardFromValidTill() + "|||" + transaction.getCardFromCVV() + "|||" + transaction.getCardToNumber() + "|||" + transaction.getAmount());
        Card cardSender = cardService.checkSenderCard(
                transaction.getCardFromNumber(),
                transaction.getCardFromValidTill(),
                transaction.getCardFromCVV());
        Card cardRecipient = cardService.checkRecipientCard(transaction.getCardToNumber());
        cardService.transferOfAmount(cardSender, cardRecipient, Double.valueOf(transaction.getAmount()));

        return "Успешный платёж";
    }
}
