package gui;
//TODO CONFIGURE LAYOUT FOR BACKGROUND AND GAME AREA, TO MAKE THEM LINE UP
//AND ALSO CREATE USER INFORMATION SECTION

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.*;

public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //stage.setTitle("Program");
        
        // 800x600 Window
        int width = 800;
        int height = 600;
        // 10x15 Grid
        int numCols = 15;
        int numRows = 10;
        
        StackPane layers = new StackPane(); // Root
        GridPane backGrid = new GridPane(); // Background, populated with floor tiles CAN CHANGE FOR SOMETHING ELSE
        VBox gameBox = new VBox(); // Main box. Contains the user interface on the bottom and display on the top
        GridPane gameGrid = new GridPane(); // Grid that contains all the entities. Goes inside "game" 
        GridPane infoGrid = new GridPane(); // Shows user information such as health. Goes inside "game"
        // CAN ALSO HAVE A CONSOLE AS AN INVISIBLE BACKGROUND TEXTAREA IN SEPARATE LAYER ON TOP OF gameGrid
        
        // Layout settings for user information grid
        infoGrid.setAlignment(Pos.TOP_CENTER);
        infoGrid.setHgap(10);
        infoGrid.setVgap(10);
        infoGrid.setPadding(new Insets(10, 10, 10, 10));
        
        // User Information (ADD LATER)
        
        
        
        
         // Code Written by Yang Liu (2020) 
         // Determines the size of each cell in the grid
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth((double) width / numCols);
            backGrid.getColumnConstraints().add(colConst);
            gameGrid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight((double) height / numRows);
            backGrid.getRowConstraints().add(rowConst);
            gameGrid.getRowConstraints().add(rowConst);
        }
        //End of Yang Liu's Contributions
        
        Scene gameScene = new Scene(layers, width, height); // Main scene where the game happens
        
        gameBox.getChildren().add(0, gameGrid);
        gameBox.getChildren().add(1, infoGrid);
        layers.getChildren().add(backGrid);
        layers.getChildren().add(gameBox);

        stage.setScene(gameScene);
        stage.show();
    }

}
