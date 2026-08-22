package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.software.MECHANISM.ClawServo;
import org.firstinspires.ftc.teamcode.software.MECHANISM.RobotHardware;

@TeleOp(name = "Sample TeleOp", group = "Linear OpMode")
public class SampleTeleOp extends LinearOpMode {
    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Drive controls (arcade style: negative Y for forward stick)
            double forward = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            robot.drive(forward, turn);

            // Servo button controls
            if (gamepad1.a) {
                robot.claw.close();
            } else if (gamepad1.b) {
                robot.claw.open();
            }

            telemetry.addData("Claw Position", robot.claw.getPosition());
            telemetry.update();
        }
    }
}

