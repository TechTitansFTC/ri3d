package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ShooterTest", group="Test")
public class ShooterTest extends LinearOpMode {

    private DcMotor shooterLeft, shooterRight;
    private Servo feederLeft, feederRight, pusher;

    // shooter speed (0.0 – 1.0)
    private double shooterPower = 0.0;
    // servo positions (0.0 – 1.0)
    private double feederPos = 0.5;
    private double pusherPos = 0.5;

    // adjust step sizes
    private static final double SPEED_STEP = 0.05;
    private static final double SERVO_STEP = 0.02;

    @Override
    public void runOpMode() {
        // Hardware mapping
        shooterLeft  = hardwareMap.get(DcMotor.class, "shooterLeft");
        shooterRight = hardwareMap.get(DcMotor.class, "shooterRight");
        feederLeft   = hardwareMap.get(Servo.class, "feederLeft");
        feederRight  = hardwareMap.get(Servo.class, "feederRight");
        pusher       = hardwareMap.get(Servo.class, "pusher");

        // Reverse one motor so they spin opposite directions
        shooterLeft.setDirection(DcMotor.Direction.REVERSE);
        shooterRight.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            /* Shooter motor controls */
            if (gamepad1.a) {
                // turn shooter ON at current speed
                shooterLeft.setPower(shooterPower);
                shooterRight.setPower(shooterPower);
            }

            if (gamepad1.b) {
                // turn shooter OFF
                shooterLeft.setPower(0.0);
                shooterRight.setPower(0.0);
            }

            if (gamepad1.x) {
                shooterPower = Math.min(1.0, shooterPower + SPEED_STEP);
            }

            if (gamepad1.y) {
                shooterPower = Math.max(0.0, shooterPower - SPEED_STEP);
            }

            /* Feeder pair servo controls */
            if (gamepad1.dpad_up) {
                feederPos = Math.min(1.0, feederPos + SERVO_STEP);
            }
            if (gamepad1.dpad_down) {
                feederPos = Math.max(0.0, feederPos - SERVO_STEP);
            }

            feederLeft.setPosition(feederPos);
            feederRight.setPosition(feederPos);

            /* Independent pusher servo controls */
            if (gamepad1.dpad_right) {
                pusherPos = Math.min(1.0, pusherPos + SERVO_STEP);
            }
            if (gamepad1.dpad_left) {
                pusherPos = Math.max(0.0, pusherPos - SERVO_STEP);
            }

            pusher.setPosition(pusherPos);

            /* Telemetry for debugging */
            telemetry.addData("Shooter Power", "%.2f", shooterPower);
            telemetry.addData("Feeder Pos",   "%.2f", feederPos);
            telemetry.addData("Pusher Pos",   "%.2f", pusherPos);
            telemetry.update();

            sleep(100); // small delay to avoid flooding
        }
    }
}
