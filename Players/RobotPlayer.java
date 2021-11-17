package Players;
/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

import CardsAndPiles.Card;

public class RobotPlayer extends Player {

    public RobotPlayer(){
        super();
    }

    /**
     * some simple logic to see if a card is something
     * they may want. It counts the number of ways its
     * similar to whats already in their hand to give
     * a score
     * @param card the card to possibly add
     * @param hand the current hand to compare against
     * @return the "improvement" score that having that card would add
     */
    // TODO: add to the UML
    private static int cardImprovementScore(Card newCard, Card[] oldHand) {
        int matches = 0;

        for(Card handCard : oldHand){
            // for a set, is of the same value as something we have
            if(newCard.getValue() == handCard.getValue()){
                matches += 3; // worth 3 so that less useful things can be worth less
            }
            // for a run, first make sure it's the right suit
            if(newCard.getSuit() == handCard.getSuit()){
                matches ++; // even matching suit is promising
                int valDifference = newCard.getValue() - handCard.getValue();
                // should be impossible to be a difference of 0
                if(valDifference == 1){ matches += 2; } // next is amazing
                if(valDifference == 2){ matches += 1; } // 2 away is still good
                // don't check for further away than 2
            }
        }

        return matches;
    }

    /**
     * for the robot, does some basic checks to see if the top
     * of the discard deck would be an improvement or not
     * @return a boolean, True to take from the discard pile,
     *      False to take from the top of the stack
     */
    @Override
    protected boolean askCardChoice() {
        // look at the card on top
        Card discardTop = discardPileRef.peekTopCard();
        
        // see how it would change our score
        int score = cardImprovementScore(discardTop, hand.cards);

        // if it looks good enough, then take it
        return (score >= 6);
        // 6 is totally arbitrary, I chose it since it's matching 2 things
    }
    
    /**
     * for the robot, finds the card in their hand which "adding"
     * it would give the lowest score, so therefore removing it
     * will give the least amount of loss
     * @return the index of the card to discard, 5 for the one we just picked up
     */
    @Override
    protected int askCardDiscard() {
        // setup work
        // build up a pretend hand
        Card[] pretendHand = new Card[5];
        // fill it with our real hand
        for(int i = 0; i < 5; i++){
            pretendHand[i] = hand.cards[i];
        }

        // check the score of the one we just picked up
        int worstScore = cardImprovementScore(hand.justDrawn, pretendHand);
        // index 5 is the current worst
        int worstIndex = 5;

        // check removing each card to find the worst
        for(int i = 0; i < 5; i++){
            // take out the card at that spot
            Card maybeBad = pretendHand[i];
            // substitute in the one we just drew
            pretendHand[i] = hand.justDrawn;
            // find the score
            int score = cardImprovementScore(maybeBad, pretendHand);
            // see if the score is the new worst
            if(score < worstScore){
                worstScore = score;
                worstIndex = i;
            }
            // make sure to put it back
            pretendHand[i] = maybeBad;
        }

        // should now know where the worst scoring card is
        return worstIndex;
    }
    
    /**
     * For the robot, if their hand is a winning hand then
     * yes they will want to end the game
     * @return True to end the game, false to keep playing
     */
    @Override
    protected boolean askEndGame() {
        // just call the superclass's function
        return hasWinningHand();
    }
}