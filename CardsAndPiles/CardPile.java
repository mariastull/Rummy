package CardsAndPiles;
import java.util.ArrayList;
import java.util.Collections;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class CardPile {
    private ArrayList<Card> cards;

    public CardPile() {
        cards = new ArrayList<Card>();
    }

    public CardPile(ArrayList<Card> cards){
        this.cards = cards;
    }

    public int getSize(){
        return cards.size();
    }

    public void shufflePile() {
        Collections.shuffle(cards);
    }

    public Card peekTopCard() {
        return cards.get(0);
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