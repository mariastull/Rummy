package CardsAndPiles;
/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class Card{
    CardSuit suit;
    int value;

    void Card(CardSuit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    String getFormattedValue() {
        String valueName = value;
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
        String valueName = value;
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
        return valueName + " of " + suit;
    }
}

public enum CardSuit {
    Hearts, Diamonds, Clubs, Spades
}