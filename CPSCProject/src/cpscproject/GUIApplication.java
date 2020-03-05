package cpscproject;


import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
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
        GridPane baseGridPane = new GridPane();

    // Code Written by Yang Liu (2020)
        int width = 800;
        int height = 600;

        // Let's say the pane is 6x5
        int numCols = 15;
        int numRows = 10;

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth((double) width / numCols);
            baseGridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight((double) height / numRows);
            baseGridPane.getRowConstraints().add(rowConst);
        }
        //End of Yang Liu's Contributions

        Map theMap = new Map(TextApp.class.getResource("Map3.txt").getPath());

        for (int row = 0; row < theMap.mapLayout.length; row++) {
            for (int column = 0; column < theMap.mapLayout[0].length; column++) {
                if(theMap.mapLayout[row][column] == null){
                    baseGridPane.add(importOpenSpace(),column,row);
                }else if (theMap.mapLayout[row][column] instanceof Wall){
                    baseGridPane.add(importWall(),column,row);
                }
            }
        }



        root.getChildren().add(baseGridPane);
        primaryStage.setTitle("WIP Name");
        primaryStage.setScene(scene);
        primaryStage.show();
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

}