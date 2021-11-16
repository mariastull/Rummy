package CardsAndPiles;
/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class Hand {
    Card[] cards;
    Card justDrawn;

    public Hand() {
        cards = new Card[5];
        for(int i = 0; i < 5; i++){
            cards[i] = null;
        }
    }

    boolean checkForWin() {}
}