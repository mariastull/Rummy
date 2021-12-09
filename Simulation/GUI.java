package Simulation;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import CardsAndPiles.Card;
import CardsAndPiles.Hand;


// TODO: add this entire file to the UML, the class and all of its functions are new
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
        // make sure the simulator itself is set up
        sim = new GameSimulator();
        
        // setting the window's title
        primaryStage.setTitle("OOAD Rummy");
        
        // creating a grid to place our elements on
        GridPane grid = createBaseGrid();



        // Set up each row of our display

        // Row 1: the computer's blank cards
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
        

        // Row 2: The game title in the middle
        Text sceneTitleText = new Text("Rummy");
        sceneTitleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));

        HBox sceneTitle = new HBox(sceneTitleText);
        sceneTitle.setAlignment(Pos.CENTER);
        grid.addRow(2, sceneTitle);
        

        // Row 3: The draw and discard piles
        discardPile = buildCardButton(sim.board.discardPile.peekTopCard());
        discardPile.setOnAction(event -> { performDraw(true); });
        
        drawPile = buildCardButton(null);
        drawPile.setText("Draw\nPile");
        drawPile.setOnAction(event -> { performDraw(false); });

        HBox discardPileRow = new HBox(10, drawPile, discardPile);
        discardPileRow.setAlignment(Pos.CENTER);
        grid.addRow(3, discardPileRow);


        // Row 4: the card we just drew
        cardDrawn = buildCardButton(null);
        cardDrawn.setVisible(false);
        cardDrawn.setDisable(true);
        cardDrawn.setOnAction(event -> { performDiscard(Hand.HAND_SIZE); });

        HBox newCardRow = new HBox(cardDrawn);
        newCardRow.setAlignment(Pos.CENTER);
        grid.addRow(4, newCardRow);


        // Row 5: the user's hand
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


        // Row 6: a button to end the game
        endGameButton = new Button("End Game");
        endGameButton.setOnAction(event -> { endGame(); });

        HBox endGameBox = new HBox(endGameButton);
        endGameBox.setAlignment(Pos.CENTER_RIGHT);
        grid.addRow(6, endGameBox);


        // Row 7: various tips for the user
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



    /**
     * Builds a base grid pane for us to work with and add rows to
     * @return a GridPane object for to add Nodes to
     */
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


    /**
     * given a card (or null), build out a button that looks like a card
     * @param card the input card to base the button on, or null for an empty card
     * @return a Button object representing our card
     */
    public Button buildCardButton(Card card){
        Button cardButton;
        if(card != null){
            cardButton = new Button(card.getFormattedFullName(false));
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


    /**
     * disable/enable clicking interactions on all of the cards in our hand
     * @param disable whether we should disable them, or not (meaning enable them)
     */
    public void disableDiscard(boolean disable){
        for(Button cardButton : userCardButtons){
            cardButton.setDisable(disable);
        }
    }


    /**
     * disable/enable clicking interactions on the two draw piles (draw and discard piles)
     * @param disable whether we should disable them, or not (meaning enable them)
     */
    public void disableDraw(boolean disable){
        drawPile.setDisable(disable);
        discardPile.setDisable(disable);
    }


    /**
     * Update the visual display of the player's hand to match what they actually have
     */
    public void updatePlayerHand(){
        Card[] hand = sim.human.getHand().cards;
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            userCardButtons.get(i).setText(hand[i].getFormattedFullName(false));
        }
    }


    /**
     * given which card the user wants, do all of the necessary actions to interpret their click
     * and route the necessary data. Includes switching what they can click on, changing
     * the tip box, and actually telling the sim what card we want to draw.
     * @param fromDiscard true to take from the discard pile, false to take from the draw pile
     */
    public void performDraw(boolean fromDiscard){
        String newCard = sim.asyncDrawCard(fromDiscard);
        cardDrawn.setText(newCard);
        cardDrawn.setVisible(true);
        cardDrawn.setDisable(false);
        disableDiscard(false);
        disableDraw(true);
        tipBox.setText("Which card would you like to discard?");
    }


    /**
     * given which card the user wants, perform all the necessary actions to discard that one.
     * Includes telling the simulation which one, updating their hand with the new info, disabling
     * the discard clicking, and letting the robot take their turn.
     * @param which which card the user wants to discard, 0-6 for one in their hand, 7 for the justDrawn
     */
    public void performDiscard(int which){
        sim.asyncDiscardCard(which);
        disableDiscard(true);

        updatePlayerHand();
        cardDrawn.setVisible(false);
        cardDrawn.setDisable(true);
        
        sim.asyncRobotPlay();
        discardPile.setText(sim.getDiscardTop());

        disableDraw(false);
        tipBox.setText("It is your turn, pick a card from either the draw pile or discard pile");
    }


    /**
     * completely wipe everything and set up a new game for the player
     */
    public void startNewGame(){
        endGameButton.setText("End Game");
        endGameButton.setOnAction(event -> { endGame(); });

        sim.setupNewGame();

        updatePlayerHand();

        for(Button robotCard : robotCardButtons){
            robotCard.setText("");
        }

        tipBox.setText("Tips show up here! Start by choosing from either the draw pile or discard pile");

        discardPile.setText(sim.getDiscardTop());

        disableDraw(false);
        disableDiscard(true);
    }


    /**
     * call for the end of the game, stopping all moves and checking scores
     */
    public void endGame(){
        disableDiscard(true);
        disableDraw(true);
        tipBox.setText("Game is over! " + sim.getGameResults());
        Card[] hand = sim.getComputerHand();
        for(int i = 0; i < Hand.HAND_SIZE; i++){
            Button robotCard = robotCardButtons.get(i);
            robotCard.setText(hand[i].getFormattedFullName(false));
        }
        // set the button up now to restart the game
        endGameButton.setText("Restart Game");
        endGameButton.setOnAction(event -> { startNewGame(); });
    }
}