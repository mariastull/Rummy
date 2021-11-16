package Players;

import CardsAndPiles.Card;
import CardsAndPiles.CardPile;
import CardsAndPiles.Hand;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public abstract class Player {
    private Hand hand;
    private CardPile discardPileRef;
    private CardPile drawPileRef;

    public Player(){
        hand = new Hand();
        discardPileRef = null;
        drawPileRef = null;
    }

    public void setPileRefs(CardPile discardRef, CardPile drawRef){
        discardPileRef = discardRef;
        drawPileRef = drawRef;
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
            hand.justDrawn = discardPileRef.takeTop();
        } else {
            hand.justDrawn = drawPileRef.takeTop();
        }
    }
    
    // overwrite this
    protected abstract int askCardDiscard();

    private void discardCard(int cardToDiscard){
        Card discarded = hand.discard(cardToDiscard);
        discardPileRef.discardCard(discarded);
    }

    // overwrite this
    protected abstract boolean askEndGame();
}