package com.example.demo.game;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The Cell class, Refactored with the help of the Colours class.
 * @author Youssef Mohamed-modified
 */
public class Cell{


    private final Rectangle rectangle;
    private final Group root;
    private Text textClass;
    private boolean modify = false;

    /**
     * Unmodified
     * @param modify
     */
    void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * Unmodified
     * @return modify
     */
    boolean getModify() {
        return modify;
    }

    /**
     * Unmodified
     * @param x
     * @param y
     * @param scale
     * @param root
     */
    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    /**
     * Unmodified
     * @param textClass
     */
    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Unmodified
     * @param cell
     */
    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Unmodified
     * @param cell
     */
    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }


    /**
     * Modified, now uses the Colours hashmap to get the colours.
     * Calls the Static Class Colours, using the method get_colours() to get the hashmap using the given number.
     * get_colours() returns an array with 4 different values, for each value needed for the colours.
     * @param number
     */
    public void setColorByNumber(int number) {

        Double[] parameters = Colours.get_colours().get(number);
        rectangle.setFill(Color.rgb(parameters[0].intValue(), parameters[1].intValue(), parameters[2].intValue(), parameters[3]));
    }

    /**
     * Unmodified
     * @return
     */
    double getX() {
        return rectangle.getX();
    }

    /**
     * Unmodified
     * @return
     */
    double getY() {
        return rectangle.getY();
    }

    /**
     * Unmodified
     * @return
     */
    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * Unmodified
     * @return
     */
    private Text getTextClass() {
        return textClass;
    }

}
