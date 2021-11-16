package Display;

import java.util.ArrayList;

import Observers.Subscriber;
import Observers.Update;
import Players.HumanPlayer;
import Buttons.Button;


public class BoardDisplay extends Subscriber{
    GameBoard boardRef;
    HumanPlayer userRef;
    ArrayList<Button> buttons;

    public BoardDisplay(){

    }

    public void giveUpdate(Update update){

    }

    public void interpretClick(){

    }

    public void drawBoard(){

    }
}
