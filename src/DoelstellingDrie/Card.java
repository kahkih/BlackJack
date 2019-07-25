package DoelstellingDrie;

public class Card {
    String cardName;
    int cardValue;

    public Card(String cardName, int cardValue) {
        this.cardName = cardName;
        this.cardValue = cardValue;
    }

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName(String cardName) {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
