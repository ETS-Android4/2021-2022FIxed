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

import org.firstinspires.ftc.teamcode.Baguette;

@Autonomous(name = "Test", group = "test")
public class Testautonomosu extends BaseAutonomous {
    public @MainRobot
    Baguette robot;

    private void drivePower (double pow, int angleDegrees) {
        //hat uses the imu to see how far it goes, and add that to the parameters so its based off distance
        //robot.mDrive.movePower(new Vector2D(pow, angleDegrees, HALAngleUnit.DEGREES));
    }

    private void turnAngle (int angleDegrees, int toleranceDegrees) {
        robot.mDrive.turnPID(angleDegrees, HALAngleUnit.DEGREES, toleranceDegrees, HALAngleUnit.DEGREES );
    }

    @Override
    public void main() {
        //robot.mDrive.reverseMotor("f_l_m");
        //robot.mDrive.reverseMotor("f_r_m");
        //robot.mDrive.reverseMotor("b_l_m");
        //robot.mDrive.reverseMotor("b_r_m");
        //robot.mDrive.setAllMotorZeroPowerBehaviors(DcMotor.ZeroPowerBehavior.FLOAT);
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
        robot.mDrive.turnPower(0.4);
        waitTime(1600);
        robot.mDrive.turnPower(0);



        telemetry.addData("thingy", robot.mDrive.getLocalizer().getPoseEstimate());
        telemetry.update();
        waitTime(10000);

        //robot.intake.dropMarker("color");*/

        //robot.mDrive.turnPID(180, HALAngleUnit.DEGREES);
        telemetry.addData("thingy", robot.mDrive.getLocalizer().getPoseEstimate());
        telemetry.update();
        waitTime(10000);

    }
}
