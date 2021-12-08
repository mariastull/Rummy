package Simulation;

import java.util.ArrayList;

import CardsAndPiles.Card;
import CardsAndPiles.Hand;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
// import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

 
public class GUI extends Application {

    ArrayList<Button> robotCardButtons;
    Button discardPile;
    Button drawPile;
    Button cardDrawn;
    ArrayList<Button> userCardButtons;
    Text tipBox;
    Button endGameButton;
    
    GameSimulator sim;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        sim = new GameSimulator();
        sim.setupNewGame();
        
        // setting the window's title
        primaryStage.setTitle("OOAD Rummy");
        
        // creating a grid to place our elements on
        GridPane grid = createBaseGrid();


        // Set up each row of our display

        // the computer's blank cards
        // need the # of cards in a hand
        robotCardButtons = new ArrayList<Button>();
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            Button blankComputerCard = buildCardButton(null);
            blankComputerCard.setDisable(true);
            robotCardButtons.add(blankComputerCard);
        }
        // put them into the hbox then in the grid
        HBox computerCardsBox = new HBox(10);
        computerCardsBox.getChildren().addAll(robotCardButtons);
        grid.addRow(1, computerCardsBox);
        
        // The game title in the middle
        Text sceneTitleText = new Text("Rummy");
        sceneTitleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));

        HBox sceneTitle = new HBox(sceneTitleText);
        sceneTitle.setAlignment(Pos.CENTER);
        grid.addRow(2, sceneTitle);
        

        // The draw and discard piles
        discardPile = buildCardButton(sim.board.discardPile.peekTopCard());
        discardPile.setOnAction(event -> { performDraw(true); });
        
        drawPile = buildCardButton(null);
        drawPile.setText("Draw\nPile");
        drawPile.setOnAction(event -> { performDraw(false); });

        HBox discardPileRow = new HBox(10, drawPile, discardPile);
        discardPileRow.setAlignment(Pos.CENTER);
        grid.addRow(3, discardPileRow);


        // the card we just drew
        cardDrawn = buildCardButton(null);
        cardDrawn.setVisible(false);
        cardDrawn.setDisable(true);
        cardDrawn.setOnAction(event -> { performDiscard(Hand.HAND_SIZE); });

        HBox newCardRow = new HBox(cardDrawn);
        newCardRow.setAlignment(Pos.CENTER);
        grid.addRow(4, newCardRow);


        // the user's hand
        userCardButtons = new ArrayList<Button>();
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            // generate a bunch of empty cards
            Button userCard = buildCardButton(null);
            // link them all up to the discard functionality
            int local_i = i;
            userCard.setOnAction(event -> { performDiscard(local_i); });
            // and keep a list of them all
            userCardButtons.add(userCard);   
        }

        HBox cardBox = new HBox(10);
        cardBox.getChildren().addAll(userCardButtons);
        grid.addRow(5, cardBox);


        // a button to end the game
        endGameButton = new Button("End Game");
        endGameButton.setOnAction(event -> { endGame(); });

        HBox endGameBox = new HBox(endGameButton);
        endGameBox.setAlignment(Pos.CENTER_RIGHT);
        grid.addRow(6, endGameBox);


        // various tips for the user
        tipBox = new Text("test");
        tipBox.setStyle("-fx-font-size: 10pt;");
        HBox tipBoxBox = new HBox(tipBox);
        tipBoxBox.setAlignment(Pos.CENTER);
        grid.addRow(7, tipBoxBox);



        // start up a new game
        startNewGame();

        // Finish and display our scene
        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public GridPane createBaseGrid(){
        // creating a grid to place our elements on
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        
        // only working with rows, so only need veritcal gap
        grid.setVgap(20);

        // background fill color
        BackgroundFill background_fill = new BackgroundFill(
            Color.GRAY,
            CornerRadii.EMPTY,
            Insets.EMPTY
        );
        Background background = new Background(background_fill);
        grid.setBackground(background);

        return grid;
    }


    public Button buildCardButton(Card card){
        Button cardButton;
        if(card != null){
            cardButton = new Button(card.getFormattedFullName());
        } else {
            cardButton = new Button();
        }
        cardButton.setStyle(
            "-fx-border-radius: 10; "
            + "-fx-min-height: 90; "
            + "-fx-min-width: 70; "
            + "-fx-text-alignment: center; "
            + "-fx-background-color: white; "
        );
        return cardButton;
    }


    public void disableDiscard(boolean disable){
        for(Button cardButton : userCardButtons){
            cardButton.setDisable(disable);
        }
    }


    public void disableDraw(boolean disable){
        drawPile.setDisable(disable);
        discardPile.setDisable(disable);
    }


    public void updatePlayerHand(){
        Card[] hand = sim.human.getHand().cards;
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            userCardButtons.get(i).setText(hand[i].getFormattedFullName());
        }
    }


    public void performDraw(boolean fromDiscard){
        String newCard = sim.drawCard(fromDiscard);
        cardDrawn.setText(newCard);
        cardDrawn.setVisible(true);
        cardDrawn.setDisable(false);
        disableDiscard(false);
        disableDraw(true);
        tipBox.setText("Which card would you like to discard?");
    }


    public void performDiscard(int which){
        sim.discardCard(which);
        disableDiscard(true);

        updatePlayerHand();
        cardDrawn.setVisible(false);
        cardDrawn.setDisable(true);
        
        sim.robotPlay();
        discardPile.setText(sim.getDiscardTop());

        disableDraw(false);
        tipBox.setText("It is your turn, pick a card from either the draw pile or discard pile");
    }


    public void startNewGame(){
        endGameButton.setText("End Game");
        endGameButton.setOnAction(event -> { endGame(); });

        sim.setupNewGame();

        updatePlayerHand();

        tipBox.setText("Tips show up here! Start by choosing from either the draw pile or discard pile");

        discardPile.setText(sim.board.discardPile.peekTopCard().getFormattedFullName());

        disableDraw(false);
        disableDiscard(true);
    }


    public void endGame(){
        disableDiscard(true);
        disableDraw(true);
        tipBox.setText("Game is over! " + sim.GUIVerifyWin());
        Card[] hand = sim.getComputerHand();
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            Button robotCard = robotCardButtons.get(i);
            robotCard.setText(hand[i].getFormattedFullName());
        }
        // set the button up now to restart the game
        endGameButton.setText("Restart Game");
        endGameButton.setOnAction(event -> { startNewGame(); });
    }
}