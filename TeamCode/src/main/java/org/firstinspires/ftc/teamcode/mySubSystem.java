package org.firstinspires.ftc.teamcode;

import com.SCHSRobotics.HAL9001.*;
import com.SCHSRobotics.HAL9001.system.robot.Robot;
import com.SCHSRobotics.HAL9001.system.robot.SubSystem;
import com.SCHSRobotics.HAL9001.util.control.CustomizableGamepad;

public class mySubSystem extends SubSystem {
    private CustomizableGamepad gamepad;
    public mySubSystem (Robot robot){
        super(robot);
        mySubSystem mySubSystem2 = new mySubSystem(robot);
    }
    @Override
    public void init() {

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void handle() {

    }

    @Override
    public void stop() {

    }
}
