package CardsAndPiles;
/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class Card{
    CardSuit suit;
    int value;
    String getFormattedValue() {}
    String getFormattedFullName() {}
}

public enum CardSuit {
    Hearts, Diamonds, Clubs, Spades
}