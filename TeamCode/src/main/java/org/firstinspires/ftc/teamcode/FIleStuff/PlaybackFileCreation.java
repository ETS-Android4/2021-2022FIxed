package org.firstinspires.ftc.teamcode.FIleStuff;

import com.SCHSRobotics.HAL9001.system.robot.BaseAutonomous;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Baguette;

public class PlaybackFileCreation extends BaseAutonomous {
    public @MainRobot
    Baguette robot;

    @Override
    public void main() {
        robot.mDrive.getLocalizer();
    }
}
