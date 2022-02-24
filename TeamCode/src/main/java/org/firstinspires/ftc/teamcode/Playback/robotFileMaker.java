package org.firstinspires.ftc.teamcode.Playback;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@TeleOp(name = "robotFileMaker")
public class robotFileMaker extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        File master = new File(Environment.getExternalStorageDirectory().getPath() + "/System64" + "/master1.txt");
        try {
            telemetry.addData("master path:", master.getAbsolutePath());
            FileWriter masterWriter = new FileWriter(master.getAbsolutePath());
            masterWriter.write(0);

            File playback0 = new File(Environment.getExternalStorageDirectory().getPath() + "/System64/playback" + 0 + ".txt");
            telemetry.addData("playback0 path:", playback0.getAbsolutePath());
            telemetry.update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
