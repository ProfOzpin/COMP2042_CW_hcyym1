package com.example.demo.game;
import java.util.*;
import java.util.Random;

/**
 * The Colours class, Contains hashmap which contains the colours for the cells.
 * Made static, because the colour scheme needs to affect all the scenes, not specific ones.
 * @author Youssef Mohamed
 */
public class Colours {
    static HashMap<Integer, Double[]> colours= new HashMap<>();//Creating HashMap
    static Random random = new Random();

    /**
     * Initializes the values for the hashmap. Runs in the main function, this is to initialize the hashmap only once.
     * So if the user changes the colour scheme, it would stay after he resets the game.
     */
    static void initialize_colours(){
        colours.put(0, new Double[] {224.0, 226.0, 226.0, 0.5});
        colours.put(2, new Double[] {232.0, 255.0, 100.0, 0.5});
        colours.put(4, new Double[] {232.0, 220.0, 50.0, 0.5});
        colours.put(8, new Double[] {232.0, 200.0, 44.0, 0.8});
        colours.put(16, new Double[] {232.0, 170.0, 44.0, 0.8});
        colours.put(32, new Double[] {180.0, 120.0, 44.0, 0.7});
        colours.put(64, new Double[] {180.0, 100.0, 44.0, 0.7});
        colours.put(128, new Double[] {180.0, 80.0, 44.0, 0.7});
        colours.put(256, new Double[] {180.0, 60.0, 44.0,0.8});
        colours.put(512, new Double[] {180.0, 30.0, 44.0, 0.8});
        colours.put(1024, new Double[] {250.0, 0.0, 44.0, 0.8});
        colours.put(2048, new Double[] {250.0, 0.0, 0.0, 1.0});
    }

    /**
     * Returns the hashmap, used to get the colours.
     * @return
     */
    static public HashMap<Integer, Double[]> get_colours(){
        return colours;
    }

    /**
     * Used to randomize the colours.
     * What it does is randomize the colour code for the Cell of Number 2, between 0 and 35.
     * Then, it consecutively adds 20 to each of the values.
     * This ensures different shades of the same colour for each cell.
     * And theoretically creates an insanely large number of colour schemes.
     */
    static void remap_colours(){
        int val_1 = random.nextInt(35);
        int val_2 = random.nextInt(35);
        int val_3 = random.nextInt(35);
        for(int i = 2; i <= 2048; i = i * 2){
            colours.put(i, new Double[] {Double.valueOf(val_1), Double.valueOf(val_2), Double.valueOf(val_3), random.nextDouble()});
            val_1 += 20;
            val_2 += 20;
            val_3 += 20;
        }
    }
}
