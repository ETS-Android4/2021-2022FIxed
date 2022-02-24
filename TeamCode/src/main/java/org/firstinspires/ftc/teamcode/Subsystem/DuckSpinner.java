package org.firstinspires.ftc.teamcode.Subsystem;

import com.SCHSRobotics.HAL9001.system.config.ConfigData;
import com.SCHSRobotics.HAL9001.system.config.ConfigParam;
import com.SCHSRobotics.HAL9001.system.config.TeleopConfig;
import com.SCHSRobotics.HAL9001.system.robot.SubSystem;
import com.SCHSRobotics.HAL9001.util.control.Button;
import com.SCHSRobotics.HAL9001.util.control.CustomizableGamepad;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Baguette;
import org.jetbrains.annotations.NotNull;

public class DuckSpinner extends SubSystem {
    public CustomizableGamepad gamepad;
    //public Baguette robot;

    public static DcMotor spinMotor;
    public static boolean isSpinAMotorButtonHeld;
    public static boolean isSpinBMotorButtonHeld;

    public static final String SPINA_MOTOR_BUTTON = "SPIN_A_MOTOR_BUTTON_Yeehaw";
    public static final String SPINB_MOTOR_BUTTON = "SPIN_B_MOTOR_BUTTON_Yeehaw";

    public static final String SPIN_SPEEED = "SPIN_SPEED";
    public ConfigData data;
    //private int framesToSkip = 3;
    public DuckSpinner(@NotNull Baguette _robot, String _SPIN_MOTOR_CONFIG) {
        super(_robot);

        robot = _robot;
        spinMotor = _robot.hardwareMap.dcMotor.get(_SPIN_MOTOR_CONFIG);

        gamepad = new CustomizableGamepad(_robot);



        usesConfig = true;
    }

    public void spinSpinMotorTime(double _power, int time) {
        spinMotor.setPower(_power);
        waitTime(time);
        spinMotor.setPower(0);
    }

    public void spinSpinMotorTime() {
        spinMotor.setPower(0.325);
        waitTime(2500);
        spinMotor.setPower(0);
    }

    public static void spinSpinMotorToggle(boolean on) {
        if (on) {
            spinMotor.setPower(0.325);
        }
        else {
            spinMotor.setPower(0);
        }
    }





    @Override
    public void init() {

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        if (usesConfig && !robot.isAutonomous()) {
            data = robot.pullNonGamepad(this);
            gamepad = robot.pullControls(this);


            //isSpinMotorButtonHeld = data.getData(SPIN_MOTOR_BUTTON, Boo\ean.class);
        }
    }

    @Override
    public void handle() {

        isSpinAMotorButtonHeld = gamepad.getInput(SPINA_MOTOR_BUTTON);
        isSpinBMotorButtonHeld = gamepad.getInput(SPINB_MOTOR_BUTTON);

        //isSpinMotorButtonHeld = gamepad1.a;
        spinMotor.setPower(0);

        double power = 0.0;

        if (isSpinAMotorButtonHeld) power += data.getData(SPIN_SPEEED, Double.class);
        if (isSpinBMotorButtonHeld) power -= data.getData(SPIN_SPEEED, Double.class);

        spinMotor.setPower(power);
    }

    @Override
    public void stop() {

    }

    @TeleopConfig
    public static ConfigParam[] teleopConfig() {
        return new ConfigParam[] {
                new ConfigParam(SPINA_MOTOR_BUTTON, Button.BooleanInputs.a, 2),
                new ConfigParam(SPINB_MOTOR_BUTTON, Button.BooleanInputs.x, 2),
                new ConfigParam(SPIN_SPEEED, ConfigParam.numberMap(0, 1, 0.025), 0.25),
        };
    }

    public double getSpinnerPower() {
        return spinMotor.getPower();
    }

}