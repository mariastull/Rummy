package CardsAndPiles;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class Card{
    CardSuit suit;
    int value;

    public Card(CardSuit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public CardSuit getSuit(){ return suit; }
    public int getValue(){ return value; }

    String getFormattedValue() {
        String valueName = ""+ value; // some junk to do string conversion
        switch (value) {
            case 11: 
                valueName = "Jack";
                break;
            case 12:
                valueName = "Queen";
                break;
            case 13:
                valueName = "King";
                break;
            default:
                break;
        }
        return valueName;
    }

    String getFormattedFullName() {
        return getFormattedValue() + " of " + suit;
    }
}