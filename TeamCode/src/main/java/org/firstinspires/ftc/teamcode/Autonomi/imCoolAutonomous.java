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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Baguette;

@Autonomous(name = "im cool", group = "cool group")
public class imCoolAutonomous extends BaseAutonomous {
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
        robot.mDrive.moveSimple(new Vector2D(.5, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, 90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);

        robot.spinner.spinSpinMotorTime();

        robot.mDrive.moveSimple(new Vector2D(-2, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, -90, HALAngleUnit.DEGREES);

       // robot.arm.dropArm();

        robot.mDrive.turnSimple(0.5, -90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, 90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, -90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);




        //red side

        robot.mDrive.moveSimple(new Vector2D(.5, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, -90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);

        robot.spinner.spinSpinMotorTime();

        robot.mDrive.moveSimple(new Vector2D(-2, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, 90, HALAngleUnit.DEGREES);

        //robot.arm.dropArm();

        robot.mDrive.turnSimple(0.5, 90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, -90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);
        robot.mDrive.turnSimple(0.5, 90, HALAngleUnit.DEGREES);
        robot.mDrive.moveSimple(new Vector2D(1, 0, HALAngleUnit.DEGREES), HALDistanceUnit.TILES, 0.4);



    }
}
