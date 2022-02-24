package org.firstinspires.ftc.teamcode.Subsystem;

import com.SCHSRobotics.HAL9001.system.config.ConfigParam;
import com.SCHSRobotics.HAL9001.system.config.TeleopConfig;
import com.SCHSRobotics.HAL9001.system.robot.SubSystem;
import com.SCHSRobotics.HAL9001.util.control.Button;
import com.SCHSRobotics.HAL9001.util.control.CustomizableGamepad;
import com.SCHSRobotics.HAL9001.util.control.Toggle;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Baguette;
import org.jetbrains.annotations.NotNull;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

public class Arm2 extends SubSystem {
    public CustomizableGamepad gamepad;
    //public Baguette robot;

    public static Servo clampServo;
    public static Servo elbowJoint;

    public static final String CLAMP_SERVO_BUTTON = "CLAMP_SERVO_BUTTON";

    public static final String LOWER_ELBOW_SERVO = "LOWER_ELBOW_SERVO";
    public static final String RAISE_ELBOW_SERVO = "RAISE_ELBOW_SERVO";

    public static int elbowState = 0;
    private boolean heldElbowLast = false;

    private Toggle clampToggle = new Toggle(Toggle.ToggleTypes.flipToggle, true);

    //public ConfigData data;
    //private int framesToSkip = 3;

    public Arm2(@NotNull Baguette _robot, String _ClAMP_CONFIG, String _ELBOW_CONFIG) {
        super(_robot);

        robot = _robot;

        clampServo = robot.hardwareMap.servo.get(_ClAMP_CONFIG);
        elbowJoint = robot.hardwareMap.servo.get(_ELBOW_CONFIG);

        gamepad = new CustomizableGamepad(_robot);

        usesConfig = true;
    }

    @Override
    public void init() {
        elbowJoint.setPosition(0.25);
        clampServo.setPosition(1);

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        clampServo.setPosition(1);
        if (usesConfig && !robot.isAutonomous()) {
            //data = robot.pullNonGamepad(this);
            gamepad = robot.pullControls(this);
            //isSpinMotorButtonHeld = data.getData(SPIN_MOTOR_BUTTON, Boolean.class);
        }
    }

    @Override
    public void handle() {
        robot.telemetry.addData(" " + elbowState, "elbow");

        if ((boolean)gamepad.getInput(LOWER_ELBOW_SERVO) || (boolean)gamepad.getInput(RAISE_ELBOW_SERVO)) {
            if (!heldElbowLast) {
                heldElbowLast = true;

                if ((boolean) gamepad.getInput(LOWER_ELBOW_SERVO) && elbowState != 0) {
                    elbowState--;
                    robot.telemetry.addData("lower", "elbow");
                }
                if ((boolean) gamepad.getInput(RAISE_ELBOW_SERVO) && elbowState != 9) {
                    elbowState++;
                    robot.telemetry.addData("raise", "elbow");
                }
            }
        }
        else {
            heldElbowLast = false;
        }


        if (elbowState == 0) {
            elbowJoint.setPosition(0.2);
        }
        else if (elbowState >= 4) {
            elbowJoint.setPosition((elbowState * (.1/7) + 0.63));
        }
        else {
            elbowJoint.setPosition((elbowState * (0.15/7)) + 0.425);
        }

        robot.telemetry.addData(" "  + elbowJoint.getPosition(), "elbow");
        //robot.telemetry.update();
        clampToggle.updateToggle((boolean)gamepad.getInput(CLAMP_SERVO_BUTTON));
        if (clampToggle.getCurrentState()) {
            clampServo.setPosition(1);
        }
        else {
            clampServo.setPosition(0.5);
        }
    }

    @Override
    public void stop() {

    }

    @TeleopConfig
    public static ConfigParam[] teleopConfig() {
        return new ConfigParam[] {
                new ConfigParam(LOWER_ELBOW_SERVO, Button.BooleanInputs.dpad_left, 2),
                new ConfigParam(RAISE_ELBOW_SERVO, Button.BooleanInputs.dpad_right, 2),
                new ConfigParam(CLAMP_SERVO_BUTTON, Button.BooleanInputs.b, 2)
        };
    }

    public void grabber(boolean close) {
        if (close) {
            clampServo.setPosition(1);
        }
        else if (!close) {
            clampServo.setPosition(0.5);
        }
    }

    public void setArm(int state) {
        if (state == 0) {
            elbowJoint.setPosition(0.2);
        }
        else if (state >= 4) {
            elbowJoint.setPosition((state * (.1/7) + 0.63));
        }
        else {
            elbowJoint.setPosition((state * (0.15/7)) + 0.425);
        }
    }

    public void dropArm(int level) {
        switch (level) {
            default:
                telemetry.addData("arm case:", "default");
                break;
            case 0:
                elbowJoint.setPosition(0.2); waitTime(1000);
                break;
            case 3:
                elbowJoint.setPosition(0.55); waitTime(1000);
                break;
            case 2:
                elbowJoint.setPosition(0.62); waitTime(1000);
                break;
            case 1:
                elbowJoint.setPosition(0.7); waitTime(1000);
                break;
        }
        clampServo.setPosition(0.5); waitTime(1000); elbowJoint.setPosition(0.3);
    }

    public double getElbowPosition() {
        return elbowJoint.getPosition();
    }

    public double getClampPosition() {
        return clampServo.getPosition();
    }

    public static void setElbowPosition(Double position) {
        elbowJoint.setPosition(position);
    }

    public static void setClampPosition(Double position) {
        clampServo.setPosition(position);
    }
}