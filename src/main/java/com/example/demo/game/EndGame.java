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

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score, String username){
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
            long stored_highscore = 0;
            String line = "";
            String[] split_line;
            File obj = new File("src/main/java/com/example/demo/game/highscore.txt");
            String total_string = "";
            boolean found = false;
            try {
                Scanner scanner = new Scanner(obj);
                while(scanner.hasNextLine()){
                    line = scanner.nextLine();
                    split_line = line.split("\\s");
                    if(split_line[0].equals(username)){
                        stored_highscore = Long.parseLong(split_line[1]);
                        highscore.setText("Highscore: "+stored_highscore);
                        found = true;
                        if(score > stored_highscore){
                            total_string += username + " " + stored_highscore + "\n";
                            highscore_alert.setText("New Highscore!");

                        }

                    } else {
                        total_string += line + "\n";
                        highscore_alert.setFont(Font.font(30));
                        highscore_alert.setText("Not a new highscore, better luck next time!");
                    }

                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(found == false){
                total_string += username + " " + score + "\n";
                highscore_alert.setText("New Highscore!");

            }

            try {

                FileWriter writer = new FileWriter(obj, false);
                writer.write(total_string);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            unregistered_alert.setText("You are not a registered user.\nPlease insert username next time to save your highscores!");
        }


    }
}
