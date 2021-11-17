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
    protected Hand hand;
    protected CardPile discardPileRef;
    protected CardPile drawPileRef;

    public Player(){
        hand = new Hand();
        discardPileRef = null;
        drawPileRef = null;
    }

    public void setupHand(Card[] cards){
        hand.cards = cards;
        hand.justDrawn = null;
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

    /**
     * Allows us to access the hand's checkForWin function
     * without making the hand itself public
     * @return True if their hand is winning, false if not
     */
    // TODO: add to UML
    public boolean hasWinningHand(){
        return hand.checkForWin();
    }
}