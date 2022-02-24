package org.firstinspires.ftc.teamcode.Autonomi;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

class nonHalAuto extends LinearOpMode {
    DcMotor flm;
    DcMotor frm;
    DcMotor blm;
    DcMotor brm;

    public void power(int power){
        flm.setPower(power);
        frm.setPower(power);
        blm.setPower(power);
        brm.setPower(power);
    }

    public void strafe(int power){ //+1 goes right, -1 goes left
                flm.setPower(power);
                frm.setPower(-power);
                blm.setPower(-power);
                brm.setPower(power);
    }

    public void stopP(){
        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
    }

    public void move(int power, String direction, int distance) { //distance is in inches
        switch(direction) {
            case "for":
                power(power);
                stopP();
                break;
            case "right":
                strafe(power);
                stopP();
                break;
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        flm = hardwareMap.dcMotor.get("f_l_m");
        frm = hardwareMap.dcMotor.get("f_r_m");
        blm = hardwareMap.dcMotor.get("b_l_m");
        brm = hardwareMap.dcMotor.get("b_r_m");

        Servo b_s = hardwareMap.servo.get("big_s");
        Servo c_s = hardwareMap.servo.get("clamp_s");

        waitForStart();
        while (opModeIsActive()) {

        }

    }
}
