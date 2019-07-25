package BlackJack.DoelstellingEen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        /*
        Elke Black Jack begint met het tonen van de geschudde kaarten.
        • De Black Jack spelen we met 1 spel van 52 kaarten. Alle kaarten zitten dus 1 keer in het
        overzicht, en als het spel opnieuw gestart wordt is de volgorde in een random nieuwe
        volgorde.
         */

        ArrayList<String> cards = new ArrayList<>();

        System.out.println("Start game?");

        getFreshDeckOfCards(cards);

        shuffleTheCards(cards);

        System.out.println("Shuffle was a success\n" + cards);
    }

    private static void getFreshDeckOfCards(ArrayList<String> cards) {
        Scanner userInput = new Scanner(System.in);

        boolean startGame;

        if(userInput.next().toLowerCase().equals("yes")) {
            startGame = true;
            if (startGame) {
                for(int i = 2; i < 14; i++) {
                    cards.add(String.format("%sD", i));
                    cards.add(String.format("%sC", i));
                    cards.add(String.format("%sH", i));
                    cards.add(String.format("%sS", i));
                }
                System.out.println("This is a fresh deck of cards\n" + cards);
            }
        }
    }

    private static void shuffleTheCards(ArrayList<String> cards) {
        Scanner userInput = new Scanner(System.in);

        boolean shuffle = true;

        while (shuffle == true) {
            System.out.println("Shuffle?");

            if (userInput.next().toLowerCase().equals("yes")) {
                shuffle = true;
                if (shuffle) {
                    Collections.shuffle(cards);
                }
                System.out.println(cards);
            } else {
                shuffle = false;
            }
        }

    }
}
