package cpscproject;


import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
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
        Button test = new Button("Obama");

        //Setting size for the pane
        baseGridPane.setMinSize(1400, 700);

        //Setting the padding
        baseGridPane.setPadding(new Insets(10, 10, 10, 10));


        baseGridPane.add(importCobbleStone(), 0,0);
        baseGridPane.add(importCobbleStone(),0,1);
        baseGridPane.add(importCobbleStone(),1,0);
        baseGridPane.add(test,0,2);



        root.getChildren().add(baseGridPane);
        primaryStage.setTitle("WIP Name");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public ImageView importCobbleStone() throws Exception{

        Image image = new Image(new FileInputStream("CPSCProject/src/cpscproject/stone2.jpg"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        return imageView;
    }

}