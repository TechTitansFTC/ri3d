package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class servoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Servo servo1 = hardwareMap.get(Servo.class, "servo1");

        double position = 0;

        waitForStart();

        while (opModeIsActive()) {


            servo1.setPosition(position);

            if (gamepad1.dpad_up) {
                position += 0.05;
            }
            if (gamepad1.dpad_down) {
                position -= 0.05;
            }
            if (gamepad1.dpad_right) {
                position += 0.01;
            }
            if (gamepad1.dpad_left) {
                position -= 0.01;
            }
            if (gamepad1.start) {
                position = 1;
            }
            servo1.setPosition(position);

            telemetry.addData("Servo Position: ", servo1.getPosition());
            telemetry.update();
        }
    }
}
