package DoelstellingTwee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Application {

    private static String flip = "House wins\n(╯°□°）╯︵ ┻━┻";

    public static void main(String[] args) {
        /*
        • Een speler kan meedoen met het spel. De speler kan door een 'k' in te voeren om een kaart
        vragen. Een speler kan altijd met heel het spel stoppen door 'q' in te voeren. De speler mag
        ook 'p' invoeren om te passen. V
        • Als een speler een kaart vraagt krijgt hij de bovenste kaart van de stapel. Het totaal aantal
        punten wordt getoont. V
        • De speler mag met 0 kaarten starten. De aas mag als een fixed 11 punten kaart beschouwd
        worden. V
        • Als de speler past, worden alle kaarten getoont die hij heeft met het totaal aantal punten van
        de kaarten.
        • De punten per kaart zijn: De waarde van het getal bij de 2, 3, 4, 5, 6, 7, 8, 9, 10. De Boer,
        Vrouw, Heer zijn 10 punten. De aas is zoals gezegd 11 punten.
         */
        Scanner userInput = new Scanner(System.in);

        ArrayList<String> cards = new ArrayList<>();
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();

        boolean quit = false;

        System.out.println("Start game?\n(yes or no)");
        if (getFreshDeckOfCards(cards, quit)) {
            quit = true;
        }

        while (!quit) {
            System.out.println("Press 'h' to get a card.\nPress 'p' to pass.\nPress 'q' to quit.");
            String userChoice = userInput.next().toLowerCase();

            switch (userChoice) {
                case "q":
                    quit = true;
                    System.out.println(flip);
                    break;
                case "p":
                    quit = true;
                    System.out.println("Player passes");
                    System.out.println("Player has " + getHandValue(playerHand) + " and the following cards: " + playerHand.toString());
                    if (getHandValue(dealerHand) < getHandValue(playerHand) || (getHandValue(dealerHand) == 0)) {
                        for (int i = getHandValue(dealerHand); i < getHandValue(playerHand) || (i == 0); i = getHandValue(dealerHand)) {
                            System.out.println("House gets a card");
                            showCardAndAddToHand(cards, dealerHand);
                            if (checkIfValueOver21(getHandValue(dealerHand))) {
                                break;
                            }
                        }
                    }
                    System.out.println(announceWinner(playerHand, dealerHand, getHandValue(playerHand), getHandValue(dealerHand)));
                    break;
                case "h":
                    System.out.println("You get a card");
                    showCardAndAddToHand(cards, playerHand);
                    if (checkIfValueOver21(getHandValue(playerHand))) {
                        System.out.println("Player has over 21");
                        System.out.println(announceWinner(playerHand, dealerHand, getHandValue(playerHand), getHandValue(dealerHand)));
                        System.out.println();
                        quit = true;
                        break;
                    }
                    if ((getHandValue(dealerHand)) < 21) {
                        System.out.println("Dealer gets a card");
                        showCardAndAddToHand(cards, dealerHand);
                        if (checkIfValueOver21(getHandValue(dealerHand))) {
                            System.out.println("Dealer has over 21");
                            System.out.println(announceWinner(playerHand, dealerHand, getHandValue(playerHand), getHandValue(dealerHand)));
                            quit = true;
                            break;
                        }
                    } else if (getHandValue(dealerHand) == 21){
                        System.out.println("House stands");
                    }
                    break;
            }
        }
    }

    private static boolean checkIfValueOver21(int handValue) {
        if (handValue > 21) {
            return true;
        } else {
            return false;
        }
    }

    private static String announceWinner(ArrayList<String> playerHand, ArrayList<String> dealerHand, int playerValue, int dealerValue) {
        String winner = null;

        if ((playerValue > dealerValue) && (playerValue < 22) || ((playerValue <= 21) && (dealerValue > 21))) {
            winner = "Player has " + getHandValue(playerHand) + "\nPlayer wins";
        } else if (playerValue > 21) {
            winner = "House has " + getHandValue(dealerHand) + "\n" + flip;
        } else if (dealerValue > 21) {
            winner = "Player has " + getHandValue(playerHand) + "\nPlayer wins";
        } else if (playerValue < dealerValue) {
            winner = "House has " + getHandValue(dealerHand) + "\n" + flip;

        } else if ((playerValue == dealerValue) && playerHand.size() > dealerHand.size()) {
            winner = "Player has " + getHandValue(playerHand) + "\nPlayer has more cards"+ "\nPlayer wins";
        } else if ((playerValue == dealerValue) && playerHand.size() < dealerHand.size()) {
            winner = "House has " + getHandValue(dealerHand) + "\nHouse has more cards" + "\n" + flip;
        } else if ((playerValue == dealerValue) && (playerHand.size() == dealerHand.size())) {
            winner = "Player has " + getHandValue(playerHand) + "\nHouse has " + getHandValue(dealerHand) + "\nIt's a draw";
        }
        return winner;
    }

    private static int getHandValue(ArrayList<String> playerCards) {
        int playerCardsValue = 0;
        for (String card : playerCards) {
            for (int i = 2; i < 11; i++) {
                if (card.startsWith(String.valueOf(i))) {
                    playerCardsValue += i;
                }
            }
            for (int i = 11; i < 14; i++) {
                if (card.startsWith(String.valueOf(i))) {
                    playerCardsValue += 10;
                }
            }
            if (card.startsWith("14")) {
                playerCardsValue += 11;
            }
        }
        return playerCardsValue;
    }

    private static void showCardAndAddToHand(ArrayList<String> cards, ArrayList<String> dealtCards) {
        dealtCards.add(cards.get(0));

        cards.remove(0);

        System.out.println(dealtCards);
    }

    private static boolean getFreshDeckOfCards(ArrayList<String> cards, boolean quit) {
        Scanner userInput = new Scanner(System.in);

        String userAnswer = userInput.next().toLowerCase();
        switch (userAnswer) {
            case ("yes"):
                for (int i = 2; i < 14; i++) {
                    cards.add(String.format("%sD", i));
                    cards.add(String.format("%sC", i));
                    cards.add(String.format("%sH", i));
                    cards.add(String.format("%sS", i));
                }
                System.out.println("Dealer shuffles the cards");
                shuffleCards(cards);
                shuffleCards(cards);
                shuffleCards(cards);
                System.out.println("Cards are shuffled\n" + cards);
                break;
            case ("no") :
                System.out.println("See ya");
                quit = true;
                break;
            default:
                System.out.println("Try again");
        }
        return quit;
    }

    private static void shuffleCards(ArrayList<String> cards) {
        Collections.shuffle(cards);
        System.out.println(cards);
    }
}
