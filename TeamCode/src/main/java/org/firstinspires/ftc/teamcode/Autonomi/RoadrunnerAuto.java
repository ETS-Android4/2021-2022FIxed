package org.firstinspires.ftc.teamcode.Autonomi;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.HALTrajectory;
import com.SCHSRobotics.HAL9001.util.math.geometry.Point2D;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Baguette;

@Autonomous(name = "Roadrunner", group = "comp")
public class RoadrunnerAuto extends BaseAutonomous {
    public @MainRobot
    Baguette robot;

    @Override
    public void main() {
        HALTrajectory forwardRoute = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineTo(new Point2D(24, 24), 0)
                .build();

        HALTrajectory returnRoute = robot.mDrive.trajectoryBuilder(forwardRoute.end())
                .splineTo(new Point2D(0, 0), 0)
                .build();

        //robot.mDrive.followTrajectory(forwardRoute);
        //waitTime(1000);
        //robot.mDrive.turnPID(PI);
        // waitTime(1000);
        //robot.mDrive.turnPID(PI);
        //waitTime(1000);
        //robot.mDrive.turnPID(PI);
        //waitTime(1000);
        //robot.mDrive.followTrajectory(returnRoute);

        robot.mDrive.followTrajectory(forwardRoute);
        robot.mDrive.followTrajectory(returnRoute);
        //robot.mDrive.followTrajectory(rightMarker);
    }
}
