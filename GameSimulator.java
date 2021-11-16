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

    bool isPlayersTurn;

    public GameSimulator(){
        board = new GameBoard();
        human = new HumanPlayer();
        robot = new RobotPlayer();
    }

    void setupNewGame() {
        board.shuffle();
        human.hand = board.getStartingHand();
        robot.hand = board.getStartingHand();

        isPlayersTurn = random.nextBool();
    }

    void startGame() {
        // TODO
    }
    
    void verifyWin() {
        // TODO
    }

    public static void main(String[] args){
        GameSimulator sim = new GameSimulator();
        sim.setupNewGame();
        sim.startGame();
        sim.verifyWin();
    }
}