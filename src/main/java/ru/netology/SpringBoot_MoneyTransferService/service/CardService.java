package ru.netology.SpringBoot_MoneyTransferService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.SpringBoot_MoneyTransferService.model.Card;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransaction;
import ru.netology.SpringBoot_MoneyTransferService.repository.CardsRepository;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.CardNotFoundException;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.CvvCodeInvalidException;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.ExpirationDateNotFoundException;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.NotEnoughMoney;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CardService {
    public static final Double COMMISSION = 0.01;
    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter("src/main/resources/log/log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private CardsRepository cardRepository;

    public Card checkSenderCard(TransferTransaction transaction) throws IOException {
        String cardFromNumber = transaction.getCardFromNumber();
        String cardFromValidTill = transaction.getCardFromValidTill();
        String cardFromCVV = transaction.getCardFromCVV();

        writer.write((dateTime() +
                "Attempted transaction.\n" +
                "From Card Number: " + transaction.getCardFromNumber() + ", " +
                "Date till: " + transaction.getCardFromValidTill() + ", " +
                "CVV: " + transaction.getCardFromCVV() + ", " +
                "To Card Number: " + transaction.getCardToNumber() + ", " +
                "Amount: " + (transaction.getAmount().getValue() / 100) + " " + transaction.getAmount().getCurrency() + ".\n"));

        Card cardSender = cardRepository.findCard(cardFromNumber);

        if (cardSender == null) {
            writer.write("Card Sender (" + transaction.getCardFromNumber() + ") NOT FOUND.\n");
            throw new CardNotFoundException("The card not found");
            // TODO: throw new CardNotFoundException("The card not found");
        }

        if (!cardSender.getCardValidTill().equals(cardFromValidTill)) {
            writer.write("Expiration Date (" + transaction.getCardFromValidTill() + ") IS INVALID.\n");
            throw new ExpirationDateNotFoundException("Expiration date is invalid");
            /// TODO: throw new ExpirationDateNotFoundException("Expiration date is invalid");
        }

        if (!cardSender.getCardCVV().equals(cardFromCVV)) {
            writer.write("CVV code (" + transaction.getCardFromCVV() + ") IS INVALID.\n");
            throw new CvvCodeInvalidException("CVV code is invalid");
            // TODO: throw new CvvCodeInvalidException("CVV code is invalid");
        }

        return cardSender;
    }

    public Card checkRecipientCard(String cardNumber) throws IOException {
        Card recipientCard = cardRepository.findCard(cardNumber);
        if (recipientCard == null) {
            writer.write("Card recipient (" + cardNumber + ") NOT FOUND.\n");
            throw new CardNotFoundException("The card recipient not found");
            // TODO: throw new CardNotFoundException("The card ecipient not found");
        }
        return recipientCard;
    }

    public boolean transferOfAmount(Card senderCard, Card recipientCard, Integer amount) throws IOException {
        Integer twoZero = 100;
        amount = amount / twoZero;

        Double amountDouble = Double.valueOf(amount);
        Double amountWithCommission = amountDouble + (amountDouble * CardService.COMMISSION);
        System.out.println("Размер списания: " + amountWithCommission);

        if (senderCard.getCardAmount() - amountWithCommission < 0) {
            writer.write("Not enough money on the Сard: " + senderCard.getCardNumber() + ".\n");
            throw new NotEnoughMoney("Not enough money on the card");
            // TODO: throw new NotEnoughMoney("Not enough money on the card");
        }

        writer.write("Размер списания: " + amountWithCommission + ".\n" +
                "Сумма после спиания на счету отправителя *" + senderCard.getCardNumber().substring(12) + " : " + senderCard.getCardAmount() + ".\n" +
                "Сумма после спиания на счету получателя *" + recipientCard.getCardNumber().substring(12) + " : " + recipientCard.getCardAmount() + ".\n");
        writer.flush();
        writer.close();

        return cardRepository.transferOfAmountFromSender(senderCard, recipientCard, amountDouble, amountWithCommission);
    }

    private static String dateTime() {
        String DATETIME_format = "HH:mm:ss | yy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(DATETIME_format);
        Date date = new Date();
        return (dateFormat.format(date) + " ==========================================================================================================\n");
    }
}
