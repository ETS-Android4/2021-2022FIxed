package org.firstinspires.ftc.teamcode.Autonomi;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.HALTrajectory;
import com.SCHSRobotics.HAL9001.util.math.geometry.Point2D;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Baguette;

@Autonomous(name = "BoxPIDCalibAuto", group = "comp")
public class BoxPIDCalibAuto extends BaseAutonomous {
    public @MainRobot
    Baguette robot;

    @Override
    public void main() {
        HALTrajectory forwardRoute = robot.mDrive.trajectoryBuilder(new Pose2d())
                .splineToConstantHeading(new Point2D(0, 24), 0)
                .build();

        HALTrajectory left = robot.mDrive.trajectoryBuilder(forwardRoute.end())
                .splineToConstantHeading(new Point2D(-48, 24), 0)
                .build();

        HALTrajectory back = robot.mDrive.trajectoryBuilder(left.end())
                .splineToConstantHeading(new Point2D(-48, 0), 0)
                .build();

        HALTrajectory right = robot.mDrive.trajectoryBuilder(back.end())
                .splineToConstantHeading(new Point2D(0, 0), 0)
                .build();

        robot.mDrive.getLocalizer().setPoseEstimate(new Pose2d(0,0, 0));
        robot.telemetry.addData(robot.mDrive.getPoseEstimate().toString(), "imu");
        robot.telemetry.update();

        robot.mDrive.followTrajectory(forwardRoute);
        robot.telemetry.addData(robot.mDrive.getPoseEstimate().toString(), "imu");
        robot.telemetry.update();
        waitTime(1500);
        robot.mDrive.followTrajectory(left);
        robot.telemetry.addData(robot.mDrive.getPoseEstimate().toString(), "imu");
        robot.telemetry.update();
        waitTime(1500);
        robot.mDrive.followTrajectory(back);
        robot.telemetry.addData(robot.mDrive.getPoseEstimate().toString(), "imu");
        robot.telemetry.update();
        waitTime(1500);
        robot.mDrive.followTrajectory(right);
        robot.telemetry.addData(robot.mDrive.getPoseEstimate().toString(), "imu");
        robot.telemetry.update();
        waitTime(1500);
    }
}
