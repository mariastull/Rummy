package Display;

import java.util.ArrayList;

import Observers.ISubscriber;
import Players.HumanPlayer;
import Buttons.Button;
import Simulation.GameBoard;

public class BoardDisplay implements ISubscriber<DisplayUpdate> {
    GameBoard boardRef;
    HumanPlayer userRef;
    ArrayList<Button> buttons;

    public BoardDisplay(){
        boardRef = null;
        userRef = null;
        buttons = new ArrayList<Button>();
    }

    // TODO: add in UML
    public void updateRefs(GameBoard boardRef, HumanPlayer userRef){
        this.boardRef = boardRef;
        this.userRef = userRef;
    }

    public void giveUpdate(DisplayUpdate update){
        // TODO: implement
        switch(update){ 
            case ShowStartButton:
                // do something
                break;
            case HandUpdated:
                // do something
                break;
            case DiscardUpdated:
                // do something
                break;
            case ShowIsComputerTurn:
            System.out.println("It's the robot's turn");
                break;
            case ShowIsPlayerTurn:
                System.out.println("It's the player's turn");
                drawBoard();
                break;
            case AskForGrabChoice:
                // do something
                break;
            case AskForDiscardChoice:
                // do something
                break;
            case ShowWin:
                System.out.println("The player won!!");
                break;
            case ShowLoss:
                System.out.println("The player lost :(");
                break;
            case ShowTie:
                System.out.println("Game ends in a tie");
                break;
        }
    }

    public void interpretClick(){
        // TODO: implement
    }

    public void drawBoard(){
        userRef.printHand();
        boardRef.printDiscardTop();
    }
}
