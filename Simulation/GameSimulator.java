package Simulation;

import CardsAndPiles.Card;
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
    public GameBoard board;
    public HumanPlayer human;
    public RobotPlayer robot;
    
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
    }

    String drawCard(boolean isFromDiscard){
        Card c = human.drawFromDeck(isFromDiscard);
        return c.getFormattedFullName();
    }

    void discardCard(int cardToDiscard){
        human.publicDiscard(cardToDiscard);
    }

    void robotPlay(){
        robot.takeTurn();
    }

    String getDiscardTop(){
        return board.returnDiscardTop();
    }

    Card[] getComputerHand(){
        return robot.seeCards();
    }

    public String GUIVerifyWin(){
        boolean humanWon = human.hasWinningHand();
        boolean robotWon = robot.hasWinningHand();
        if(humanWon){
            if(robotWon){
                return "It's a tie!";
            }
            return "You win!!";
        }
        return "You lose :(";
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

        GUI.launch(args);

        // sim.setupNewGame();
        
        // sim.startGame();
        
        // sim.verifyWin();
    }
}