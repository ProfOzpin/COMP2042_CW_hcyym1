package com.example.demo.user;
import javafx.scene.text.Font;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.FileWriter;

/**
 * Account class, heavily modified. Now contains only one method, which searches for usernames in highscore_txt.
 * @author Youssef Mohamed-modified
 */
public class Account {

    /**
     * New function, reads highscore.txt
     * It reads through every line and stores it in a string.
     * It splits each line using a blank space to read for username and score.
     * If the username matches what was passed as a parameter, it compares the score.
     * If the score is higher than the stored highscore, then instead of storing the line into a string, it creates another line, replacing the stored highscore with the new one.
     * Otherwise, stores the original line in a string.
     * Rewrites to highscore.txt.
     * Returns are used to indicate if the highscore is new, the stored highscore (unchanged), and if the username was found.
     * @author Youssef Mohamed
     * @param userName
     * @param score
     * @return new_highscore, stored_highscore, found
     */
    public List<Object> score_Compare(String userName, Long score){
        long stored_highscore = 0;
        boolean new_highscore = false;
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
                if(split_line[0].equals(userName)){
                    stored_highscore = Long.parseLong(split_line[1]);
                    found = true;
                    if(score > stored_highscore) {
                        System.out.println("in here");
                        total_string += userName + " " + score + "\n";
                        new_highscore = true;
                        System.out.println(new_highscore);
                    } else {
                        total_string += line + "\n";
                    }

                } else {
                    total_string += line + "\n";
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(found == false){
            total_string += userName + " " + score + "\n";
        }


        try {
            FileWriter writer = new FileWriter(obj, false);
            writer.write(total_string);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("end");
        System.out.println(new_highscore);
        return Arrays.asList(new_highscore, stored_highscore, found);
    }


}
