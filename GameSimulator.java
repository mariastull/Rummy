import java.util.Random;

import CardsAndPiles.GameBoard;
import Players.HumanPlayer;
import Players.RobotPlayer;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class GameSimulator {
    GameBoard board;
    HumanPlayer human;
    RobotPlayer robot;

    boolean isPlayersTurn;

    public GameSimulator(){
        board = new GameBoard();
        human = new HumanPlayer();
        robot = new RobotPlayer();
    }

    void setupNewGame() {
        board.drawPile.shufflePile();
        human.setupHand(board.getStartingHand());
        robot.setupHand(board.getStartingHand());

        Random rng = new Random();
        isPlayersTurn = rng.nextBoolean();
    }

    void startGame() {
        // TODO
    }
    
    void verifyWin() {
        // TODO 
        // (call CheckWin on Hand)
    }

    public static void main(String[] args){
        GameSimulator sim = new GameSimulator();
        sim.setupNewGame();
        sim.startGame();
        sim.verifyWin();
    }
}