package org.firstinspires.ftc.teamcode.Autonomi;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.HALTrajectory;
import com.SCHSRobotics.HAL9001.util.math.geometry.Point2D;
import com.SCHSRobotics.HAL9001.util.math.geometry.Vector2D;
import com.SCHSRobotics.HAL9001.util.math.units.HALAngleUnit;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Baguette;

@Autonomous(name = "RedBack", group = "comp")
public class RedBack extends BaseAutonomous {
    public @MainRobot
    Baguette robot;

    private void drivePower (double pow, int angleDegrees) {
        //write code that uses the imu to see how far it goes, and add that to the parameters so its based off distance
        //robot.mDrive.movePower(new Vector2D(pow, angleDegrees, HALAngleUnit.DEGREES));
    }

    private void turnAngle (int angleDegrees, int toleranceDegrees) {
        robot.mDrive.turnPID(angleDegrees, HALAngleUnit.DEGREES, toleranceDegrees, HALAngleUnit.DEGREES );
    }

    @Override
    public void main() {
        robot.mDrive.setAllMotorZeroPowerBehaviors(DcMotor.ZeroPowerBehavior.FLOAT);
        /*HALTrajectory returnRoute = new HALTrajectory(robot.mDrive.trajectoryBuilder(forwardRoute.end(), 0).
                lineTo(new Point2D(0,0)).
                build().
                toRoadrunner(),
                CoordinateMode.HAL);
*/
        //robot.mDrive.followTrajectory(forwardRoute);
        //waitTime(1000);
        //robot.mDrive.turnPID(PI);
        // waitTime(1000);
        //robot.mDrive.turnPID(PI);
        //waitTime(1000);
        //robot.mDrive.turnPID(PI);
        //waitTime(1000);
        //robot.mDrive.followTrajectory(returnRoute);

        /*HALTrajectory rightMarker = new HALTrajectory(robot.mDrive.trajectoryBuilder(returnRoute.end(), 0).

                lineTo(new Point2D(-30,48)).
                build().
                toRoadrunner(),
                CoordinateMode.HAL);*/

        //robot.mDrive.followTrajectory(forwardRoute);
        //robot.mDrive.followTrajectory(returnRoute);
        //robot.mDrive.followTrajectory(rightMarker);


        //red side

        //scan
        HALTrajectory setForPlace = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Point2D(37, 22.5), 0)
                .build();

        HALTrajectory alignForPark = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Point2D(0, -12), 0)
                .build();

        HALTrajectory ducks = robot.mDrive.trajectoryBuilder(new Pose2d())
                .lineTo(new Point2D(-71, -3))
                .build();

        HALTrajectory parkPlace = robot.mDrive.trajectoryBuilder(new Pose2d())
                .lineTo(new Point2D(5, -16))
                .build();

        //robot.mDrive.followTrajectory(scootForward);
        //waitTime(500);
        //robot.mDrive.turnPID(-Math.PI/2); //or -3 pi
        // /2

        robot.mDrive.followTrajectory(setForPlace);
        waitTime(500);
        // robot.mDrive.turnPID(Math.PI);

        robot.arm2.dropArm(3);
        waitTime(500);

        robot.mDrive.followTrajectory(alignForPark);
        waitTime(500);

        robot.mDrive.followTrajectory(ducks);
        waitTime(500);

        robot.mDrive.turnTime(0.25, 3000);
        robot.mDrive.moveTime(new Vector2D(0, .25), 200);
        robot.spinner.spinSpinMotorTime(-0.25, 4000);

        robot.mDrive.turnTime(0.25, 150);
        robot.mDrive.followTrajectory(parkPlace);


        //robot.mDrive.followTrajectory(parkDepot);


    }
}
