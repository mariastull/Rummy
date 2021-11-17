package CardsAndPiles;

import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class Hand {
    public Card[] cards;
    public Card justDrawn;

    public final static int HAND_SIZE = 7;

    public Hand() {
        cards = new Card[HAND_SIZE];
        for(int i = 0; i < HAND_SIZE; i++){
            cards[i] = null;
        }
    }

    private void addUnorderedSubsets(ArrayList<Integer> set, ArrayList<HashSet> collective){
        HashSet<Integer> meld = new HashSet<Integer>();
        for (int i = 0; i < set.size(); i++){
            meld.add(set.get(i));
        }
        collective.add(meld);

        if(set.size()==3){
            return;
        }

        // otherwise, we have a set of size 4, and need to add all size 3 subsets
        for (int i =0; i < set.size(); i++){ // element to leave out
            meld = new HashSet<Integer>();
            for (int j=0; j< set.size(); j++){
                if (j==i){
                    continue;
                }
                meld.add(set.get(j));
            }
            collective.add(meld);
        }        
    }

    private void addOrderedSubsets(ArrayList<Integer> set, ArrayList<HashSet> collective){
        int n = set.size();
        for (int size = 3; size <= n; size++){
            for (int start = 0; start <= n - size; start++ ){
                HashSet<Integer> meld = new HashSet<Integer>();
                for (int index = start; index < start+size; index ++){
                    meld.add(set.get(index));
                }
                collective.add(meld);
            }
        }
    }

    public boolean checkForWin() {
        // for now, just always assume nobody has won
        
        
        // add cards into a 4 x 14 array (skip 0)--will be position in hand for cards in hand, -1 otherwise
        int[][] deckArr = new int[4][14];
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                deckArr[i][j] = -1;
            }
        }
        for (int i=0; i< HAND_SIZE; i++){
            Card card = cards[i];
            deckArr[card.getSuit().ordinal()][card.value] = i;
            // NOTE: using ordinal for enums is supposed to be bad practice, but we are not going to
            // change the number of suits in a 52-card deck so this shouldn't break anything
        }

        // create ArrayList of sets for possible melds 
        // HashSet<int>: which cards in the hand make up the meld)
        ArrayList<HashSet> possible_melds = new ArrayList<HashSet>();

        // check for 3 or 4 of a kind
        // add all possible sets to possible_melds
        // (1 possible for 3 of a kind, 5 possible for 4 of a kind)
        for(int rank = 1; rank <=13; rank++){
            ArrayList<Integer> currMeld = new ArrayList<Integer>();
            int cardsInMeld = 0;
            for(int suit = 0; suit< 4; suit++){             
                if(deckArr[suit][rank] != -1){ //card in hand
                    cardsInMeld++;
                    currMeld.add(deckArr[suit][rank]);
                }
            }
            if (cardsInMeld >= 3){
                addUnorderedSubsets(currMeld, possible_melds);
                currMeld.clear();
            }
        }

        // check for runs (straight flush)
        // add all possible sets to possible_melds
        for (int suit = 0; suit < 4; suit++){
            int start = -1;
            int curr = -1;
            ArrayList<Integer> currMeld = new ArrayList<Integer>();
            for (int rank = 1; rank<=13; rank++){
                if (deckArr[suit][rank]!= -1){
                    if (start == -1){ //starting a new possible run
                        start = rank;
                        curr = rank;
                        currMeld.add(deckArr[suit][rank]);
                    }
                    else{ //we are already in a possible run
                        curr++;
                        currMeld.add(deckArr[suit][rank]);
                    }
                } else{ //card not in hand
                    if (start != -1){
                        if(curr - start >= 2){ //we have at least 3 cards in the run
                            addOrderedSubsets(currMeld, possible_melds);
                        }
                            // reset
                        start = -1;
                        curr = -1;
                        currMeld.clear();
                    }
                }
            }
            if(start != -1 && curr - start >= 2){ //we have at least 3 cards in the run at the end of the loop
                addOrderedSubsets(currMeld, possible_melds);
            }

        }

        // for each pair of runs:
        // if size + size 2 == 7 AND size of intersection between the sets = 0
        // return true
        // also for each individual set, if size==7 return true

        for (int i=0; i< possible_melds.size(); i++){
            HashSet<Integer> meld1 = possible_melds.get(i);
            if (meld1.size()==HAND_SIZE){
                return true;
            }
            for (int j=i+1; j< possible_melds.size(); j++){
                HashSet<Integer> meld2 = possible_melds.get(j);
                HashSet<Integer> intersection = new HashSet<Integer>(meld1);
                intersection.retainAll(meld2);
                if(meld1.size() + meld2.size() ==  HAND_SIZE && intersection.size()==0){
                    return true;
                }
            }
        }
        
        return false;
    }

    // Helper method for testing purposes
    public void addCard(Card c, int position){
        if (position >= 0 && position < HAND_SIZE){
            cards[position] = c;
        }

    }

    public Card discard(int which){
        Card choice;
        if(which == HAND_SIZE){
            choice = justDrawn;
        } else {
            choice = cards[which];
            cards[which] = justDrawn;
        }
        justDrawn = null;
        return choice;
    }
}