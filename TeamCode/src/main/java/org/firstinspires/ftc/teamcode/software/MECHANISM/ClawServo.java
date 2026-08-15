package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawServo {
    private Servo clawServo = null;
    
    private final double OPEN_POSITION = 0.8;
    private final double CLOSED_POSITION = 0.2;

    public void init(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "claw_servo");
        open(); // Set default starting position
    }

    public void open() {
        clawServo.setPosition(OPEN_POSITION);
    }

    public void close() {
        clawServo.setPosition(CLOSED_POSITION);
    }

    public double getPosition() {
        return clawServo.getPosition();
    }
}

