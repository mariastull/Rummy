package Players;

import CardsAndPiles.Card;
import CardsAndPiles.CardPile;
import CardsAndPiles.Hand;
import Observers.IPublisher;
import Observers.ISubscriber;
import Display.DisplayUpdate;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public abstract class Player implements IPublisher<DisplayUpdate>{
    protected Hand hand;
    protected CardPile discardPileRef;
    protected CardPile drawPileRef;

    private ISubscriber<DisplayUpdate> subscriber;

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

    public final boolean takeTurn() {
        boolean cardChoice = askCardChoice();
        if(cardChoice) System.out.println("Taking from the discard pile");
        else System.out.println("Taking from the top of the deck");
        addCardToHand(cardChoice);
        int cardToDiscard = askCardDiscard();
        System.out.println("Chose to discard card: " + (cardToDiscard+1));
        discardCard(cardToDiscard);
        boolean endGame = askEndGame();
        if(endGame) System.out.println("Chose to end the game");
        else System.out.println("Will keep playing");
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
            checkReshuffleDeck();
        }
    }
    
    // overwrite this
    protected abstract int askCardDiscard();

    public final void publicDiscard(int cardToDiscard){
        if (cardToDiscard<7 && cardToDiscard>=0){
            discardCard(cardToDiscard);
        }
    }

    private final void discardCard(int cardToDiscard){
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
     * Records the given subscriber so we know who to publish to
     * @param subscriber the new subscriber to add
     */
    public void setSubscriber(ISubscriber<DisplayUpdate> subscriber){
        this.subscriber = subscriber;
    }

    /**
     * pass along the given update to our subscriber
     */
    public void notifySubscriber(DisplayUpdate update){
        subscriber.giveUpdate(update);
    }

    /**
     * Prints their current hand
     */
    public void printHand(){
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            System.out.println("- " + (i+1) + ": " + hand.cards[i].getFormattedFullName());
        }
        if(hand.justDrawn != null){
            System.out.println("- " + (Hand.HAND_SIZE+1) + ": " + hand.justDrawn.getFormattedFullName());
        }
    }

    /**
     * Checks if we ran out of cards in the draw pile and need to reshuffle the discard into the draw
     */
    public void checkReshuffleDeck(){
        if(drawPileRef.getSize() == 0){
            System.out.println("Ran out of cards in the draw deck!");
            // TODO: actually do something
        }
    }
}