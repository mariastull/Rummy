package Display;

import java.util.ArrayList;

import CardsAndPiles.Card;
import CardsAndPiles.Hand;
import Simulation.GameSimulator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
// import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
// import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

 
public class GUI extends Application {

    // public static void main(String[] args) {
    //     launch(args);
    // }
    
    @Override
    public void start(Stage primaryStage) {
        
        // setting the window's title
        primaryStage.setTitle("OOAD Rummy");
        
        // creating a grid to place our elements on
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        // grid.setHgap(10);
        grid.setVgap(10);
        // grid.setPadding(new Insets(25, 25, 25, 25));
        
        // Placing a title on the screen
        Text sceneTitleText = new Text("Rummy");
        sceneTitleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        HBox sceneTitle = new HBox(sceneTitleText);
        sceneTitle.setAlignment(Pos.CENTER);
        grid.addRow(2, sceneTitle);
        
        // build out the draw and discard piles
        Button discardPile = buildCardButton(display.boardRef.discardPile.peekTopCard());
        Button drawPile = buildCardButton(null);
        drawPile.setText("Draw\nPile");
        HBox discardPileRow = new HBox(10, drawPile, discardPile);
        discardPileRow.setAlignment(Pos.CENTER);
        grid.addRow(3, discardPileRow);

        // final Text actiontarget = new Text();
        // grid.add(actiontarget, 1, 6);

        // set up the user's hand
        Hand handRef = display.userRef.getHand();
        
        ArrayList<Button> cardButtons = new ArrayList<Button>();
        for(Card card : handRef.cards){
            cardButtons.add(buildCardButton(card));
        }

        // link up all of the cards in our hand to the discard pile
        for(int i = 0; i < cardButtons.size(); i++){
            cardButtons.get(i).setOnAction(event -> { sim.discardCard(i); });
        }

        HBox cardBox = new HBox(10);
        cardBox.getChildren().addAll(cardButtons);
        grid.addRow(5, cardBox);

        // the discard pile
        // HBox discardsBox = new HBox(10);
        // Button discardSelectedCardButton = new Button("Discard Selected Card");
        // Text discardTextHelper1 = new Text("Card");
        // Text cardToDiscard = new Text("<NONE>");
        // Text discardTextHelper2 = new Text(" selected to Discard");
        // discardsBox.getChildren().add(discardSelectedCardButton);
        // discardsBox.getChildren().add(discardTextHelper1);
        // discardsBox.getChildren().add(cardToDiscard);
        // discardsBox.getChildren().add(discardTextHelper2);

        // grid.add(discardsBox, 1, 10);

        

        // the card we just drew
        Button cardDrawn = buildCardButton(null);
        cardDrawn.setVisible(false);
        cardDrawn.setDisable(true);
        HBox newCardRow = new HBox(cardDrawn);
        newCardRow.setAlignment(Pos.CENTER);
        grid.addRow(4, newCardRow);

        // a button to end the game
        Button endGameButton = new Button("End Game");
        HBox endGameBox = new HBox(10);
        endGameBox.getChildren().add(endGameButton);
        endGameBox.setAlignment(Pos.CENTER_RIGHT);
        grid.addRow(6, endGameBox);

        // endGameButton.setOnAction(new EventHandler<ActionEvent>() {
 
        //     @Override
        //     public void handle(ActionEvent e) {
        //         gameEndResult.setText(sim.GUIVerifyWin());
        //         computerHand.setText("Computer's hand was: " + sim.getComputerHand());
        //     }
        // });

        // now that we have our references, we can create the actions between them

        discardPile.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                String newCard = sim.drawCard(true);
                cardDrawn.setText("Card drawn: " + newCard);
            }
        });

        drawPile.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                String newCard = sim.drawCard(false);
                cardDrawn.setText("Card drawn: " + newCard);
                
            }
        });

        discardSelectedCardButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                if(! cardToDiscard.getText().equals("<NONE")){
                    int index = Integer.valueOf(cardToDiscard.getText());
                    sim.discardCard(index);
                    cardDrawn.setText("");
                    cardToDiscard.setText("<NONE>");
                    for(int i = 0; i < cardButtons.size(); i++){
                        cardButtons.get(i).setText(handRef.cards[i].getFormattedFullName());
                    }
                }
               // this is the end of the user's turn
               // TODO: display more info about computer's turn??
               // maybe in a text box
               sim.robotPlay();
            }
        });




        // button: "discard selected card"

        // Grey out buttons? How to 


        Scene scene = new Scene(grid, 600, 600);
       
        // create a background fill
        BackgroundFill background_fill = new BackgroundFill(
            Color.GRAY,
            CornerRadii.EMPTY,
            Insets.EMPTY
        );

        // create Background
        Background background = new Background(background_fill);

        // set background
        grid.setBackground(background);


        primaryStage.setScene(scene);

        primaryStage.show();
    
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
}