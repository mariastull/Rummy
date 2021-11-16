package CardsAndPiles;
import java.util.ArrayList;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class CardPile {
    ArrayList<Card> cards;

    public CardPile() {
        cards = new ArrayList<Card>();
    }

    public void shufflePile() {
        
    }

    Card peekTopCard() {
        return discardPile.get(0);
    }

    public Card takeTop() {}
    public void discardCard(Card toDiscard) {}
    public void addCard(Card toAdd) {}
}