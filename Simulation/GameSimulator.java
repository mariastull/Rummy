package Simulation;

import java.util.Random;

import CardsAndPiles.Card;
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

    public GameSimulator(){
        board = new GameBoard();
        human = new HumanPlayer();
        robot = new RobotPlayer();
    }

    void setupNewGame() {
        board = new GameBoard();
        
        human.setupHand(board.getStartingHand());
        human.setPileRefs(board.discardPile, board.drawPile);

        robot.setupHand(board.getStartingHand());
        robot.setPileRefs(board.discardPile, board.drawPile);
    }

    String asyncDrawCard(boolean isFromDiscard){
        Card c = human.drawFromDeck(isFromDiscard);
        return c.getFormattedFullName(false);
    }

    void asyncDiscardCard(int cardToDiscard){
        human.discardCard(cardToDiscard);
    }

    void asyncRobotPlay(){
        robot.takeTurn(false);
    }

    String getDiscardTop(){
        return board.returnDiscardTop();
    }

    Card[] getComputerHand(){
        return robot.seeCards();
    }

    public String getGameResults(){
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

    
    // public String getGameResults2() {
    //     boolean humanWon = human.hasWinningHand();
    //     boolean robotWon = robot.hasWinningHand();

    //     String results;

    //     if(humanWon){
    //         if(robotWon){
    //             // both won, it's a tie
    //             results = "It's a tie! You both won";
    //         } else {
    //             // player won!
    //             results = "You win!!";
    //         }
    //     } else {
    //         if(robotWon){
    //             // robot won!
    //             results = "You lose, the robot won :(";
    //         } else {
    //             // both lost, it's a tie
    //             results = "It's a tie! You both lost";
    //         }
    //     }

    //     return results;
    // }

    public static void main(String[] args){
        GameSimulator sim = new GameSimulator();
        sim.setupNewGame();
        System.out.println("Welcome to Rummy!");

        boolean shouldEnd = false;
        boolean playersTurn = new Random().nextBoolean();
        
        while(!shouldEnd){
            System.out.println("----------------------");
            
            if(playersTurn){
                System.out.println("Player's turn:");
                sim.human.printHand();
                sim.board.printDiscardTop();
                shouldEnd = sim.human.takeTurn(true);
            
            } else {
                System.out.println("Robot's turn:");
                shouldEnd = sim.robot.takeTurn(true);
            }
        }

        System.out.println(sim.getGameResults());
    }
}