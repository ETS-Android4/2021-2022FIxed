package org.firstinspires.ftc.teamcode;

import com.SCHSRobotics.HAL9001.system.robot.BaseTeleop;
import com.SCHSRobotics.HAL9001.system.robot.MainRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "ImCoolTeleop", group = "cool group")
public class ImCoolTeleop extends BaseTeleop {

    public @MainRobot
    Baguette robot;

    @Override
    protected void onInit() {
        Baguette.isRunningAuto = false;
        //robot.mDrive.reverseMotor("f_l_m");
        //robot.mDrive.reverseMotor("f_r_m");
        //robot.mDrive.reverseMotor("b_l_m");
        //robot.mDrive.reverseMotor("b_r_m");
        //robot.spinner.spinMotor = hardwareMap.dcMotor.get("spin_motor");
        //DuckSpinner.spinMotor = hardwareMap.dcMotor.get("spin_motor");
        //robot.arm.armServo = hardwareMap.servo.get("big_s");
        //robot.arm.clampServo = hardwareMap.servo.get("clamp_s");
        //robot.spinner.gamepad = robot.pullControls(robot.spinner);
        //robot.spinner.data = robot.pullNonGamepad(robot.spinner);
    }

    @Override
    protected void onInitLoop() {

    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onStop() {

    }
}