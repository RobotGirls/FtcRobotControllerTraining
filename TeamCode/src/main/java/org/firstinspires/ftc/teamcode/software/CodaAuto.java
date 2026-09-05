package org.firstinspires.ftc.teamcode.software;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="CodaAuto", group="Autonomous")
public class CodaAuto extends LinearOpMode {

    RobotHardware robot = new RobotHardware();
    GamepadHandler gamepadWrapper;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        gamepadWrapper = new GamepadHandler(gamepad1);

        // 1. Run the menu completely hidden away in a simple loop
        while (!isStarted() && !isStopRequested()) {
            gamepadWrapper.runConfigurationMenu(telemetry);
            telemetry.addLine("Press PLAY to lock settings.");
            telemetry.update();
        }

        // --- MATCH STARTS ---

        // 2. Read the results directly from the wrapper object
        boolean isRed = gamepadWrapper.isRedAlliance();
        boolean isClose = gamepadWrapper.isCloseSide();

        if (isRed && isClose) {
            // Run path logic...
            telemetry.addLine("Running Path: RED CLOSE");
            telemetry.update();

            // Step 1: Drive forward at half speed for 1.5 seconds
            robot.driveTime(0.5, 0.5, 0.5, 0.5, 1500, this);
            sleep(300); // Small pause to let the robot stop shaking

            // Step 2: Turn Right by running left side forward and right side backward for 0.8 seconds
            robot.driveTime(0.4, -0.4, 0.4, -0.4, 800, this);
            sleep(300);

            // Step 3: Drive backward slowly into parking zone for 1 second
            robot.driveTime(-0.3, -0.3, -0.3, -0.3, 1000, this);
        } else {
            // Default path for all other combinations: Just drive straight for 1.2 seconds to park
            telemetry.addLine("Running Default Path");
            telemetry.update();
            robot.driveTime(0.4, 0.4, 0.4, 0.4, 1200, this);
        }
        telemetry.addLine("Autonomous Path Complete.");
        telemetry.update();

    }
}