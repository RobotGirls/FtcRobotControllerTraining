package org.firstinspires.ftc.teamcode.software.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor intakeMotor;

    // constructor
    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void setPower(double power) {
        intakeMotor.setPower(power);
    }

    public void stop() {
        intakeMotor.setPower(0.0);
    }
}