/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/
import java.util.ArrayList;
import java.util.Random;

public class GameBoard {

    Random rand;

    CardPile drawPile;
    CardPile discardPile;

    void GameBoard() {
        // initialize deck
        for(int i = 0; i < 13; i++) {
            Card cardToAdd(CardSuit.Hearts, i);
            drawPile.cards.add(cardToAdd);
            Card cardToAdd(CardSuit.Diamonds, i);
            drawPile.cards.add(cardToAdd);
            Card cardToAdd(CardSuit.Spades, i);
            drawPile.cards.add(cardToAdd);
            Card cardToAdd(CardSuit.Clubs, i);
            drawPile.cards.add(cardToAdd);
        }

        // get random discard card
        int randomValue = rand.nextInt(drawPile.size());
        Card randomCard = drawPile.get(randomValue);
        discardPile.add(randomCard);
        drawPile.remove(randomValue);
    }

    ArrayList<Card> getStartingHand() {
        //return 5 cards to begin playing with
        ArrayList<Card> myHand = new ArrayList<Card>();
        for(int i = 0; i < 5; i++) {
            int randomValue = rand.nextInt(drawPile.size());
            Card randomCard = drawPile.get(randomValue);
            myHand.add(randomCard);
            drawPile.remove(randomValue);
        }
        return myHand;
    }

}