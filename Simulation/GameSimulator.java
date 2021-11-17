package Simulation;
import java.util.Random;

import Display.BoardDisplay;
import Display.DisplayUpdate;
import Players.HumanPlayer;
import Players.RobotPlayer;

/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

public class GameSimulator {
    private GameBoard board;
    private BoardDisplay display;
    private HumanPlayer human;
    private RobotPlayer robot;

    private boolean isPlayersTurn;

    public GameSimulator(){
        board = new GameBoard();
        human = new HumanPlayer();
        robot = new RobotPlayer();
    }

    void linkToDisplay(BoardDisplay display) {
        this.display = display;
    }

    void setupNewGame() {
        board.drawPile.shufflePile();
        human.setupHand(board.getStartingHand());
        robot.setupHand(board.getStartingHand());

        Random rng = new Random();
        isPlayersTurn = rng.nextBoolean();
    }

    void startGame() {
        // TODO: implement
    }
    
    void verifyWin() {
        DisplayUpdate update;

        boolean humanWon = human.hasWinningHand();
        boolean robotWon = robot.hasWinningHand();

        if(humanWon){
            if(robotWon){
                // both won, it's a tie
                update = DisplayUpdate.ShowTie;
            } else {
                // player won!
                update = DisplayUpdate.ShowWin;
            }
        } else {
            if(robotWon){
                // robot won!
                update = DisplayUpdate.ShowLoss;
            } else {
                // both lost, it's a tie
                update = DisplayUpdate.ShowTie;
            }
        }

        display.giveUpdate(update);
    }

    public static void main(String[] args){
        GameSimulator sim = new GameSimulator();
        BoardDisplay display = new BoardDisplay();
        sim.linkToDisplay(display);
        sim.setupNewGame();
        sim.startGame();
        sim.verifyWin();
    }
}