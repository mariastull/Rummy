package Players;

import CardsAndPiles.CardPile;
import CardsAndPiles.Hand;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public abstract class Player {
    public Hand hand;
    private CardPile discardPileRef;

    public Player(){
        hand = new Hand();
    }

    public boolean takeTurn() {
        boolean cardChoice = askCardChoice();
        addCardToHand(cardChoice);
        int cardToDiscard = askCardDiscard();
        discardCard(cardToDiscard);
        boolean endGame = askEndGame();
        return endGame;
    }

    // overwrite this
    protected abstract boolean askCardChoice();

    private void addCardToHand(boolean isFromDiscard){
        if(isFromDiscard){
            hand.cardJustDrawn = discardPileRef
        }
        
    }
    
    // overwrite this
    protected abstract int askCardDiscard();

    private void discardCard(int cardToDiscard){

    }

    // overwrite this
    protected abstract boolean askEndGame();
}