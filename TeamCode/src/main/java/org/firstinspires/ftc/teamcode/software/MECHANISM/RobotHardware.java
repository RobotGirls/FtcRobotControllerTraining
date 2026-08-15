package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public ClawServo claw = null;

    public void init(HardwareMap hardwareMap) {
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setPower(0);
        rightDrive.setPower(0);
        
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Initialize separate servo subsystem
        claw = new ClawServo();
        claw.init(hardwareMap);
    }

    public void drive(double forward, double turn) {
        double leftPower = forward + turn;
        double rightPower = forward - turn;
        
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
    }
}

