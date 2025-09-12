package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class motorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motor1 = hardwareMap.get(DcMotor.class, "motor1");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up) {
                motor1.setPower(1);
            }
            if (gamepad1.dpad_down) {
                motor1.setPower(-1);
            }
            if (gamepad1.start) {
                motor1.setPower(0);
            }

            telemetry.addData("Motor 1 Power: ", motor1.getPower());
            telemetry.update();
        }
    }
}
