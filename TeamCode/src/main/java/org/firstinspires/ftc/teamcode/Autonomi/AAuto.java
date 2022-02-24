package org.firstinspires.ftc.teamcode.Autonomi;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.CoordinateMode;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.HALTrajectory;
import com.SCHSRobotics.HAL9001.util.math.geometry.Point2D;
import com.SCHSRobotics.HAL9001.util.math.geometry.Vector2D;
import com.SCHSRobotics.HAL9001.util.math.units.HALAngleUnit;
import com.SCHSRobotics.HAL9001.util.math.units.HALDistanceUnit;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Baguette;

//@Autonomous(name = "MyHalAuto", group = "Example Programs")
public class AAuto extends BaseAutonomous {
    public @MainRobot
    Baguette robot;

    @Override
    public void main() {
        while(opModeIsActive()) {
            /*
            This is why we encouraged you to design programs with functions in mind
            rather than just using the built in automatic-run functions (or at least its one of the reasons).
            */

            //scan the box
            //place the ball
            //get the box
            //carousel
            //park

            // Some examples RoadRunner code
            HALTrajectory middleMarker = new HALTrajectory(robot.mDrive.trajectoryBuilder(new Pose2d(0,0), 0).
                    splineTo(new Point2D(6,0), Math.PI/4).
                    build().
                    toRoadrunner(),
                    CoordinateMode.HAL);

            HALTrajectory middleCase = new HALTrajectory(robot.mDrive.trajectoryBuilder(middleMarker.end(), 0).
                    splineTo(new Point2D(6,0), Math.PI/4).
                    build().
                    toRoadrunner(),
                    CoordinateMode.HAL);
            robot.mDrive.followTrajectory(middleMarker);
            // Detect the object, if its middle, execute this code
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            // Pick up
            robot.mDrive.turnPID(Math.PI/4);
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            // Place block on middle level

            // If its left, execute this code
            robot.mDrive.turnPID(22.5, HALAngleUnit.DEGREES);
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            // Pick up
            robot.mDrive.turnPID(22.5, HALAngleUnit.DEGREES);
            // Place on corresponding level

            // If its right, execute this code
            robot.mDrive.turnPID(-22.5, HALAngleUnit.DEGREES);
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            // Pick up
            robot.mDrive.turnPID(67.5, HALAngleUnit.DEGREES);
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            // Place on corrosponding level

            // Carousel
            robot.mDrive.turnPID(Math.PI);
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            robot.mDrive.turnPID(-22.5, HALAngleUnit.DEGREES);
            // Spin carousel

            robot.mDrive.turnPID(Math.PI);
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            // Grab frieght
            robot.mDrive.moveSimple(new Vector2D(4, 0), HALDistanceUnit.CENTIMETERS, 1);
            robot.mDrive.turnPID(Math.PI/2);


        }
    }
}