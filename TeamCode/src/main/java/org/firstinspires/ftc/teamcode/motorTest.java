package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class motorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motor1 = hardwareMap.get(DcMotor.class, "motor1");

        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double power = 0;
        boolean prevA = false;
        boolean prevB = false;


        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up) power = 1;
            if (gamepad1.dpad_down) power = -1;
            if (gamepad1.start) power = 0;

            // Incremental adjustment with edge detection
            if (gamepad1.a && !prevA) power -= 0.1;
            if (gamepad1.b && !prevB) power += 0.1;
            prevA = gamepad1.a;
            prevB = gamepad1.b;

            // Clamp power
            power = Math.max(-1, Math.min(1, power));

            motor1.setPower(power);
            motor1.setPower(power);

            telemetry.addData("Motor 1 Power: ", motor1.getPower());
            telemetry.update();
        }
    }
}
