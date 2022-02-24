package org.firstinspires.ftc.teamcode.Autonomi;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.CoordinateMode;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.HALTrajectory;
import com.SCHSRobotics.HAL9001.util.math.geometry.Point2D;
import com.SCHSRobotics.HAL9001.util.math.units.HALAngleUnit;
import com.SCHSRobotics.HAL9001.util.math.units.HALDistanceUnit;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Baguette;

@Autonomous(name = "markerGrab", group = "tests")
public class markerGrabTests extends BaseAutonomous {
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
        HALTrajectory forwardRoute = new HALTrajectory(robot.mDrive.trajectoryBuilder(new Pose2d(0,0, 0), HALDistanceUnit.INCHES, HALAngleUnit.DEGREES).

                lineTo(new Point2D(0,48)).
                build().toRoadrunner(),
                CoordinateMode.HAL);

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



        //scan
        HALTrajectory setForPlace = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Point2D(-28, 22), 0)
                .build();

        HALTrajectory setMarker = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Point2D(28, -10), 0)
                .build();

        HALTrajectory ducks = robot.mDrive.trajectoryBuilder(new Pose2d())
                .lineTo(new Point2D(104, 5))
                .build();

        HALTrajectory parkPlace = robot.mDrive.trajectoryBuilder(new Pose2d())
                .lineTo(new Point2D(0, 35))
                .build();

        //robot.mDrive.followTrajectory(scootForward);
        //waitTime(500);
        //robot.mDrive.turnPID(-Math.PI/2); //or -3 pi
        // /2

        robot.mDrive.followTrajectory(setForPlace);
        waitTime(200);
        // robot.mDrive.turnPID(Math.PI);

        robot.arm2.dropArm(3);
        waitTime(200);

        robot.mDrive.followTrajectory(setMarker);
        waitTime(200);
        robot.arm2.setArm(6);
        waitTime(1000);
        robot.arm2.grabber(true);
        waitTime(1000);
        robot.arm2.setArm(1);


        robot.mDrive.followTrajectory(ducks);
        waitTime(200);

        robot.mDrive.turnTime(-0.25, 700);
        robot.spinner.spinSpinMotorTime(0.35, 4000);

        robot.mDrive.turnTime(0.5, 300);
        robot.mDrive.followTrajectory(parkPlace);

    }
}
