package ru.netology.SpringBoot_MoneyTransferService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.SpringBoot_MoneyTransferService.model.Card;
import ru.netology.SpringBoot_MoneyTransferService.repository.CardsRepository;

@Service
public class CardService {
    public static final Double COMMISSION = 0.01;

    @Autowired
    private CardsRepository cardRepository;

    public Card checkSenderCard(String cardFromNumber, String cardFromValidTill, String cardFromCVV) {
        Card cardSender = cardRepository.findCard(cardFromNumber);

        if (cardSender == null) {
            throw new CardNotFoundException("The card not found");
            // TODO: throw new CardNotFoundException("The card not found");
        }

        // TODO: добавить проверку срока действия карты

        if (!cardSender.getCardValidTill().equals(cardFromValidTill)) {
            throw new ExpiredDateException("The card has expired");
            /// TODO: throw new ExpiredDateException("The card has expired");
        }

        if (!cardSender.getCardCVV().equals(cardFromCVV)) {
            throw new CvvCodeInvalidException("CVV code is invalid");
            // TODO: throw new CvvCodeInvalidException("CVV code is invalid");
        }

        return cardSender;
    }

    public Card checkRecipientCard(String cardNumber) {
        Card recipientCard = cardRepository.findCard(cardNumber);
        if (recipientCard == null) {
            throw new CardNotFoundException("The card not found");
            // TODO: throw new CardNotFoundException("The card not found");
        }
        return recipientCard;
    }

    public boolean transferOfAmount(Card senderCard, Card recipientCard, Double amount) {
        Double amountWithCommission = amount + (amount * CardService.COMMISSION);
        System.out.println("Размер списания: " + amountWithCommission);
        if (senderCard.getCardAmount() - amountWithCommission < 0) {
            throw new InsufficientFundsException("Insufficient funds");
            // TODO: throw new InsufficientFundsException("Insufficient funds");
        }

        cardRepository.transferOfAmountFromSender(senderCard, recipientCard, amount, amountWithCommission);
        return true;
    }
}
