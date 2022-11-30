package com.example.demo.game;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Optional;
import java.util.Scanner;


public class EndGame {


    private static EndGame singleInstance = null;
    private EndGame(){

    }
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
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


        Button quitButton = new Button("QUIT");
        quitButton.setPrefSize(100,30);
        quitButton.setTextFill(Color.PINK);
        root.getChildren().add(quitButton);
        quitButton.relocate(100,800);
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

        long stored_highscore = 0;
        String line = "";
        File obj = new File("src/main/java/com/example/demo/game/highscore.txt");
        try {
            Scanner scanner = new Scanner(obj);
            line = scanner.nextLine();
            stored_highscore = Long.parseLong(line);
            scanner.close();
            highscore.setText("Highscore: "+stored_highscore);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(score > stored_highscore){
            try {

                FileWriter writer = new FileWriter(obj, false);
                writer.write(Long.toString(score));
                writer.flush();
                writer.close();
                highscore_alert.setText("New Highscore!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }






    }
}
