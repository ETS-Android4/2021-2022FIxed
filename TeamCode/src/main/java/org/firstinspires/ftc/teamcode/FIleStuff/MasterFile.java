package org.firstinspires.ftc.teamcode.FIleStuff;

import java.io.File;
import java.io.IOException;

public class MasterFile {
    public static void main(String args[]) {
        File master = new File("/Users/aditya/PlaybackFiles/master1.txt");
        try {
            master.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
