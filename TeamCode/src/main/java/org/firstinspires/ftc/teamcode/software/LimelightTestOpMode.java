package org.firstinspires.ftc.teamcode.software;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;


@TeleOp(name = "Limelight 3A Test", group = "Linear OpMode")
public class LimelightTestOpMode extends LinearOpMode {
    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize Limelight from the hardware map
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        // setMsTransmissionInterval() changes how often data from the Telemetry
        // object goes from the Robot
        // Controller to the Driver Station. Setting it to 11 milliseconds forces
        // the system to update the Driver Station screen almost 90 times per
        // second instead of using the default 250 ms (4 times per second) delay
        // Speeds up how fast numbers, tracking info, and debugging text refresh
        // on your Driver Station device screen. Used in limelight because Vision
        // data from a Limelight 3A changes rapidly. An 11 ms interval lets you
        // see fast-moving target coordinates, latency stats, and robot poses instantly.
        telemetry.setMsTransmissionInterval(11);
        telemetry.addData("Status", "Initialized. Starting pipeline...");
        telemetry.update();

        // Use Pipeline 0 (where you saved your AprilTag pipeline)
        limelight.pipelineSwitch(0);

        // Start streaming camera data. Starts polling for data.  If you neglect to call
        // start(), getLatestResult() will return null.
        limelight.start();

        // Wait for the driver to press PLAY
        waitForStart();

        while (opModeIsActive()) {
            // Fetch the latest tracking results from the camera
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                telemetry.addData("Target Detected", "YES");

                // Horizontal offset from crosshair to target (-31.25 to 31.25 degrees)
                telemetry.addData("Tx (X-Offset)", "%.2f degrees", result.getTx());

                // Vertical offset from crosshair to target (-24.75 to 24.75 degrees)
                telemetry.addData("Ty (Y-Offset)", "%.2f degrees", result.getTy());

                // Target area as a percentage of the total image (0% - 100%)
                telemetry.addData("Ta (Area)", "%.2f%%", result.getTa());

                // Pipeline latency (how long the image took to process in ms)
                telemetry.addData("Targeting Latency", "%.2f ms", result.getTargetingLatency());
            } else {
                telemetry.addData("Target Detected", "NO");
            }

            telemetry.update();
        }

        // Clean up and turn off the camera pipeline when the OpMode stops
        limelight.stop();
    }
}