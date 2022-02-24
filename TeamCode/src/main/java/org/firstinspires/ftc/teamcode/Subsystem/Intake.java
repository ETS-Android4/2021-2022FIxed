package org.firstinspires.ftc.teamcode.Subsystem;

import com.SCHSRobotics.HAL9001.system.config.ConfigParam;
import com.SCHSRobotics.HAL9001.system.config.TeleopConfig;
import com.SCHSRobotics.HAL9001.system.robot.SubSystem;
import com.SCHSRobotics.HAL9001.util.control.Button;
import com.SCHSRobotics.HAL9001.util.control.CustomizableGamepad;
import com.SCHSRobotics.HAL9001.util.math.geometry.Vector2D;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Baguette;

import Util.Converter;


public class Intake extends SubSystem {
    public CustomizableGamepad gamepad;
    private Baguette robot;


    public static DcMotor slidesMotor;
    public static DcMotor intakeMotor;
    public static CRServo dropperServo;
    public static final String SLIDES_MOTOR_UP_BUTTON = "SLIDES_MOTOR_UP_BUTTON";
    public static final String SLIDES_MOTOR_DOWN_BUTTON = "SLIDES_MOTOR_DOWN_BUTTON";
    public static final String INTAKE_MOTOR_IN_BUTTON = "INTAKE_MOTOR_IN_BUTTON";
    public static final String INTAKE_MOTOR_OUT_BUTTON = "INTAKE_MOTOR_OUT_BUTTON";
    public static final String DROPPER_SERVO_BUTTON = "DROPPER_SERVO_BUTTON";
    public static final String SPEED_BUTTON = "SPEED_BUTTON";
    public static boolean isSettingSlide = false;

    boolean flag = true;



    public Intake(Baguette _robot, String _SLIDES_MOTOR_CONFIG, String _INTAKE_MOTOR_CONFIG, String _DROPPER_SERVO_CONFIG){
        super(_robot);

        slidesMotor = _robot.hardwareMap.dcMotor.get(_SLIDES_MOTOR_CONFIG);
        intakeMotor = _robot.hardwareMap.dcMotor.get(_INTAKE_MOTOR_CONFIG);
        dropperServo = _robot.hardwareMap.crservo.get(_DROPPER_SERVO_CONFIG);

        robot = _robot;

        gamepad = new CustomizableGamepad(_robot);



        usesConfig = true;
    }

    public void dropMarker(String color) {
        int temp = 3;
        switch (temp/*color*/) {
            case 1:
                robot.mDrive.moveSimple(new Vector2D(0, Converter.inchToEncoder(3)), -0.4);
                waitTime(1000);
                robot.intake.setSlides(1);
                break;
            case 2:
                robot.mDrive.moveSimple(new Vector2D(0, Converter.inchToEncoder(3)), -0.4);
                waitTime(1000);
                robot.intake.setSlides(2);
                break;
            case 3:
                robot.mDrive.moveSimple(new Vector2D(0, -Converter.inchToEncoder(5)), 0.4);
                waitTime(1000);
                robot.intake.setSlides(3);
                break;
        }
    }
    public void setPower() {
        slidesMotor.setPower(0.75);
    }
    public void drop() {
        dropperServo.setPower(-.2);
        waitTime(750);
        dropperServo.setPower(-1);
    }

    public void slidesPowerTime (int direction, int timeMillis){
        slidesMotor.setPower(direction * 0.6);
        waitTime(timeMillis);
    }

    public void setSlides(int level) {
        double pow = -0.6;
        if (!isSettingSlide) {
            isSettingSlide = true;
            switch (level) {
                case 1:
                    slidesMotor.setPower(pow);
                    waitTime(500);
                    slidesMotor.setPower(0);
                    drop();
                    waitTime(400);
                    slidesMotor.setPower(-pow);
                    waitTime(400);
                    slidesMotor.setPower(0);
                    isSettingSlide = false;
                case 2:
                    slidesMotor.setPower(pow);
                    waitTime(750);
                    slidesMotor.setPower(0);
                    drop();
                    waitTime(400);
                    slidesMotor.setPower(-pow);
                    waitTime(650);
                    slidesMotor.setPower(0);
                    isSettingSlide = false;
                case 3:
                    robot.telemetry.addData("test", "test");
                    robot.telemetry.update();
                    slidesMotor.setPower(pow);
                    waitTime(2600);
                    slidesMotor.setPower(0);
                    drop();
                    waitTime(400);
                    slidesMotor.setPower(-pow);
                    waitTime(2000);
                    slidesMotor.setPower(0);
                    isSettingSlide = false;
            }
        }
    }

    @Override
    public void init() {
        dropperServo.setPower(-1);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        if (usesConfig && !robot.isAutonomous()) {
            gamepad = robot.pullControls(this);
        }
    }

    @Override
    public void handle() {
        if (gamepad.getInput(INTAKE_MOTOR_IN_BUTTON)){
            intakeMotor.setPower(0.75);
        }
        else if (gamepad.getInput(INTAKE_MOTOR_OUT_BUTTON)){
            intakeMotor.setPower(-0.75);
        }
        else {
            intakeMotor.setPower(0);
        }

        if (gamepad.getInput(SLIDES_MOTOR_UP_BUTTON)){
            slidesMotor.setPower(-.6);
        }
        else if (gamepad.getInput(SLIDES_MOTOR_DOWN_BUTTON)){
            slidesMotor.setPower(.6);
        }
        else {
            slidesMotor.setPower(0);
        }

        if (gamepad.getInput(DROPPER_SERVO_BUTTON)) {
            drop();
        }

        if (gamepad.getInput(SPEED_BUTTON)) {

            if (flag) {
                robot.mDrive.setVelocityMultiplier(0.2);
                robot.telemetry.addData("speed", "on");
                robot.telemetry.update();
                robot.mDrive.setTurnSpeedMultiplier(0.2);
                flag = false;
            }
            else {
                robot.mDrive.setVelocityMultiplier(1);
                robot.telemetry.addData("speed", "off");
                robot.telemetry.update();
                robot.mDrive.setTurnSpeedMultiplier(1);
                flag = true;
            }
        }
        robot.telemetry.addData(robot.mDrive.getPoseEstimate().toString(), "imu");
        robot.telemetry.update();
    }

    @Override
    public void stop() {

    }

    @TeleopConfig
    public static ConfigParam[] teleopConfig() {
        return new ConfigParam[] {
                new ConfigParam(SLIDES_MOTOR_UP_BUTTON, Button.BooleanInputs.dpad_up, 2),
                new ConfigParam(SLIDES_MOTOR_DOWN_BUTTON, Button.BooleanInputs.dpad_down, 2),
                new ConfigParam(INTAKE_MOTOR_IN_BUTTON, Button.BooleanInputs.bool_right_trigger, 2),
                new ConfigParam(INTAKE_MOTOR_OUT_BUTTON, Button.BooleanInputs.bool_left_trigger, 2),
                new ConfigParam(DROPPER_SERVO_BUTTON , Button.BooleanInputs.x, 2),
                new ConfigParam(SPEED_BUTTON , Button.BooleanInputs.y, 1)
        };
    }

    public double getIntakePower() {
        return intakeMotor.getPower();
    }

    public double getSlidesPower() {
        return slidesMotor.getPower();
    }
}
