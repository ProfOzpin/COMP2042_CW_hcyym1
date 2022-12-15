package com.example.demo.game;
import java.util.*;
import java.util.Random;

/**
 * The Colours class. Contains hashmap which contains the colours for the cells.
 * @author Youssef Mohamed
 */
public class Colours {
    static HashMap<Integer, Double[]> colours= new HashMap<>();//Creating HashMap
    static Random random = new Random();

    static public HashMap<Integer, Double[]> initialize_colours(){
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
        return colours;
    }

    static public HashMap<Integer, Double[]> get_colours(){
        return colours;
    }

    static public HashMap<Integer, Double[]> remap_colours(){
        int val_1 = random.nextInt(35);
        int val_2 = random.nextInt(35);
        int val_3 = random.nextInt(35);
        for(int i = 2; i <= 2048; i = i * 2){
            colours.put(i, new Double[] {Double.valueOf(val_1), Double.valueOf(val_2), Double.valueOf(val_3), random.nextDouble()});
            val_1 += 20;
            val_2 += 20;
            val_3 += 20;
        }
        return colours;
    }
}
