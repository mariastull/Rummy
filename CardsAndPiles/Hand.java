package CardsAndPiles;
/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class Hand {
    public Card[] cards;
    public Card justDrawn;

    public Hand() {
        cards = new Card[5];
        for(int i = 0; i < 5; i++){
            cards[i] = null;
        }
    }

    public boolean checkForWin() {}

    public Card discard(int which){
        Card choice = cards[which];
        cards[which] = justDrawn;
        justDrawn = null;
        return choice;
    }
}