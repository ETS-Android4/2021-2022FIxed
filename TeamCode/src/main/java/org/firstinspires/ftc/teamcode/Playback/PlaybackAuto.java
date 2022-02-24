package org.firstinspires.ftc.teamcode.Playback;

import android.os.Environment;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Baguette;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

//gotta translate to HAL
@Autonomous(name = "playbackAuto", group = "playback")
public class PlaybackAuto extends BaseAutonomous {
    public @MainRobot
    Baguette robot;
    
    @Override
    public void main() {
        try {
            boolean buttonA = true;
            boolean buttonB = true;
            boolean runOnceBoolean = false;

            int numberOfFiles = 0;
            int fileNumber = 0;

            File directoryPath = new File(Environment.getExternalStorageDirectory().getPath() + "/System64");
            //List of all files and directories
            String contents[] = directoryPath.list();
            robot.telemetry.addData("Available Playbacks: (decide which one you want before you click start)", "");
            for(int i = 0; i < contents.length; i++) {
                robot.telemetry.addData("", contents[i]);
                numberOfFiles++;
            }
            //todo: playback key requires manual updating in the code if you want to use it
            robot.telemetry.addData("\n\n Playback Key: ", "\n 0: empty file, ignore it");
            robot.telemetry.addData("\n press y to move on", "");
            robot.telemetry.update();
            while (!robot.gamepad1.y) {

            }

            while (robot.gamepad1.y) {

            }
            robot.telemetry.addData("button key: ", "\nincrease: a\ndecrease: b\nfinish: y\n\n");
            robot.telemetry.addData("file number is: ", fileNumber);
            robot.telemetry.update();
            while (!robot.gamepad1.y) {
                if (robot.gamepad1.a && buttonA) { //increase fileNumber
                    robot.telemetry.addData("button key: ", "\nincrease: a\ndecrease: b\nfinish: y\n\n");
                    if ((fileNumber + 1 <= numberOfFiles)) {
                        fileNumber++;
                        robot.telemetry.addData("increased fileNumber. it's now: ", fileNumber);
                    }
                    else {
                        robot.telemetry.addData("cannot increase further, max file reached, number: ", fileNumber);
                    }
                    robot.telemetry.update();

                    buttonA = false;
                } else if (!(robot.gamepad1.a || buttonA)) { //if both are false
                    buttonA = true;
                }

                if (robot.gamepad1.b && buttonB) { //decrease fileNumber
                    robot.telemetry.addData("button key: ", "\nincrease: a\ndecrease: b\nfinish: y\n\n");
                    if (fileNumber - 1 >= 0) {
                        fileNumber -= 1;
                        robot.telemetry.addData("decreased fileNumber. it's now: ", fileNumber);

                    }
                    else {
                        robot.telemetry.addData("cannot decrease further, lowest file reached, number: ", fileNumber);
                    }
                    robot.telemetry.update();
                    buttonB = false;
                } else if (!(robot.gamepad1.b || buttonB)) { //if both are false
                    buttonB = true;
                }
            }
            robot.telemetry.addData("chose file number: ", fileNumber);
            robot.telemetry.update();

            //check the outcome and create the reader for the auto file
            String filePath = Environment.getExternalStorageDirectory().getPath() + "/System64/playback" + fileNumber + ".txt";
            System.out.println("file path: " + filePath + " fileNumber: " + fileNumber); //check the numbers match
            BufferedReader autoReader = new BufferedReader(new FileReader(filePath));

            String line;
            int numberOfFrames = 0;

            ArrayList<Double> xPosArray = new ArrayList<>();
            ArrayList<Double> yPosArray = new ArrayList<>();
            ArrayList<Double> headingArray = new ArrayList<>();

            ArrayList<Double> elbowPosArray = new ArrayList<>();
            ArrayList<Double> clampPosArray = new ArrayList<>();

            ArrayList<Double> spinnerPowerArray = new ArrayList<>();

            ArrayList<Double> intakePowerArray = new ArrayList<>();
            ArrayList<Double> slidesPowerArray = new ArrayList<>();

            //todo: important!! create a new arrayList for each new data you have (i.e. adding in servo data)

            while ((line = autoReader.readLine()) != null) {
                System.out.println("line: " + line);
                numberOfFrames++;

                int commaPosClose;
                int commaPosFar = 0;
                int dataTracker = 0;

                //find comma positions and then grab doubles in between the comma positions
                //line length is just a precaution here, the main constrictor is the angle tracker
                //todo: important!! data tracker number must be one less than the number of cases
                for(int i = 0; i < line.length() && dataTracker != 3; i++) {
                    if (line.substring(i, i + 1).equals(",")) { //if we're at a comma
                        commaPosClose = commaPosFar + 1; //set the close to the previous far + 1 to avoid the comma
                        commaPosFar = i; //change the far to the current pos
                        dataTracker++;

                        //case for each angle instead of ifs
                        //the second to case must not have a break, or you must put the last data code with the second to last data code
                        switch (dataTracker) {
                            case 1:
                                xPosArray.add(Double.parseDouble(line.substring(0, commaPosFar)));
                                break;

                            case 2: //get angle 2 and 3 bc there are no more commas and the code will go out of bounds
                                yPosArray.add(Double.parseDouble(line.substring(commaPosClose, commaPosFar)));
                                break;

                            case 3: //get angle 2 and 3 bc there are no more commas and the code will go out of bounds
                                headingArray.add(Double.parseDouble(line.substring(commaPosClose, commaPosFar)));
                                break;

                            case 4: //get angle 2 and 3 bc there are no more commas and the code will go out of bounds
                                elbowPosArray.add(Double.parseDouble(line.substring(commaPosClose, commaPosFar)));
                                break;

                            case 5: //get angle 2 and 3 bc there are no more commas and the code will go out of bounds
                                clampPosArray.add(Double.parseDouble(line.substring(commaPosClose, commaPosFar)));
                                break;

                            case 6: //get angle 2 and 3 bc there are no more commas and the code will go out of bounds
                                spinnerPowerArray.add(Double.parseDouble(line.substring(commaPosClose, commaPosFar)));
                                break;

                            case 7:
                                intakePowerArray.add(Double.parseDouble(line.substring(commaPosClose, commaPosFar)));
                                slidesPowerArray.add(Double.parseDouble(line.substring(commaPosFar + 1)));
                                break;

                            default:
                                System.out.println("switch went default for some reason, dataTracker: " + dataTracker);
                                break;
                        }
                    }
                }
            }
            robot.telemetry.addData("path from file: ", fileNumber + " grabbed\n press start to run");
            robot.telemetry.update();
            autoReader.close();

            //put a bunch of buttons to keep things going and then actually have the movement happen after wait for start

            waitForStart();
            while(opModeIsActive()) {
                for (int i = 1; i < numberOfFrames && !runOnceBoolean; i++) { //for each point, print out the angles
                    //waitTime();
                    //move(xPosArray.get(i)...);
                    robot.mDrive.moveSimple((xPosArray.get(i) - xPosArray.get(i - 1)), (yPosArray.get(i) - yPosArray.get(i - 1)), 0.4);
                    robot.arm2.setElbowPosition(elbowPosArray.get(i));
                    robot.arm2.setClampPosition(clampPosArray.get(i));
                    robot.spinner.spinSpinMotorToggle(spinnerPowerArray.get(i) != 0);
                    System.out.println("data received for frame " + i + ": " + xPosArray.get(i) + "," + yPosArray.get(i) + "," + headingArray.get(i) + "," + elbowPosArray.get(i) + "," + clampPosArray.get(i) + "," + spinnerPowerArray.get(i) + "," + intakePowerArray.get(i) + "," + slidesPowerArray.get(i));
                }
                runOnceBoolean = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
