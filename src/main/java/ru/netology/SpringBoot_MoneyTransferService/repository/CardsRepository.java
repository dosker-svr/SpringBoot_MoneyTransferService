package ru.netology.SpringBoot_MoneyTransferService.repository;

import org.springframework.stereotype.Repository;
import ru.netology.SpringBoot_MoneyTransferService.model.Card;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CardsRepository {
    private final static Map<String, Card> cardMap = new HashMap<>();
    private static Double serviceProfit = 0.0;

    public boolean transferOfAmountFromSender(Card senderCard, Card recipientCard, Double amount, Double amountWithCommission) {
        serviceProfit = serviceProfit + (amountWithCommission - amount);

        senderCard.setCardAmount(senderCard.getCardAmount() - amountWithCommission);
        recipientCard.setCardAmount(recipientCard.getCardAmount() + amount);

        return true;
    }

    public Card findCard(String cardNumber) {
        Card findCard = cardMap.get(cardNumber);
        return findCard;
    }

    public static void createCards() {
        final Long baseNumCard = 4276_8440_2988_1000L;
        final String baseValidTill = "11/25";
        final String baseCVV = "00";
        final Double baseAmount = 1000.00;

        for (int i = 0; i < 10; i++ ) {
            Card card = new Card(
                    String.valueOf(baseNumCard + i),
                    baseValidTill,
                    (baseCVV + i));
            card.setCardAmount(baseAmount + (i * 100));
            cardMap.put(card.getCardNumber(), card);
            System.out.println("Создали карту - " + card);
        }

        Card cardWithExpiredDate = new Card(
                String.valueOf(4276_8440_2988_5000L),
                "11/19",
                String.valueOf(baseCVV + 100));
        cardWithExpiredDate.setCardAmount(baseAmount + 2000);
        cardMap.put(cardWithExpiredDate.getCardNumber(), cardWithExpiredDate);

        Card cardWithZeroAmount = new Card(
                String.valueOf(4276_8440_2988_6000L),
                baseValidTill,
                String.valueOf(baseCVV + 100));
        cardWithZeroAmount.setCardAmount(0.0);
        cardMap.put(cardWithZeroAmount.getCardNumber(), cardWithZeroAmount);
    }
}
