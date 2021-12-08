package Display;

import java.util.ArrayList;

import Observers.ISubscriber;
import Players.HumanPlayer;
import Buttons.Button;
import Simulation.GUI;
import Simulation.GameBoard;

public class BoardDisplay implements ISubscriber<DisplayUpdate> {

    public GUI gui;
    public GameBoard boardRef;
    public HumanPlayer userRef;
    public ArrayList<Button> cardButtons;

    public BoardDisplay(){
        boardRef = null;
        userRef = null;
        cardButtons = null;
    }

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
                // ArrayList<Button> cards = new ArrayList<Card>();
                // for(Card card : sim.)
                break;
            case DiscardUpdated:
                // do something
                break;
            case ShowIsComputerTurn:
                drawBoard(false);
                break;
            case ShowIsPlayerTurn:
                drawBoard(true);
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

    // TODO: add the extra param to the UML
    public void drawBoard(boolean isPlayerTurn){
        // TODO: implement
    }
}
