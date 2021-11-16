import CardsAndPiles.Hand;

package Players;
/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class Player {
    Hand hand;
    Bool takeTurn() {}
    Bool askCardChoice() {}
    void addCardToHand(bool isFromDiscard) {}
    int askCardDiscard(int cardToDiscard) {}
    Bool askEndGame() {}
}