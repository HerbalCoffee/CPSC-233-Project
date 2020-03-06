package cpscproject;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image ;
import java.io.FileInputStream;

public class GUIApplication extends Application
{
    public static void main(String [] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Group root = new Group();
        Scene scene = new Scene(root);
        StackPane baseStackPane = new StackPane();
        GridPane mapGridPane = new GridPane();
        GridPane entityGridPane = new GridPane();
        GridPane playerGridPane = new GridPane();
        GridPane hudGridPane = new GridPane();

        int fontSize = 18;
        Label playerHealth = new Label("  Health: ");
        playerHealth.setFont(new Font("Arial", fontSize));
        playerHealth.setTextFill(Color.web("#FF0000"));

        Label playerDamage = new Label("  Damage: ");
        playerDamage.setFont(new Font("Arial", fontSize));
        playerDamage.setTextFill(Color.web("#FF0000"));


    // Code Written by Yang Liu (2020)
        int width = 800;
        int height = 600;

        // Let's say the pane is 6x5
        int numCols = 15;
        int numRows = 10;

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth((double) width / numCols);
            mapGridPane.getColumnConstraints().add(colConst);
            entityGridPane.getColumnConstraints().add(colConst);
            playerGridPane.getColumnConstraints().add(colConst);
            hudGridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight((double) height / numRows);
            mapGridPane.getRowConstraints().add(rowConst);
            entityGridPane.getRowConstraints().add(rowConst);
            playerGridPane.getRowConstraints().add(rowConst);
            hudGridPane.getRowConstraints().add(rowConst);
        }
        //End of Yang Liu's Contributions

        Map theMap = new Map(TextApp.class.getResource("Map1.txt").getPath());

        //(Temporarily) create variables for player x and y location
        Player thePlayer = null;

        thePlayer = Spawner.spawnPlayer(theMap);

        playerGridPane.add(importPlayer(),thePlayer.getLocation().getX(),thePlayer.getLocation().getY());

        theMap.replaceElement(thePlayer.getLocation(), thePlayer);

        Spawner.spawnEnemies(theMap, 3);

        for(int index = 0; index < theMap.getEnemyList().size();index++){
            entityGridPane.add(importEnemy(),theMap.getEnemyList().get(index).getLocation().getX(),theMap.getEnemyList().get(index).getLocation().getY());
    }

        for (int row = 0; row < theMap.mapLayout.length; row++) {
            for (int column = 0; column < theMap.mapLayout[0].length; column++) {
                if(theMap.mapLayout[row][column] instanceof Exit){
                    mapGridPane.add(importDoor(),column,row);
                }else if (theMap.mapLayout[row][column] instanceof Wall){
                    mapGridPane.add(importWall(),column,row);
                }else{
                    mapGridPane.add(importOpenSpace(),column,row);
                }
            }
        }

        hudGridPane.add(playerHealth,12,0,3,3);
        hudGridPane.add(playerDamage,12,1,3,3);
        baseStackPane.getChildren().add(mapGridPane);
        baseStackPane.getChildren().add(entityGridPane);
        baseStackPane.getChildren().add(playerGridPane);
        baseStackPane.getChildren().add(hudGridPane);
        root.getChildren().add(baseStackPane);
        primaryStage.setTitle("Dungeon Disaster");
        primaryStage.setScene(scene);
        primaryStage.show();


        Player finalThePlayer = thePlayer;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    try {
                        playerGridPane.getChildren().clear();
                        finalThePlayer.moveUp(theMap);
                        playerGridPane.add(importPlayer(), finalThePlayer.getLocation().getX(), finalThePlayer.getLocation().getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if (event.getCode() == KeyCode.S){
                    try {
                        playerGridPane.getChildren().clear();
                        finalThePlayer.moveDown(theMap);
                        playerGridPane.add(importPlayer(), finalThePlayer.getLocation().getX(), finalThePlayer.getLocation().getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if (event.getCode() == KeyCode.A){
                    try {
                        playerGridPane.getChildren().clear();
                        finalThePlayer.moveLeft(theMap);
                        playerGridPane.add(importPlayer(), finalThePlayer.getLocation().getX(), finalThePlayer.getLocation().getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if (event.getCode() == KeyCode.D){
                    try {
                        playerGridPane.getChildren().clear();
                        finalThePlayer.moveRight(theMap);
                        playerGridPane.add(importPlayer(), finalThePlayer.getLocation().getX(), finalThePlayer.getLocation().getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public ImageView importOpenSpace() throws Exception{

        //Image Taken from: https://opengameart.org/content/stone-texture-bump
        Image image = new Image(new FileInputStream("CPSCProject/src/cpscproject/openSpace.jpg"), 50, 50,false , false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }

    public ImageView importWall() throws Exception{

        //Image Taken from: https://assetstore.unity.com/packages/2d/textures-materials/stone/stone-wall-texture-pack-01-30957
        Image image = new Image(new FileInputStream("CPSCProject/src/cpscproject/wall.jpg"), 50, 50,false , false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }

    public ImageView importDoor() throws Exception{

        //Image Taken from: https://assetstore.unity.com/packages/2d/textures-materials/stone/stone-wall-texture-pack-01-30957
        Image image = new Image(new FileInputStream("CPSCProject/src/cpscproject/doorSprite.png"), 50, 50,false , false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }

    public ImageView importPlayer() throws Exception{

        //Image Taken from: https://assetstore.unity.com/packages/2d/textures-materials/stone/stone-wall-texture-pack-01-30957
        Image image = new Image(new FileInputStream("CPSCProject/src/cpscproject/player.png"), 50, 50,false , false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }

    public ImageView importEnemy() throws Exception{

        //Image Taken from: https://assetstore.unity.com/packages/2d/textures-materials/stone/stone-wall-texture-pack-01-30957
        Image image = new Image(new FileInputStream("CPSCProject/src/cpscproject/enemy.png"), 50, 50,false , false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }


}