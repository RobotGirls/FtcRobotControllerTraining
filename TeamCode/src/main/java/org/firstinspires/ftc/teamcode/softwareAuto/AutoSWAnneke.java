package org.firstinspires.ftc.teamcode.softwareAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.MechanumDriveSWClass;
import org.firstinspires.ftc.teamcode.opmodes.RedLeftPark;

import org.firstinspires.ftc.teamcode.mechCode.IntakeRoadRunner;

@Autonomous(name = "Basic Auto Anneke")
public class AutoSWAnneke extends LinearOpMode {

        private boolean first = true;
        private static final double FIRST_LIFT_DOWN_POS = 50.0;
        private static final double LAST_LIFT_DOWN_POS = 100.0;
        private double currLiftPos = 0.0;

        @Override
        public void runOpMode() throws InterruptedException {
            // instantiating the robot at a specific pose
            Pose2d initialPose = new Pose2d(-38, -62, Math.toRadians(89));
            MechanumDriveSWClass drive = new MechanumDriveSWClass(hardwareMap, initialPose);

            IntakeRoadRunner intake = new IntakeRoadRunner(hardwareMap, telemetry);

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

                            firstTraj,
                            intake.intakeArtifact()

                    )
            );
        }

        }

