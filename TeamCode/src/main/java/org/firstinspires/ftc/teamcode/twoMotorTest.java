package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class twoMotorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motor1 = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "motor2");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up) {
                motor1.setPower(1);
                motor2.setPower(1);
            }
            if (gamepad1.dpad_down) {
                motor1.setPower(-1);
                motor2.setPower(-1);
            }
            if (gamepad1.dpad_right) {
                motor1.setPower(1);
                motor2.setPower(-1);
            }
            if (gamepad1.dpad_left) {
                motor1.setPower(-1);
                motor2.setPower(1);
            }
            if (gamepad1.start) {
                motor1.setPower(0);
                motor2.setPower(0);
            }

            telemetry.addData("Motor 1 Power: ", motor1.getPower());
            telemetry.addData("Motor 2 Power: ", motor2.getPower());
            telemetry.update();
        }
    }
}
