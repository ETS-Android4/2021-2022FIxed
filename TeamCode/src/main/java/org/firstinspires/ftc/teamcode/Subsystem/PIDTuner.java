package org.firstinspires.ftc.teamcode.Subsystem;

import com.SCHSRobotics.HAL9001.system.config.AutonomousConfig;
import com.SCHSRobotics.HAL9001.system.config.ConfigData;
import com.SCHSRobotics.HAL9001.system.config.ConfigParam;
import com.SCHSRobotics.HAL9001.system.robot.SubSystem;
import com.acmerobotics.roadrunner.control.PIDCoefficients;

import org.firstinspires.ftc.teamcode.Baguette;
import org.jetbrains.annotations.NotNull;

public class PIDTuner extends SubSystem {
    public Baguette robot;

    public ConfigData data;
    public PIDTuner(@NotNull Baguette _robot) {
        super(_robot);

        robot = _robot;

        usesConfig = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        if (usesConfig && robot.isAutonomous()) {

            data = robot.pullNonGamepad(this);
            robot.mDrive.setTurnPID(new PIDCoefficients(data.getData("P", Double.class), data.getData("I", Double.class), data.getData("D", Double.class)));
            //robot.mDrive.setTurnPID(new PIDCoefficients(0.01, 0, 0.0025));
        }

    }

    @Override
    public void handle() {

    }

    @Override
    public void stop() {

    }

    @AutonomousConfig
    public static ConfigParam[] autonomousConfig() {
        return new ConfigParam[] {
                new ConfigParam("P", ConfigParam.numberMap(0, 2, 0.003), 0.15),
                new ConfigParam("I", ConfigParam.numberMap(0, 2, 0.003), 0.15),
                new ConfigParam("D", ConfigParam.numberMap(0, 2, 0.003), 0.15)
        };
    }
}