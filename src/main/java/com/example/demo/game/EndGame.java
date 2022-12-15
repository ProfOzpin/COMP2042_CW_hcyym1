package com.example.demo.game;
import com.example.demo.user.Account;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * EndGame class, Modified to show the users highscore, and indicate if new highscore.
 * @author Youssef Mohamed-modified
 */
public class EndGame {


    private static EndGame singleInstance = null;

    /**
     * Unmodified
     */
    private EndGame(){

    }

    /**
     * Unmodified
     * @return
     */
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    /**
     * Modified to add the highscore alerts. It also calls the Account class, in order to search for stored highscores.
     * Using score_Compare from Account class, it checks for 4 differnet possibilies.
     * 1, the account is a guest account, and will not have a saved highscore, and should enter a username next time.
     * 2, it is a new user, as the username is not present in highscore.txt, and therefore it is automatcally a new highscore and is stored in the text file.
     * 3, it a returning user and they have a new highscore, they are alerted accordingly.
     * 4, it is a returning user and they have not achieved a new highscore, they are alerted accordingly.
     * @param endGameScene
     * @param root
     * @param primaryStage
     * @param score
     * @param username
     */
    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score, String username){
        Account account_handler = new Account();

        Text text = new Text("GAME OVER");
        text.relocate(250,250);

        text.setFont(Font.font(80));
        root.getChildren().add(text);

        Text scoreText = new Text("Score: "+score);
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(250,350);
        scoreText.setFont(Font.font(80));
        root.getChildren().add(scoreText);

        Text highscore = new Text("");
        highscore.setFill(Color.BLACK);
        highscore.relocate(250,450);
        highscore.setFont(Font.font(80));
        root.getChildren().add(highscore);

        Text highscore_alert = new Text("");
        highscore_alert.setFill(Color.BLACK);
        highscore_alert.relocate(250,550);
        highscore_alert.setFont(Font.font(80));
        root.getChildren().add(highscore_alert);

        Text unregistered_alert = new Text("");
        unregistered_alert.setFill(Color.BLACK);
        unregistered_alert.relocate(100,550);
        unregistered_alert.setFont(Font.font(30));
        root.getChildren().add(unregistered_alert);

        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.BLUE);
        root.getChildren().add(quitButton);
        quitButton.relocate(350,650);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quit Dialog");
                alert.setHeaderText("Quit from this page");
                alert.setContentText("Are you sure?");



                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    root.getChildren().clear();
                }
            }
        });

        Button retryButton = new Button("RETRY");
        retryButton.setPrefSize(100,30);
        retryButton.setTextFill(Color.BLUE);
        root.getChildren().add(retryButton);
        retryButton.relocate(470,650);
        retryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main main = new Main();
                try {
                    main.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if(!username.equals("Guest")){
            List<?> result = account_handler.score_Compare(username, score);

            if((boolean) result.get(2)){
                highscore.setText("Highscore: "+ result.get(1));
                if((boolean) result.get(0)){
                    highscore_alert.setText("New Highscore!");
                } else {
                    highscore_alert.setFont(Font.font(30));
                    highscore_alert.setText("Not a new highscore, better luck next time!");
                }
            } else {
                highscore_alert.setText("New Highscore!");
                highscore.setText("Highscore: "+ score);
            }


        }


    }
}
