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

        double power1 = 0;
        double power2 = 0;

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up) {
                power1 = 1;
                power2 = 1;
            }
            if (gamepad1.dpad_down) {
                power1 = -1;
                power2 = -1;
            }
            if (gamepad1.dpad_right) {
                power1 = 1;
                power2 = -1;
            }
            if (gamepad1.dpad_left) {
                power1 = -1;
                power2 = 1;
            }
            if (gamepad1.a) {
                power1 -= 0.1;
            }
            if (gamepad1.b) {
                power1 += 0.1;
            }
            if (gamepad1.x) {
                power2 -= 0.1;
            }
            if (gamepad1.y) {
                power2 += 0.1;
            }
            if (gamepad1.start) {
                power1 = 0;
                power2 = 0;
            }
            motor1.setPower(power1);
            motor2.setPower(power2);

            telemetry.addData("Motor 1 Power: ", motor1.getPower());
            telemetry.addData("Motor 2 Power: ", motor2.getPower());
            telemetry.update();
        }
    }
}
