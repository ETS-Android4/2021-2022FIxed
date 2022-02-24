package org.firstinspires.ftc.teamcode.Autonomi;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Baguette;

import static java.lang.Math.PI;

@Autonomous(name = "PIDTest", group = "Example Programs")
public class PIDTestAutonomous extends BaseAutonomous {
    public @MainRobot
    Baguette robot;
    //WeirdSpinnerHalWorkaround weird = new WeirdSpinnerHalWorkaround();



    @Override
    public void main() {


        //robot.mDrive.setTurnPID();

        //robot.mDrive.getLocalizer().setPoseEstimate(new Pose2d(0,0,0));

        robot.mDrive.turnPID(Math.PI/2, Math.PI/45);
        telemetry.addData(robot.mDrive.getLocalizer().getPoseEstimate().toString(), "imu");
        telemetry.addData("flag:", "1");
        telemetry.update();
        waitTime(1000);
        robot.mDrive.turnPID(PI/4);
        telemetry.addData(robot.mDrive.getLocalizer().getPoseEstimate().toString(), "imu");
        telemetry.addData("flag:", "2");
        telemetry.update();
        waitTime(1000);
        robot.mDrive.turnPID(3*PI/4);
        telemetry.addData(robot.mDrive.getLocalizer().getPoseEstimate().toString(), "imu");
        telemetry.addData("flag:", "3");
        telemetry.update();
        waitTime(1000);


    }

}
