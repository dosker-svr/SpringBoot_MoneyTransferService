package ru.netology.SpringBoot_MoneyTransferService.model;

public class Card {
    private String cardNumber;
    private String cardValidTill;
    private String cardCVV;
    private Double cardAmount;

    public Card() {
    }

    public Card(String cardNumber, String cardValidTill, String cardCVV) {
        this.cardNumber = cardNumber;
        this.cardValidTill = cardValidTill;
        this.cardCVV = cardCVV;
        this.cardAmount = 0.0;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardFromNumber) {
        this.cardNumber = cardFromNumber;
    }

    public String getCardValidTill() {
        return cardValidTill;
    }

    public void setCardValidTill(String cardFromValidTill) {
        this.cardValidTill = cardFromValidTill;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardFromCVV) {
        this.cardCVV = cardFromCVV;
    }

    public Double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Double cardAmount) {
        this.cardAmount = cardAmount;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardValidTill='" + cardValidTill + '\'' +
                ", cardCVV='" + cardCVV + '\'' +
                ", cardAmount=" + cardAmount +
                '}';
    }
}
