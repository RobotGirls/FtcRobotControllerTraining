package org.firstinspires.ftc.teamcode.autoCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.opmodes.RedLeftPark;
import org.firstinspires.ftc.teamcode.mechCode.ShooterRoadRunner;

@Autonomous(name = "Basic Auto April")
public class AutoSWApril extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        // instantiating the robot at a specific pose
        Pose2d initialPose = new Pose2d(-38, -62, Math.toRadians(89));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        ShooterRoadRunner shooter = new ShooterRoadRunner(hardwareMap, telemetry);
// fancy method called constructor ^^
        // actionBuilder builds from the drive steps passed to it
        TrajectoryActionBuilder toSub = drive.actionBuilder(initialPose)
                // red left park
                .lineToY(-10)
                .turn(Math.toRadians(-90))
                .lineToX(-26);


        // ON INIT:
        //      Actions.runBlocking(claw.closeClaw());

        Action firstTraj = toSub.build();

        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Robot position: ", drive.updatePoseEstimate());
            telemetry.update();
        }
        waitForStart();
        if (isStopRequested()) return;

        // IN RUNTIME
        // running the action sequence!
        Actions.runBlocking(
                new SequentialAction(
//                        liftPivot.liftPivotDown(),
                        firstTraj,
                        shooter.shootArtifact()
                )
        );
    }

}