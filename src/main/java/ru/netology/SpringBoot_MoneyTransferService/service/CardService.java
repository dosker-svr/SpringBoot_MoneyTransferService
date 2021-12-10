package ru.netology.SpringBoot_MoneyTransferService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.SpringBoot_MoneyTransferService.model.Card;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransaction;
import ru.netology.SpringBoot_MoneyTransferService.repository.CardsRepository;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.CardNotFoundException;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.CvvCodeInvalidException;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.ExpirationDateNotFoundException;
import ru.netology.SpringBoot_MoneyTransferService.service.exceptions.NotEnoughMoneyException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CardService {
    public static final String LOG_FILE = "./easyLog/easyLog.txt";
    public static final Double COMMISSION = 0.01;
    private static BufferedWriter writer;

    static {
        try {
            File file = new File(LOG_FILE);
            if (!file.exists()) {
                new File("./easyLog").mkdir();
                boolean isCreated = file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
                    //src/main/resources/log/log.txt
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private CardsRepository cardRepository;

    public Card checkSenderCard(TransferTransaction transaction) {
        String cardFromNumber = transaction.getCardFromNumber();
        String cardFromValidTill = transaction.getCardFromValidTill();
        String cardFromCVV = transaction.getCardFromCVV();

        try {
            writer.write((dateTime() +
                    "Attempted transaction.\n" +
                    "From Card Number: " + transaction.getCardFromNumber() + ", " +
                    "Date till: " + transaction.getCardFromValidTill() + ", " +
                    "CVV: " + transaction.getCardFromCVV() + ", " +
                    "To Card Number: " + transaction.getCardToNumber() + ", " +
                    "Amount: " + (transaction.getAmount().getValue() / 100) + " " + transaction.getAmount().getCurrency() + ".\n"));
        } catch (IOException ex) {

        }

        Card cardSender = cardRepository.findCard(cardFromNumber);

        if (cardSender == null) {
            try {
                writer.write("Card Sender (" + transaction.getCardFromNumber() + ") NOT FOUND.\n");
            } catch (IOException ex) {

        }
            System.out.println("The card not found!!!!!!!");
            throw new CardNotFoundException("Карта отправителя платежа с номером *" + cardFromNumber.substring(12) + " не найдена");
        }

        if (!cardSender.getCardValidTill().equals(cardFromValidTill)) {
            try {
                writer.write("Expiration Date (" + transaction.getCardFromValidTill() + ") IS INVALID.\n");
            } catch (IOException ex) {

            }
            System.out.println("Expiration date is invalid!!!!!!!");
            throw new ExpirationDateNotFoundException("Неверно указан срок действия карты");
        }

        if (!cardSender.getCardCVV().equals(cardFromCVV)) {
            try {
                writer.write("CVV code (" + transaction.getCardFromCVV() + ") IS INVALID.\n");
            } catch (IOException ex) {

            }
            System.out.println("CVV code is invalid!!!!!!!");
            throw new CvvCodeInvalidException("CVV код указан неверно");
        }

        return cardSender;
    }

    public Card checkRecipientCard(String cardNumber) {
        Card recipientCard = cardRepository.findCard(cardNumber);
        if (recipientCard == null) {
            try {
                writer.write("Card recipient (" + cardNumber + ") NOT FOUND.\n");
            } catch (IOException ex) {

            }
            System.out.println("The card recipient not found!!!!!!!");
            throw new CardNotFoundException("Карта получателя платежа с номером *" + cardNumber.substring(12) + " не найдена");
        }
        return recipientCard;
    }

    public boolean transferOfAmount(Card senderCard, Card recipientCard, TransferTransaction transaction) {
        Integer twoZero = 100;
        Integer amount = transaction.getAmount().getValue() / twoZero;

        Double amountDouble = Double.valueOf(amount);
        Double amountWithCommission = amountDouble + (amountDouble * CardService.COMMISSION);
        System.out.println("Amount of payment: " + amountWithCommission);

        if (senderCard.getCardAmount() - amountWithCommission < 0) {
            try {
                writer.write("Not enough money on the Сard: " + senderCard.getCardNumber() + ".\n");
            } catch (IOException ex) {

            }
            System.out.println("Not enough money on the card!!!!!!!");
            throw new NotEnoughMoneyException("На карте *" + senderCard.getCardNumber().substring(12) + " недостаточно средств");
        }

        boolean result = cardRepository.transferOfAmountFromSender(senderCard, recipientCard, amountDouble, amountWithCommission);

        try {
            writer.write("Размер списания: " + amountWithCommission + ".\n" +
                    "Сумма после спиания на счету отправителя *" + senderCard.getCardNumber().substring(12) + " : " + senderCard.getCardAmount() + ".\n" +
                    "Сумма после спиания на счету получателя *" + recipientCard.getCardNumber().substring(12) + " : " + recipientCard.getCardAmount() + ".\n");
        writer.flush();
        writer.close();
        } catch (IOException ex) {

        }
        transaction.setIdTransaction(TransferTransaction.idCounter++);

        return result;
    }

    private static String dateTime() {
        String DATETIME_format = "HH:mm:ss | dd-MM-yy";
        DateFormat dateFormat = new SimpleDateFormat(DATETIME_format);
        Date date = new Date();
        return (dateFormat.format(date) + " ==========================================================================================================\n");
    }
}
