package Players;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class HumanPlayer extends Player {
    @Override
    protected boolean askCardChoice() {
        // TODO: implement
        return false;
    }

    @Override
    protected int askCardDiscard() {
        // TODO: implement
        return 5;
    }

    @Override
    protected boolean askEndGame() {
        // TODO: implement
        return true;
    }
}