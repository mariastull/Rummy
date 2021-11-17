package Simulation;
/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/
import java.util.ArrayList;
import java.util.Random;

import CardsAndPiles.CardPile;
import CardsAndPiles.Card;
import CardsAndPiles.CardSuit;

public class GameBoard {

    Random rand;

    CardPile drawPile;
    CardPile discardPile;

    public GameBoard() {
        // initialize deck
        ArrayList<Card> deck = new ArrayList<Card>();
        for(int i = 0; i < 13; i++) {
            deck.add(new Card(CardSuit.Hearts, i));
            deck.add(new Card(CardSuit.Diamonds, i));
            deck.add(new Card(CardSuit.Spades, i));
            deck.add(new Card(CardSuit.Clubs, i));
        }

        // pick a random discard card
        rand = new Random();
        int randomValue = rand.nextInt(deck.size());
        ArrayList<Card> oneCard = new ArrayList<Card>();
        oneCard.add(deck.get(randomValue));
        deck.remove(randomValue);

        // create the piles now
        drawPile = new CardPile(deck);
        discardPile = new CardPile(oneCard);
    }

    Card[] getStartingHand() {
        //return 5 cards to begin playing with
        Card[] myHand = new Card[5];
        for(int i = 0; i < 5; i++) {
            myHand[i] = drawPile.takeTop();
        }
        return myHand;
    }

}