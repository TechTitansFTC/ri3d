package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class motortest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Define motors
        DcMotorEx motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
        DcMotorEx motor2 = hardwareMap.get(DcMotorEx.class, "motor2");

        // Default to forward
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {
            // Joystick control: up = positive power, down = negative power
            double power = -gamepad1.left_stick_y;  // stick up = forward

            // Apply power to both motors
            motor1.setPower(power);
            motor2.setPower(power);

            // Button X → set forward
            if (gamepad1.x) {
                motor1.setDirection(DcMotorSimple.Direction.FORWARD);
                motor2.setDirection(DcMotorSimple.Direction.FORWARD);
            }

            // Button B → set reverse
            if (gamepad1.b) {
                motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                motor2.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            // Debug info
            telemetry.addData("Joystick Power", power);
            telemetry.addData("Motor1 Dir", motor1.getDirection());
            telemetry.addData("Motor2 Dir", motor2.getDirection());
            telemetry.update();
        }
    }
}
