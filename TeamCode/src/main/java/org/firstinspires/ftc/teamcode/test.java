package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "test")
public class  test  extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor flm = hardwareMap.dcMotor.get("f_l_m");
        DcMotor frm = hardwareMap.dcMotor.get("f_r_m");
        DcMotor blm = hardwareMap.dcMotor.get("b_l_m");
        DcMotor brm = hardwareMap.dcMotor.get("b_r_m");

        Servo b_s = hardwareMap.servo.get("big_s");
        Servo c_s = hardwareMap.servo.get("clamp_s");


        int sVal = 90;
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a){
                if (gamepad1.left_stick_y < 0){
                    b_s.setPosition(sVal);
                }
            }

        }
    }
}
