/*
Adapted from the Java FX Form example from
https://docs.oracle.com/javafx/2/get_started/form.htm
*/

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

 
public class UglyInterface extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
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

        DrawFromDiscardButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Draw from discard pressed");
            }
        });

        Button drawButton = new Button("Draw from deck");
        hbBtn.getChildren().add(drawButton);

        drawButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.PURPLE);
                actiontarget.setText("Draw from deck button pressed");
            }
        });

        HBox cardBox = new HBox(10);
       
        // TODO: in final version, get card label from hand
        // I also think it would make sense for the card buttons to be an array? 
        Button card1 = new Button("(e.g.) Queen of Hearts");
        Button card2 = new Button("Card 2");
        Button card3 = new Button("Card 3");
        Button card4 = new Button("Card 4");
        Button card5 = new Button("Card 5");
        Button card6 = new Button("Card 6");
        Button card7 = new Button("Card 7");

        cardBox.getChildren().add(card1);
        cardBox.getChildren().add(card2);
        cardBox.getChildren().add(card3);
        cardBox.getChildren().add(card4);
        cardBox.getChildren().add(card5);
        cardBox.getChildren().add(card6);
        cardBox.getChildren().add(card7);

        grid.add(cardBox, 1, 8);

        // action item: when a button is selected, display "[card] selected"

        card1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                drawButton.setText("Card 1 selected");
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