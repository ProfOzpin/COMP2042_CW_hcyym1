package com.example.demo.game;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

/**
 * The GameScene class, Modified to include main menu, and take users username, and refactored by removing the movement functions into the Movement class.
 * @author Youssef Mohamed-modified
 */
class GameScene {
    private static final int HEIGHT = 700;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private final TextMaker textMaker = TextMaker.getSingleInstance();
    private final Cell[][] cells = new Cell[n][n];
    private Group root;
    private long score = 0;

    /**
     * Unmodified
     * @param number
     */
    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    /**
     * Unmodified
     * @return
     */
    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * Unmodified
     * @param turn
     */
    private void getEmptyCells(int turn) {

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }

        fillCells(aForBound, bForBound, emptyCells);
    }

    /**
     * Unmodified
     * @param aForBound
     * @param bForBound
     * @param emptyCells
     */
    private void fillCells(int aForBound, int bForBound, Cell[][] emptyCells){
        Text text;
        Random random = new Random();
        boolean putTwo = random.nextInt() % 2 != 0;
        int xCell, yCell;
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    /**
     * Unmodified
     * @return
     */
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }

    /**
     * Unmodified
     */
    private void sumCellNumbersToScore() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                score += cells[i][j].getNumber();
            }
        }
    }

    /**
     * New class. Used as a main menu.
     * Has a start menu, the change colour theme button, which calls the Colours class to use remap_colours().
     * Also has a text field to store the username, which is sent to EndGame as a parameter.
     * @author Youssef Mohamed
     * @param gameScene
     * @param root
     * @param primaryStage
     * @param endGameScene
     * @param endGameRoot
     */
    void main_menu(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
        this.root = root;



        Random random = new Random();


        Text title = new Text();
        root.getChildren().add(title);
        title.setText("2048");
        title.setFont(Font.font(100));
        title.relocate(340, 100);

        Text subtitle = new Text();
        root.getChildren().add(subtitle);
        subtitle.setText("Prepared by Youssef Mohamed");
        subtitle.setFont(Font.font(50));
        subtitle.relocate(150, 200);

        TextField username_input = new TextField("Username");
        username_input.relocate(375, 450);
        username_input.setPrefSize(150, 30);
        root.getChildren().add(username_input);



        Button startButton = new Button("START");
        startButton.setPrefSize(200,50);
        startButton.setTextFill(Color.BLUE);
        root.getChildren().add(startButton);
        startButton.relocate(350,500);
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String username = username_input.getText();
                root.getChildren().clear();
                game(gameScene, root, primaryStage, endGameScene, endGameRoot, username);
            }
        });

        Button colourButton = new Button("CHANGE COLOUR");
        colourButton.setPrefSize(200,50);
        colourButton.setTextFill(Color.BLUE);
        root.getChildren().add(colourButton);
        colourButton.relocate(350,560);
        colourButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameScene.setFill(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                Colours.remap_colours();
            }
        });


    }


    /**
     * Modified to send over the username.
     * Uses the Movement class to register movement, instead of holding all the functions in this class.
     * @param gameScene
     * @param root
     * @param primaryStage
     * @param endGameScene
     * @param endGameRoot
     * @param username
     */
    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot, String username) {
        this.root = root;

        username.replaceAll("\\s","");
        if(username.equals("Username")) {
            username = "Guest";
        }




        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }

        getEmptyCells(1);
        getEmptyCells(1);




        Text username_text = new Text();
        root.getChildren().add(username_text);
        username_text.setText(username);
        username_text.setFont(Font.font(25));
        username_text.relocate(750, 50);

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");




        String finalUsername = username;

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                int haveEmptyCell;
                if (key.getCode() == KeyCode.DOWN) {
                    Movement.moveDown(cells);
                } else if (key.getCode() == KeyCode.UP) {
                    Movement.moveUp(cells);
                } else if (key.getCode() == KeyCode.LEFT) {
                    Movement.moveLeft(cells);
                } else if (key.getCode() == KeyCode.RIGHT) {
                    Movement.moveRight(cells);
                }
                GameScene.this.sumCellNumbersToScore();
                scoreText.setText(score + "");
                haveEmptyCell = GameScene.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (Movement.canNotMove(cells)) {
                        primaryStage.setScene(endGameScene);

                        EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score, finalUsername);
                        root.getChildren().clear();
                        score = 0;
                    }
                } else if(haveEmptyCell == 1)
                    GameScene.this.getEmptyCells(2);
            });
        });



    }
}
