package DoelstellingDrie;

import java.util.ArrayList;

public class Player {
    ArrayList<String> playerHand;
    String name;
    int handValue;


    public Player(ArrayList<String> playerHand, String name, int handValue) {
        this.playerHand = playerHand;
        this.name = name;
        this.handValue = handValue;
    }

    public ArrayList<String> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<String> playerHand) {
        this.playerHand = playerHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }
}
