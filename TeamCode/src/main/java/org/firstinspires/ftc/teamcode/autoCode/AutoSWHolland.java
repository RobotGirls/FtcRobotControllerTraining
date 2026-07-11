package org.firstinspires.ftc.teamcode.autoCode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.MecanumDriveSWClass;
import org.firstinspires.ftc.teamcode.mechCode.IntakeRoadRunner;
import org.firstinspires.ftc.teamcode.opmodes.RedLeftPark;

@Autonomous(name = "AutoSWHolland.java")
public class AutoSWHolland extends LinearOpMode {
    private boolean first = true;
    private static final double FIRST_LIFT_DOWN_POS = 50.0;
    private static final double LAST_LIFT_DOWN_POS = 100.0;
    private double currLiftPos = 0.0;
    ElapsedTime liftTimer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
            // instantiating the robot at a specific pose
            Pose2d initialPose = new Pose2d(-38, -62, Math.toRadians(89));
            MecanumDriveSWClass drive = new MecanumDriveSWClass(hardwareMap, initialPose);

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
