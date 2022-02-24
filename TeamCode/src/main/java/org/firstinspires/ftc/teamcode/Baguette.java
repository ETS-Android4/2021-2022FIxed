package org.firstinspires.ftc.teamcode;

import com.SCHSRobotics.HAL9001.system.robot.Robot;
import com.SCHSRobotics.HAL9001.system.robot.localizer.HolonomicDriveEncoderIMULocalizer;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.AxesSigns;
import com.SCHSRobotics.HAL9001.system.robot.roadrunner_util.RoadrunnerConfig;
import com.SCHSRobotics.HAL9001.system.robot.subsystems.drivetrain.MecanumDrive;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.Subsystem.Arm2;
import org.firstinspires.ftc.teamcode.Subsystem.ArmController;
import org.firstinspires.ftc.teamcode.Subsystem.DuckSpinner;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.PIDTuner;

public class Baguette extends Robot {

    //@InternalCamera(resWidth = 320, resHeight = 240, usesViewport = true)
    //@ExternalCamera(resWidth = 320, resHeight = 240, configName = "Cam", usesViewport = false, uniqueId = "cam")
    //public OpenCvCamera camera = getCamera("cam");
   // static {OpenCVLoader.initDebug();}


    public MecanumDrive mDrive;
    public DuckSpinner spinner;
    public ArmController arm;
    public Arm2 arm2;
    public Intake intake;
    public PIDTuner pidTuner;
    //public Vision vision;
    public static boolean isRunningAuto = true;
    //@ExternalCamera(resHeight = 720, resWidth = 1080, configName = "Cam", uniqueId = "cam", usesViewport = true)
    //public OpenCvCamera camera = getCamera("cam");

    public Baguette(OpMode opMode) {
        super(opMode);

        //opMode.telemetry.clear();
        //this.telemetry.clear();

        mDrive = new MecanumDrive(
                this,
                //TODO: change these later
                //15.25 from edge to edge
                new RoadrunnerConfig(3, 15.0/29.0, 15.25,  384.5, 435),
                "f_l_m",
                "f_r_m",
                "b_l_m",
                "b_r_m",
                true
        );

        mDrive.setLocalizer(new HolonomicDriveEncoderIMULocalizer(
                this,
                mDrive,
                "imu",
                "f_l_m",
                "f_r_m",
                "b_l_m",
                "b_r_m"
        ).remapIMUAxes(AxesOrder.YZX, AxesSigns.NNN));
        /*mDrive.setLocalizer(new HolonomicDriveEncoderLocalizer(
                mDrive,
                "f_l_m",
                "f_r_m",
                "b_l_m",
                "b_r_m"

        ));*/

        //l.setPoseEstimate(new Pose2d(l.getPoseEstimate().getX(), l.getPoseEstimate().getY(), l.getPoseEstimate().getHeading() + 180));

        mDrive.reverseMotor("f_l_m");
        mDrive.reverseMotor("f_r_m");
        mDrive.reverseMotor("b_l_m");
        mDrive.reverseMotor("b_r_m");

        //mDrive.setTurnPID(new PIDCoefficients(0.6, 0.015, 0.0225));
        //mDrive.set

        //mDrive.setHeadingPIDTolerance(Math.PI/90);

        //mDrive.setHeadingPID(new PIDCoefficients(1, 0, 0));
        mDrive.setTranslationalPID(new PIDCoefficients(2, 0.2, 0.5));
        //arm = new ArmController(this, "big_s", "clamp_s");
        pidTuner = new PIDTuner(this);
        arm2 = new Arm2(this, "clamp_s", "big_s");
        spinner = new DuckSpinner(this, "spin_motor");
        intake = new Intake(this, "slide", "intake", "tray_servo");
        //'vision = new Vision(this);
    }
}
