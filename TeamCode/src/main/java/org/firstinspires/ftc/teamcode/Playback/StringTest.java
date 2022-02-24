package org.firstinspires.ftc.teamcode.Playback;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

//gotta translate to HAL
@TeleOp(name = "stringTest", group = "playback")
public class StringTest extends LinearOpMode {
    BNO055IMU imu;
    Orientation angles;
    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        Servo servo = hardwareMap.servo.get("back_servo");
        try {
            while (!gamepad1.y) {
                telemetry.addData("press y to create a new playback auto file", "");
                telemetry.update();
            }
            while (gamepad1.y) {

            }
            BufferedReader reader = new BufferedReader(new FileReader("/Users/aditya/PlaybackFiles/master1.txt"));
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
            telemetry.addData("fileCheck: " + fileNumberCheck + ", fileNumber: " + fileNumber, ""); //check the numbers match
            File autoFile = new File("/Users/aditya/PlaybackFiles/playback" + fileNumber + ".txt");
            FileWriter masterWriter = new FileWriter("/Users/aditya/PlaybackFiles/master1.txt", true);

            //masterWriter.write("test");
            masterWriter.write(new Integer(fileNumber).toString() + "\n");
            telemetry.addData(autoFile.getAbsolutePath(), ""); //check the path of the new file and make sure the name is new
            telemetry.update();
            masterWriter.close();

            //now that the files made lets actually add data... ig everything gets to go in the try.
            FileWriter fw = new FileWriter(autoFile.getAbsoluteFile(), true);

            /*java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            System.setOut(new java.io.PrintStream(out));*/

            boolean buttonA = true;

            int pointCounter = 1;

            waitForStart();
            telemetry.addData("button key: ", "\nadd point: a\nfinish: press stop\n\n");
            telemetry.update();
            while(opModeIsActive()) {
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS);
                String data = "";
                if (gamepad1.a && buttonA) {
                    data = "";
                    data += angles.firstAngle + "," + angles.secondAngle + "," + angles.thirdAngle + "," + servo.getPosition();
                    telemetry.addData("button key: ", "\nadd frame: a\nfinish: press stop\n\n");
                    telemetry.addData("added data for frame " + pointCounter + " to the file: " + data, "");
                    telemetry.update();
                    pointCounter++;
                    fw.write(data + "\n");
                    //System.err.println("Out was: " + out.toString());
                    buttonA = false;
                }
                else if (!(gamepad1.a || buttonA)) {
                    buttonA = true;
                }
            }
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
