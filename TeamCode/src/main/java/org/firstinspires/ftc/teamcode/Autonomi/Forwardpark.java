package org.firstinspires.ftc.teamcode.Autonomi;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.HALTrajectory;
import com.SCHSRobotics.HAL9001.util.math.geometry.Point2D;
import com.SCHSRobotics.HAL9001.util.math.units.HALAngleUnit;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Baguette;

@Autonomous(name = "ForwardPark", group = "comp")
public class Forwardpark extends BaseAutonomous {
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
        //robot.mDrive.reverseMotor("f_l_m");
        //robot.mDrive.reverseMotor("f_r_m");
        //robot.mDrive.reverseMotor("b_l_m");
        //robot.mDrive.reverseMotor("b_r_m");
        //robot.mDrive.setAllMotorZeroPowerBehaviors(DcMotor.ZeroPowerBehavior.FLOAT);
        HALTrajectory first = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Point2D(25, 0), 0)
                .build();

        HALTrajectory park = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Point2D(0, 80), 0)
                .build();

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



        robot.mDrive.followTrajectory(first);
        waitTime(1000);
        //scan
        robot.mDrive.followTrajectory(park);
        waitTime(1000);


    }
}
