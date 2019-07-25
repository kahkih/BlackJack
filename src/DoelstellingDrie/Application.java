package DoelstellingDrie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Application {

    public static String flip = "House wins\n(╯°□°）╯︵ ┻━┻";

    public static void main(String[] args) {

        /*
        Vanaf hier wordt het spannender. Het is vanaf deze doelstelling verplicht om met kaarten als
        objecten te werken. Kaart kaart = new Kaart(). De vorige doelstellingen mochten prima op
        andere wijzen volbracht worden, vanaf hier is Object Georiënteerd programmeren een
        vereiste. V
        • De speler krijgt twee kaarten toebedeeld wanneer hij start, zoals bij de werkelijke regels. Ook
        kan de Aas 1 of 11 punten waard zijn. Dat is afhankelijk van het gegeven of hij anders boven
        de 21 punten uit komt. V
        • Als een speler niet op tijd past, is de speler af.
         */
        Application application = new Application();
        application.playBlackJack();
    }

    private void playBlackJack() {
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> cardsInDeck = new ArrayList<>();

        ArrayList<Card> playerCards = new ArrayList<>();
        ArrayList<Card> dealerCards = new ArrayList<>();

        boolean quit = false;

        System.out.println("Enter you name");

        Player player = new Player(null, userInput.next(), 0);

        System.out.println("Start game?\n(yes or no)");
        if (getFreshDeckOfCards(cardsInDeck, quit)) {
            quit = true;
        }

        playerCards.add(new Card(cardsInDeck.get(0)));
        cardsInDeck.remove(0);
        playerCards.add(new Card(cardsInDeck.get(0)));
        cardsInDeck.remove(0);
        System.out.println("Player " + cardsInHand(playerCards));
        System.out.println("Player has " + getHandValue(playerCards) + " points");

        dealerCards.add(new Card(cardsInDeck.get(0)));
        cardsInDeck.remove(0);
        dealerCards.add(new Card(cardsInDeck.get(0)));
        cardsInDeck.remove(0);
        System.out.println("House " + cardsInHand(dealerCards));
        System.out.println("House has " + getHandValue(dealerCards) + " points");

        while (!quit) {
            System.out.println("Press 'h' to get a card.\nPress 'p' to pass.\nPress 'q' to quit.");
            String userChoice = userInput.next().toLowerCase();

            switch (userChoice) {
                case ("h"):
                    playerCards.add(new Card(cardsInDeck.get(0)));
                    System.out.println("Player gets " + cardsInDeck.get(0));
                    System.out.println("Player has " + getHandValue(playerCards) + " points");
                    cardsInDeck.remove(0);
                    if (checkIfValueOver21(getHandValue(playerCards))) {
                        System.out.println("Player has over 21");
                        System.out.println(announceWinner(playerCards, dealerCards, getHandValue(playerCards), getHandValue(dealerCards)));
                        System.out.println();
                        quit = true;
                        break;
                    }
                    if ((getHandValue(dealerCards)) < 21) {
                        dealerCards.add(new Card(cardsInDeck.get(0)));
                        System.out.println("House gets " + cardsInDeck.get(0));
                        System.out.println("House has " + getHandValue(dealerCards) + " points");
                        cardsInDeck.remove(0);
                        if (checkIfValueOver21(getHandValue(dealerCards))) {
                            System.out.println("House has over 21");
                            System.out.println(announceWinner(playerCards, dealerCards, getHandValue(playerCards), getHandValue(dealerCards)));
                            System.out.println();
                            quit = true;
                            break;
                        } else if (getHandValue(dealerCards) == 21) {
                            System.out.println("House stands");
                        }
                        break;

                    }
                case ("p"):
                    quit = true;
                    System.out.println("Player passes");
                    System.out.println("Player " + cardsInHand(playerCards));
                    System.out.println("Player has " + getHandValue(playerCards) + " points");

                    System.out.println("House " + cardsInHand(dealerCards));
                    System.out.println("House has " + getHandValue(dealerCards) + " points");
                    if (getHandValue(dealerCards) < getHandValue(playerCards) || (getHandValue(dealerCards) == 0)) {
                        for (int i = getHandValue(dealerCards); i < getHandValue(playerCards) || (i == 0); i = getHandValue(dealerCards)) {
                            System.out.println("House gets a card");
                            dealerCards.add(new Card(cardsInDeck.get(0)));
                            System.out.println("House " + cardsInHand(dealerCards));
                            cardsInDeck.remove(0);
                            if (checkIfValueOver21(getHandValue(dealerCards))) {
                                break;
                            }
                        }
                    }
                    System.out.println(announceWinner(playerCards, dealerCards, getHandValue(playerCards), getHandValue(dealerCards)));

                    break;
                case ("q"):
                    quit = true;
                    System.out.println(flip);
                    break;
            }
        }
    }

    private static String announceWinner(ArrayList<Card> playerHand, ArrayList<Card> dealerHand, int playerValue, int dealerValue) {
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
            winner = "Player has " + getHandValue(playerHand) + "\nPlayer has more cards" + "\nPlayer wins";
        } else if ((playerValue == dealerValue) && playerHand.size() < dealerHand.size()) {
            winner = "House has " + getHandValue(dealerHand) + "\nHouse has more cards" + "\n" + flip;
        } else if ((playerValue == dealerValue) && (playerHand.size() == dealerHand.size())) {
            winner = "Player has " + getHandValue(playerHand) + "\nHouse has " + getHandValue(dealerHand) + "\nIt's a draw";
        }
        return winner;
    }

    private boolean checkIfValueOver21(int handValue) {
        if (handValue > 21) {
            return true;
        } else {
            return false;
        }
    }

    private String cardsInHand(ArrayList<Card> playerCards) {
        String cards = "has ";

        for (Card drawnCard : playerCards) {
            cards = cards + " " + drawnCard.cardName;
        }
        return cards;
    }

    private static int getHandValue(ArrayList<Card> playerCards) {
        int playerCardsValue = 0;
        for (Card card : playerCards) {
            for (int i = 2; i < 11; i++) {
                if (card.cardName.startsWith(String.valueOf(i))) {
                    playerCardsValue += i;
                }
            }
            for (int i = 11; i < 14; i++) {
                if (card.cardName.startsWith(String.valueOf(i))) {
                    playerCardsValue += 10;
                }
            }
        }
        for (Card card : playerCards) {
            if (card.cardName.startsWith("14") && (playerCardsValue < 11)) {
                playerCardsValue += 11;
            } else if (card.cardName.startsWith("14") && (playerCardsValue > 10)) {
                playerCardsValue += 1;
            }
        }
        return playerCardsValue;
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
            case ("no"):
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
