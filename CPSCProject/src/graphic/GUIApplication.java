package graphic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Alert;
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
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;



public class GUIApplication extends Application {
    
    /**
     * Main method for running the GUI application
     * 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Method for starting the GUI application and handling key events
     *
     * @param primaryStage The main stage of the application
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        StackPane baseStackPane = new StackPane();
        GridPane mapGridPane = new GridPane();
        GridPane entityGridPane = new GridPane();
        GridPane playerGridPane = new GridPane();
        GridPane hudGridPane = new GridPane();
        GridPane attackButtonPane = new GridPane();

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

        // Let's say the pane is 10x15
        int numCols = 15;
        int numRows = 10;

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth((double) width / numCols);
            mapGridPane.getColumnConstraints().add(colConst);
            entityGridPane.getColumnConstraints().add(colConst);
            playerGridPane.getColumnConstraints().add(colConst);
            hudGridPane.getColumnConstraints().add(colConst);
            attackButtonPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight((double) height / numRows);
            mapGridPane.getRowConstraints().add(rowConst);
            entityGridPane.getRowConstraints().add(rowConst);
            playerGridPane.getRowConstraints().add(rowConst);
            hudGridPane.getRowConstraints().add(rowConst);
            attackButtonPane.getRowConstraints().add(rowConst);
        }
        //End of Yang Liu's Contributions
        
        Map theMap = new Map(GUIApplication.class.getResource("/resources/Map1.txt").getPath());

        //(Temporarily) create variables for player x and y location
        Player thePlayer = null;

        thePlayer = Spawner.spawnPlayer(theMap);

        playerGridPane.add(importPlayer(), thePlayer.getLocation().getX(), thePlayer.getLocation().getY());

        theMap.replaceElement(thePlayer.getLocation(), thePlayer);

        Spawner.spawnEnemies(theMap, 3);

        Spawner.spawnConsumable(theMap, 2);
        Spawner.spawnWeapon(theMap, 1);

        for (int index = 0; index < theMap.getEnemyList().size(); index++) {
            entityGridPane.add(importEnemy(), theMap.getEnemyList().get(index).getLocation().getX(), theMap.getEnemyList().get(index).getLocation().getY());
        }

        for (int row = 0; row < theMap.mapLayout.length; row++) {
            for (int column = 0; column < theMap.mapLayout[0].length; column++) {
                if (theMap.mapLayout[row][column] instanceof Exit) {
                    mapGridPane.add(importDoor(), column, row);
                } else if (theMap.mapLayout[row][column] instanceof Wall) {
                    mapGridPane.add(importWall(), column, row);
                } else {
                    mapGridPane.add(importOpenSpace(), column, row);
                }
            }
        }

        for (int row = 0; row < theMap.mapLayout.length; row++) {
            for (int column = 0; column < theMap.mapLayout[0].length; column++) {
                if (theMap.mapLayout[row][column] instanceof Consumable) {
                    entityGridPane.add(importHealthPotion(), column, row);
                } else if (theMap.mapLayout[row][column] instanceof Weapon) {
                    entityGridPane.add(importIronSword(), column, row);
                }
            }
        }

        hudGridPane.add(playerHealth, 12, 0, 3, 3);
        hudGridPane.add(playerDamage, 12, 1, 3, 3);
        Button attackButton = new Button("Attack");
        attackButtonPane.add(attackButton,13,7,2,3);
        baseStackPane.getChildren().add(mapGridPane);
        baseStackPane.getChildren().add(entityGridPane);
        baseStackPane.getChildren().add(playerGridPane);
        baseStackPane.getChildren().add(hudGridPane);
        baseStackPane.getChildren().add(attackButtonPane);
        root.getChildren().add(baseStackPane);
        primaryStage.setTitle("Dungeon Disaster");
        primaryStage.setScene(scene);
        playerHealth.setText("  Health: " + Math.round(thePlayer.getHealth()));
        playerDamage.setText("  Damage: " + Math.round(thePlayer.getDamage("")));
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
                        doSpecialActions(theMap, finalThePlayer, entityGridPane, playerGridPane, attackButton);
                        playerHealth.setText("  Health: " + Math.round(finalThePlayer.getHealth()));
                        playerDamage.setText("  Damage: " + Math.round(finalThePlayer.getDamage("")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode() == KeyCode.S) {
                    try {
                        playerGridPane.getChildren().clear();
                        finalThePlayer.moveDown(theMap);
                        playerGridPane.add(importPlayer(), finalThePlayer.getLocation().getX(), finalThePlayer.getLocation().getY());
                        doSpecialActions(theMap, finalThePlayer, entityGridPane, playerGridPane, attackButton);
                        playerHealth.setText("  Health: " + Math.round(finalThePlayer.getHealth()));
                        playerDamage.setText("  Damage: " + Math.round(finalThePlayer.getDamage("")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode() == KeyCode.A) {
                    try {
                        playerGridPane.getChildren().clear();
                        finalThePlayer.moveLeft(theMap);
                        playerGridPane.add(importPlayer(), finalThePlayer.getLocation().getX(), finalThePlayer.getLocation().getY());
                        doSpecialActions(theMap, finalThePlayer, entityGridPane, playerGridPane, attackButton);
                        playerHealth.setText("  Health: " + Math.round(finalThePlayer.getHealth()));
                        playerDamage.setText("  Damage: " + Math.round(finalThePlayer.getDamage("")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode() == KeyCode.D) {
                    try {
                        playerGridPane.getChildren().clear();
                        finalThePlayer.moveRight(theMap);
                        playerGridPane.add(importPlayer(), finalThePlayer.getLocation().getX(), finalThePlayer.getLocation().getY());
                        doSpecialActions(theMap, finalThePlayer, entityGridPane, playerGridPane, attackButton);
                        playerHealth.setText("  Health: " + Math.round(finalThePlayer.getHealth()));
                        playerDamage.setText("  Damage: " + Math.round(finalThePlayer.getDamage("")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode() == KeyCode.H) {
                    finalThePlayer.useHealthPotion();
                    playerHealth.setText("  Health: " + Math.round(finalThePlayer.getHealth()));
                    playerDamage.setText("  Damage: " + Math.round(finalThePlayer.getDamage("")));
                }
            }
        });

    }
    
    /**
     * Method for importing a sprite representing an open space stored in the application source
     * 
     * @return an ImageView object of a sprite representing an open space
     * @throws Exception 
     */
    public ImageView importOpenSpace() throws Exception {

        //Image Taken from: https://opengameart.org/content/stone-texture-bump
        Image image = new Image(new FileInputStream(GUIApplication.class.getResource("/resources/openSpace.jpg").getPath()), 50, 50, false, false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }
    /**
     * Method for importing a sprite representing a wall stored in the application source
     *
     * @return an ImageView object of a sprite representing a wall
     * @throws Exception 
     */

    public ImageView importWall() throws Exception {

        //Image Taken from: https://assetstore.unity.com/packages/2d/textures-materials/stone/stone-wall-texture-pack-01-30957
        Image image = new Image(new FileInputStream(GUIApplication.class.getResource("/resources/wall.jpg").getPath()), 50, 50, false, false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }
    /**
     * Method for importing a sprite representing a door stored in the application source
     * 
     * @return an ImageView object of a sprite representing a door
     * @throws Exception 
     */

    public ImageView importDoor() throws Exception {

        //Image Taken from: https://nebula-games.itch.io/resident-evil-door-transition
        Image image = new Image(new FileInputStream(GUIApplication.class.getResource("/resources/doorSprite.png").getPath()), 50, 50, false, false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }
    
    /**
     * Method for importing a sprite representing the player stored in the application source
     * 
     * @return an ImageView object of a sprite representing the player
     * @throws Exception 
     */
    public ImageView importPlayer() throws Exception {

        //Image Taken from: https://www.pixilart.com/art/player-character-sprite-recreation-2-64629c0ba19e223
        Image image = new Image(new FileInputStream(GUIApplication.class.getResource("/resources/player.png").getPath()), 50, 50, false, false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }
    
    /**
     * Method for importing a sprite representing an enemy stored in the application source
     * 
     * @return an ImageView object of a sprite representing an enemy
     * @throws Exception 
     */
    public ImageView importEnemy() throws Exception {

        //Image Taken from: https://ya-webdesign.com/explore/transparent-sprite-enemy/
        Image image = new Image(new FileInputStream(GUIApplication.class.getResource("/resources/enemy.png").getPath()), 50, 50, false, false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }
    
    /**
     * Method for importing a sprite representing a health potion stored in the application source
     * 
     * @return an ImageView object of a sprite representing a health potion
     * @throws Exception 
     */
    public ImageView importHealthPotion() throws Exception {

        //Image Taken from: https://ya-webdesign.com/explore/potion-of-healing-png/
        Image image = new Image(new FileInputStream(GUIApplication.class.getResource("/resources/healthpotion.png").getPath()), 50, 50, false, false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }
    
    /**
     * Method for importing a sprite representing an iron sword stored in the application source
     * 
     * @return an ImageView object of a sprite representing an iron sword
     * @throws Exception 
     */
    public ImageView importIronSword() throws Exception {

        //Image Taken from: https://webstockreview.net/explore/clipart-sword-iron-sword/
        Image image = new Image(new FileInputStream(GUIApplication.class.getResource("/resources/ironsword.png").getPath()), 50, 50, false, false);

        ImageView imageView = new ImageView(image);

        return imageView;
    }
    
    /**
     * Method that runs special actions during gameplay. 
     * This handles removing elements from the map after entities have been collected/defeated, triggering enemy battles, and determining whether or not the player has completed the stage.
     * 
     * @param theMap the main Map object
     * @param aPlayer the player object
     * @param entityPane the GridPane on which enemies and items are spawned
     * @param playerPane the GridPane on which the player is spawned
     */

    public void doSpecialActions(Map theMap, Player aPlayer, GridPane entityPane, GridPane playerPane, Button attackButton) {
        if (theMap.getElement(aPlayer.getLocation()) != null) {
            char currInLoc = theMap.getElement(aPlayer.getLocation()).getChar();
            if (currInLoc == 'I') {
                aPlayer.addWeapon((Weapon) theMap.getElement(aPlayer.getLocation()));
                ImageView i = null;
                for (Node node : entityPane.getChildren()) {
                    if (node instanceof ImageView && entityPane.getRowIndex(node) == aPlayer.getLocation().getY() && entityPane.getColumnIndex(node) == aPlayer.getLocation().getX()) {
                        i = (ImageView) node;
                    }
                }
                entityPane.getChildren().remove(i);
                System.out.println("You picked up an Iron Sword!");
            }
            if (currInLoc == 'H') {
                aPlayer.addCollectible((Collectible) theMap.getElement(aPlayer.getLocation()));
                ImageView i = null;
                for (Node node : entityPane.getChildren()) {
                    if (node instanceof ImageView && entityPane.getRowIndex(node) == aPlayer.getLocation().getY() && entityPane.getColumnIndex(node) == aPlayer.getLocation().getX()) {
                        i = (ImageView) node;
                    }
                }
                entityPane.getChildren().remove(i);
                System.out.println("You picked up a health potion!");
            }
            if (currInLoc == 'E') {
                Scanner control = new Scanner(System.in);
                String direction;
                //Set-up for enemy encounters
                Enemy anEnemy = null;
                //Find the enemy in the list that the player encountered
                for (Enemy e : theMap.enemyList) {
                    if ((e.getLocation().getX() == aPlayer.getLocation().getX()) && (e.getLocation().getY() == aPlayer.getLocation().getY())) {
                        anEnemy = e;
                    }
                }
                //Start of enemy battle
                while (anEnemy.getHealth() > 0 && aPlayer.getHealth() >= 0) {
                    System.out.println("Player Health: " + Math.round(aPlayer.getHealth()));
                    System.out.println("Enemy Health: " + anEnemy.getHealth());
                    System.out.println("Press A to attack!");
                    direction = control.next();
                    if (direction.equalsIgnoreCase("a")) {
                        aPlayer.attack(anEnemy);
                        System.out.println("The enemy attacked!");
                        aPlayer.takeDamage(anEnemy.getDamage(direction));

                    } else {
                        System.out.println("Invalid Move! The enemy attacked when you were waiting!");
                        aPlayer.takeDamage(anEnemy.getDamage(direction));
                    }

                    if (aPlayer.getHealth() <= 0) {
                        anEnemy.setHealth(0);
                        System.out.println("You died!");
                        System.exit(0);
                    }
                }
                if (anEnemy.getHealth() <= 0 && aPlayer.getHealth() > 0) {
                    System.out.println("You killed the enemy!");
                    System.out.println("Player Health: " + aPlayer.getHealth());
                    theMap.removeEnemy(anEnemy);
                    ImageView i = null;
                    for (Node node : entityPane.getChildren()) {
                        if (node instanceof ImageView && entityPane.getRowIndex(node) == anEnemy.getLocation().getY() && entityPane.getColumnIndex(node) == anEnemy.getLocation().getX()) {
                            i = (ImageView) node;
                        }
                    }
                    entityPane.getChildren().remove(i);
                    aPlayer.setLevel(aPlayer.getLevel() + 1);
                    System.out.println("The Player's Level is: " + aPlayer.getLevel());

                }
            }
            if (currInLoc == 'C') {
                if (theMap.enemyList.isEmpty()) {
                    System.out.println("You reached the exit! Congratulations!");
                    System.exit(0);
                } else {
                    playerPane.getChildren().clear();
                    aPlayer.moveDown(theMap);
                    theMap.replaceElement(new Location(aPlayer.getLocation().getX(), aPlayer.getLocation().getY() - 1), new Exit(new Location(aPlayer.getLocation().getX(), aPlayer.getLocation().getY() - 1)));
                    try {
                        playerPane.add(importPlayer(), aPlayer.getLocation().getX(), aPlayer.getLocation().getY());
                    } catch (Exception ex) {
                        Logger.getLogger(GUIApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("You cannot use the exit! Not all enemies have been defeated!");
                }
            }

        }

    }

    public void doSpecialActions2(Map theMap, Player aPlayer, GridPane entityPane, GridPane playerPane, Button attackButton) {
        if (theMap.getElement(aPlayer.getLocation()) != null) {
            char currInLoc = theMap.getElement(aPlayer.getLocation()).getChar();
            if (currInLoc == 'I') {
                aPlayer.addWeapon((Weapon) theMap.getElement(aPlayer.getLocation()));
                ImageView i = null;
                for (Node node : entityPane.getChildren()) {
                    if (node instanceof ImageView && entityPane.getRowIndex(node) == aPlayer.getLocation().getY() && entityPane.getColumnIndex(node) == aPlayer.getLocation().getX()) {
                        i = (ImageView) node;
                    }
                }
                entityPane.getChildren().remove(i);
                System.out.println("You picked up an Iron Sword!");
            }
            if (currInLoc == 'H') {
                aPlayer.addCollectible((Collectible) theMap.getElement(aPlayer.getLocation()));
                ImageView i = null;
                for (Node node : entityPane.getChildren()) {
                    if (node instanceof ImageView && entityPane.getRowIndex(node) == aPlayer.getLocation().getY() && entityPane.getColumnIndex(node) == aPlayer.getLocation().getX()) {
                        i = (ImageView) node;
                    }
                }
                entityPane.getChildren().remove(i);
                System.out.println("You picked up a health potion!");
            }
            if (currInLoc == 'E') {
                //Set-up for enemy encounters
                Enemy anEnemy = null;
                boolean inBattle = true;
                //Find the enemy in the list that the player encountered
                for (Enemy e : theMap.enemyList) {
                    if ((e.getLocation().getX() == aPlayer.getLocation().getX()) && (e.getLocation().getY() == aPlayer.getLocation().getY())) {
                        anEnemy = e;
                    }
                }


                //Start of enemy battle
                while (anEnemy.getHealth() > 0 && aPlayer.getHealth() >= 0) {

                    Enemy finalAnEnemy = anEnemy;
                    boolean finalInBattle = inBattle;
                    attackButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent e) {
                            if (finalInBattle){
                                aPlayer.attack(finalAnEnemy);
                                aPlayer.takeDamage(finalAnEnemy.getDamage(""));
                                Alert details = new Alert(Alert.AlertType.INFORMATION);
                                details.setContentText("Player Health: " + Math.round(aPlayer.getHealth()) + "   Enemy Health: " + finalAnEnemy.getHealth());
                                details.show();


                            }
                        }
                    });

                    if (aPlayer.getHealth() <= 0) {
                        anEnemy.setHealth(0);
                        Alert deathMessage = new Alert(Alert.AlertType.WARNING);
                        deathMessage.setContentText("You Died!");
                        deathMessage.show();
                        System.exit(0);
                    }
                }
                if (anEnemy.getHealth() <= 0 && aPlayer.getHealth() > 0) {
                    System.out.println("You killed the enemy!");
                    System.out.println("Player Health: " + aPlayer.getHealth());
                    ImageView i = null;
                    for (Node node : entityPane.getChildren()) {
                        if (node instanceof ImageView && entityPane.getRowIndex(node) == anEnemy.getLocation().getY() && entityPane.getColumnIndex(node) == anEnemy.getLocation().getX()) {
                            i = (ImageView) node;
                        }
                    }
                    entityPane.getChildren().remove(i);
                    aPlayer.setLevel(aPlayer.getLevel() + 1);
                    System.out.println("The Player's Level is: " + aPlayer.getLevel());

                }
            }
            if (currInLoc == 'C') {
                if (theMap.enemyList.isEmpty()) {
                    System.out.println("You reached the exit! Congratulations!");
                    System.exit(0);
                } else {
                    playerPane.getChildren().clear();
                    aPlayer.moveDown(theMap);
                    theMap.replaceElement(new Location(aPlayer.getLocation().getX(), aPlayer.getLocation().getY() - 1), new Exit(new Location(aPlayer.getLocation().getX(), aPlayer.getLocation().getY() - 1)));
                    try {
                        playerPane.add(importPlayer(), aPlayer.getLocation().getX(), aPlayer.getLocation().getY());
                    } catch (Exception ex) {
                        Logger.getLogger(GUIApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("You cannot use the exit! Not all enemies have been defeated!");
                }
            }

        }

    }
}
