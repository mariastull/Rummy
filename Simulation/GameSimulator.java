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
    private HumanPlayer human;
    private RobotPlayer robot;

    private boolean isPlayersTurn;
    
    private BoardDisplay display;

    public GameSimulator(){
        board = new GameBoard();
        human = new HumanPlayer();
        robot = new RobotPlayer();
    }

    void linkToDisplay(BoardDisplay display) {
        this.display = display;
        this.display.updateRefs(board, human);
    }

    void setupNewGame() {
        board.drawPile.shufflePile();
        
        human.setupHand(board.getStartingHand());
        human.setPileRefs(board.discardPile, board.drawPile);
        human.setSubscriber(display);

        robot.setupHand(board.getStartingHand());
        robot.setPileRefs(board.discardPile, board.drawPile);
        robot.setSubscriber(display);

        Random rng = new Random();
        isPlayersTurn = rng.nextBoolean();
        if(isPlayersTurn) System.out.println("Player goes first");
        else System.out.println("Robot goes first");
    }

    void startGame() {
        display.drawBoard();
        boolean endCalled = false;
        while(!endCalled) {
            System.out.println("----------------------");
            // let them take their turn
            if(isPlayersTurn){
                display.giveUpdate(DisplayUpdate.ShowIsPlayerTurn);
                endCalled = human.takeTurn();
            } else {
                display.giveUpdate(DisplayUpdate.ShowIsComputerTurn);
                endCalled = robot.takeTurn();
            }
            // switch turns
            isPlayersTurn = !isPlayersTurn;
        }
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

        human.printHand();
        robot.printHand();
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