package com.example.demo.game;
import java.util.*;

public class Colours {
    HashMap<Integer, Double[]> colours= new HashMap<>();//Creating HashMap

    public HashMap<Integer, Double[]> initialize_colours(){
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
}
