package Simulation;

import java.util.ArrayList;

import CardsAndPiles.Card;
import CardsAndPiles.Hand;
import Display.BoardDisplay;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
// import javafx.scene.*;
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

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GameSimulator sim = new GameSimulator();
        BoardDisplay display = new BoardDisplay();

        sim.linkToDisplay(display);
        sim.setupNewGame();
        sim.startGame();
        
    
        primaryStage.setTitle("OOAD Rummy");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Rummy");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        

        Button DrawFromDiscardButton = new Button("Draw from discard");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(DrawFromDiscardButton);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);



        Button drawButton = new Button("Draw from deck");
        hbBtn.getChildren().add(drawButton);


        Hand handRef = display.userRef.getHand();

        HBox cardBox = new HBox(10);
        
        ArrayList<Button> cards = new ArrayList<Button>();
        for(Card card : handRef.cards){
            Button cardButton = new Button(card.getFormattedFullName());
            cards.add(cardButton);
            cardBox.getChildren().add(cardButton);
        }

        if(handRef.justDrawn != null){
            Button cardButton = new Button(handRef.justDrawn.getFormattedFullName());
            cards.add(cardButton);
            cardBox.getChildren().add(cardButton);
        }

        grid.add(cardBox, 1, 8);

        HBox discardsBox = new HBox(10);
        Button discardSelectedCardButton = new Button("Discard Selected Card");
        Text discardTextHelper1 = new Text("Card");
        Text cardToDiscard = new Text("<NONE>");
        Text discardTextHelper2 = new Text(" selected to Discard");
        discardsBox.getChildren().add(discardSelectedCardButton);
        discardsBox.getChildren().add(discardTextHelper1);
        discardsBox.getChildren().add(cardToDiscard);
        discardsBox.getChildren().add(discardTextHelper2);

        grid.add(discardsBox, 1, 10);
        int discardIndex = -1;

        for(int i = 0; i < cards.size(); i++){

            // int local_i = i;
            // EventHandler<ActionEvent> event = x -> { drawButton.setText("Card " + local_i + " selected"); };
            // cards.get(i).setOnAction(event);
            
            int local_i = i;
            cards.get(i).setOnAction(event -> { cardToDiscard.setText("" + local_i); });

            // cards.get(i).setOnAction(new EventHandler<ActionEvent>() {

            //     int cardNum = i;
    
            //     @Override
            //     public void handle(ActionEvent e) {
            //         // this doesn't work, i is not in scope or something
            //         drawButton.setText("Card " + cardNum + " selected");
            //     }
            // });
        }

        HBox newCardBox = new HBox(10);
        Text cardDrawn = new Text();
        newCardBox.getChildren().add(cardDrawn);
        grid.add(newCardBox, 1, 12);


        DrawFromDiscardButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Draw from discard pressed");
                // TODO: Display top of discard pile
                String newCard = sim.drawCard(true);
                cardDrawn.setText("Card drawn: " + newCard);
            }
        });

        drawButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.PURPLE);
                actiontarget.setText("Draw from deck button pressed");
                String newCard = sim.drawCard(true);
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
                    for(int i = 0; i < cards.size(); i++){
                        cards.get(i).setText(handRef.cards[i].getFormattedFullName());
                    }
                }
                // TODO: update discardPile
               // this is the end of the user's turn
               //TODO: switch to robot turn
            }
        });




        // button: "discard selected card"

        // Grey out buttons? How to 


        Scene scene = new Scene(grid, 500, 500);
       
        // create a background fill
        BackgroundFill background_fill = new BackgroundFill(Color.FORESTGREEN, 
        CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background = new Background(background_fill);

        // set background
        grid.setBackground(background);


        primaryStage.setScene(scene);

        primaryStage.show();
    
    }
}