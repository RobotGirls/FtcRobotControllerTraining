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
        }
    }
}