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
                // do something
                break;
            case ShowIsPlayerTurn:
                // do something
                break;
            case AskForGrabChoice:
                // do something
                break;
            case AskForDiscardChoice:
                // do something
                break;
            case ShowWin:
                // do something
                break;
            case ShowLoss:
                // do something
                break;
            case ShowTie:
                // do something
                break;
        }
    }

    public void interpretClick(){
        // TODO: implement
    }

    public void drawBoard(){
        // TODO: implement
    }
}
