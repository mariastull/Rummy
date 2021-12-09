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

    // TODO: update in UML that this has a param
    public final boolean takeTurn(boolean textBased) {
        boolean cardChoice = askCardChoice();
        if(textBased){
            if(cardChoice) System.out.println("Taking from the discard pile");
            else System.out.println("Taking from the top of the deck");
        }
        addCardToHand(cardChoice);
        int cardToDiscard = askCardDiscard();
        if(textBased){
            System.out.println("Chose to discard card: " + (cardToDiscard+1));
        }
        discardCard(cardToDiscard);
        boolean endGame = askEndGame();
        if(textBased){
            if(endGame) System.out.println("Chose to end the game");
            else System.out.println("Will keep playing");
        }
        return endGame;
    }

    // overwrite this
    protected abstract boolean askCardChoice();

    public final Card drawFromDeck(boolean isFromDiscard){
        addCardToHand(isFromDiscard);
        return hand.justDrawn;
    }

    private final void addCardToHand(boolean isFromDiscard){
        if(isFromDiscard){
            hand.justDrawn = discardPileRef.takeTop();
        } else {
            hand.justDrawn = drawPileRef.takeTop();
        }
    }
    
    // overwrite this
    protected abstract int askCardDiscard();

    public final void discardCard(int cardToDiscard){
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
    public boolean hasWinningHand(){
        return hand.checkForWin();
    }

    /**
     * Prints their current hand
     */
    public void printHand(){
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            System.out.println("- " + (i+1) + ": " + hand.cards[i].getFormattedFullName(true));
        }
        if(hand.justDrawn != null){
            System.out.println("- " + (Hand.HAND_SIZE+1) + ": " + hand.justDrawn.getFormattedFullName(true));
        }
    }
}