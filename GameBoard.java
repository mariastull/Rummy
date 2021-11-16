/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/
import java.util.ArrayList;

public class GameBoard {

    CardPile fullDeck;

    CardPile drawPile;
    CardPile discardPile;

    void GameBoard() {
        // initialize deck
        for(int i = 0; i < 13; i++) {
            Card cardToAdd(CardSuit.Hearts, i);
            fullDeck.cards.add(cardToAdd);
            Card cardToAdd(CardSuit.Diamonds, i);
            fullDeck.cards.add(cardToAdd);
            Card cardToAdd(CardSuit.Spades, i);
            fullDeck.cards.add(cardToAdd);
            Card cardToAdd(CardSuit.Clubs, i);
            fullDeck.cards.add(cardToAdd);
        }
    }

    Card peekTopCard() {}
    Card drawCard(Bool isFromDiscard) {}
    void discardCard(Card toDiscard) {}
}