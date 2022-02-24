package org.firstinspires.ftc.teamcode.Subsystem;

import com.SCHSRobotics.HAL9001.system.robot.SubSystem;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Baguette;

//@TeleOp(name = "Arm Controls", group = "robot")
public class ArmController extends SubSystem {

    Servo elbowJoint;
    Servo clampServo;


    public ArmController(Baguette _robot, String BIG_S, String CLAMP_S){
        super(_robot);
        robot = _robot;
        elbowJoint = robot.hardwareMap.servo.get(BIG_S);
        clampServo = robot.hardwareMap.servo.get(CLAMP_S);
    }

    double armPos = 0.0;

    @Override
    public void init() {
        clampServo.setPosition(0.4);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void handle() {
        double py = -robot.gamepad1.left_stick_y;
        boolean down = robot.gamepad1.dpad_down;
        boolean up = robot.gamepad1.dpad_up;
        boolean grab = robot.gamepad1.right_bumper;
        boolean letGo = robot.gamepad1.left_bumper;
        boolean thirdLevel = robot.gamepad1.triangle;
        boolean secondLevel = robot.gamepad1.square;
        boolean firstLevel = robot.gamepad1.x;

        armPos = elbowJoint.getPosition();

        /*while(py > 0.5)
        {
            elbowJoint.setPosition(armPos + 1);
            armPos += 1;
        }
        while(py < -0.5)
        {
            elbowJoint.setPosition(armPos - 1);
            armPos -= 1;
        }*/

        if(down == true)
        {
            elbowJoint.setPosition(0);
        }
        else if(up == true)
        {
            robot.telemetry.addData("yeehaw", "servo");
            robot.telemetry.update();

            elbowJoint.setPosition(1);
        }

        if(grab == true)
        {
            clampServo.setPosition(0.4);
        }
        else if(letGo == true)
        {
            clampServo.setPosition(0);
        }

        if(firstLevel == true) {
            elbowJoint.setPosition(0.2);
        }
        else if(secondLevel == true)
        {
            elbowJoint.setPosition(0.4);
        }
        else if(thirdLevel == true)
        {
            elbowJoint.setPosition(0.7);
        }
    }

    @Override
    public void stop() {

    }


}