package CardsAndPiles;
import java.util.Random;
import java.util.Collection;

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
        Collections.shuffle(cards);
    }

    Card peekTopCard() {
        return discardPile.get(0);
    }

    public Card takeTop() {
        Card topCard = cards.get(0);
        cards.remove(0);
        return topCard;
    }

    public void discardCard(Card toDiscard) {
        cards.add(0, toDiscard);
    }

}