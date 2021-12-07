package ru.netology.SpringBoot_MoneyTransferService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.SpringBoot_MoneyTransferService.model.Amount;
import ru.netology.SpringBoot_MoneyTransferService.model.Card;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransaction;
import ru.netology.SpringBoot_MoneyTransferService.repository.CardsRepository;
import ru.netology.SpringBoot_MoneyTransferService.service.CardService;

@SpringBootTest
public class CardServiceTest {
    @Autowired
    private CardService cardService;

    private static TransferTransaction transaction;
    private static String recipientCardNumber;

    @BeforeAll
    public static void createCards() {
        CardsRepository.createCards();
        recipientCardNumber = "4276844029881002";
        transaction = new TransferTransaction(
                "4276844029881001",
                "11/25",
                "001",
                recipientCardNumber,
                new Amount("RUR", 50000));
    }


    @Test
    public void checkSenderCardTest() {
        // When:
        Card senderCard = cardService.checkSenderCard(transaction);
        // Then:
        Assertions.assertNotNull(senderCard);
    }

    @Test
    public void checkRecipientCardTest() {
        // When:
        Card recipientCard = cardService.checkRecipientCard(recipientCardNumber);
        // Then:
        Assertions.assertNotNull(recipientCard);
    }

    @Test
    public void transferOfAmountTest() {
        // Given:
        Card senderCard = cardService.checkSenderCard(transaction);
        Card recipientCard = cardService.checkRecipientCard(recipientCardNumber);

        // Then:
        Assertions.assertTrue(cardService.transferOfAmount(senderCard, recipientCard, transaction));
    }
}
