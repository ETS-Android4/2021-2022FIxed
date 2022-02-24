package org.firstinspires.ftc.teamcode.Playback;

import android.os.Environment;

import com.SCHSRobotics.HAL9001.system.robot.Robot;
import com.SCHSRobotics.HAL9001.system.robot.SubSystem;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Baguette;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

//gotta translate to HAL
public class PlaybackFileCreation extends SubSystem {

    /**
     * Constructor for subsystem.
     *
     * @param robot The robot the subsystem is contained within.
     */
    Baguette robot;
    public PlaybackFileCreation(@NotNull Baguette _robot) {
        super(_robot);
        robot = _robot;
    }
    File autoFile;

    @Override
    public void init() {
        try {
            while (!robot.gamepad1.y) {
                robot.telemetry.addData("press y to create a new playback auto file", "");
                robot.telemetry.update();
            }
            while (robot.gamepad1.y) {

            }
            BufferedReader reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/System64" + "/master1.txt"));
            int fileNumber = 0;
            int fileNumberCheck = 0;
            String fileCheck;
            while ((fileCheck = reader.readLine()) != null) {
                System.out.println(fileCheck);
                if (fileCheck != null) {
                    fileNumberCheck = Integer.parseInt(fileCheck);
                }
                /*if (fileNumberCheck == 0) {
                    fileNumber++;
                }*/
                //read the line and save it to a variable to check later
                fileNumber++;
            }
            robot.telemetry.addData("fileCheck: " + fileNumberCheck + ", fileNumber: " + fileNumber, ""); //check the numbers match
            File autoFile = new File(Environment.getExternalStorageDirectory().getPath() + "/System64/playback" + fileNumber + ".txt");
            FileWriter masterWriter = new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/System64" + "/master1.txt", true);

            //masterWriter.write("test");
            masterWriter.write(new Integer(fileNumber).toString() + "\n");
            robot.telemetry.addData(autoFile.getAbsolutePath(), ""); //check the path of the new file and make sure the name is new
            robot.telemetry.update();
            masterWriter.close();

            //now that the files made lets actually add data... ig everything gets to go in the try.

            /*java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            System.setOut(new java.io.PrintStream(out));*/

        }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        robot.telemetry.addData("button key: ", "\nadd point: a\nfinish: press stop\n\n");
        robot.telemetry.update();
    }

    boolean buttonA = true;
    int frameCounter = 1;

    @Override
    public void handle() {
        try {
            FileWriter fw = new FileWriter(autoFile.getAbsoluteFile(), true);

            String data = "";
            if (robot.gamepad1.a && buttonA) {
                data = "";
                data += robot.mDrive.getLocalizer().getPoseEstimate().getX() + "," + robot.mDrive.getLocalizer().getPoseEstimate().getY() + "," + robot.mDrive.getLocalizer().getPoseEstimate().getHeading() + "," + robot.arm2.getElbowPosition() + "," + robot.arm2.getClampPosition() + "," + robot.spinner.getSpinnerPower() + "," + robot.intake.getIntakePower() + "," + robot.intake.getSlidesPower();
                robot.telemetry.addData("button key: ", "\nadd frame: a\nfinish: press stop\n\n");
                robot.telemetry.addData("added data for frame " + frameCounter + " to the file: " + data, "");
                robot.telemetry.update();
                frameCounter++;
                fw.write(data + "\n");
                //System.err.println("Out was: " + out.toString());
                buttonA = false;
            } else if (!(robot.gamepad1.a || buttonA)) {
                buttonA = true;
            }
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
