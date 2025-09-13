package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        // Mechanism motors
        DcMotor intake       = hardwareMap.get(DcMotor.class, "int");
        DcMotor shooterLeft  = hardwareMap.get(DcMotor.class, "sl");
        DcMotor shooterRight = hardwareMap.get(DcMotor.class, "sr");

        // Servo
        Servo servoKickers   = hardwareMap.get(Servo.class, "sk");

        // IMU init (still used for heading if needed)
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        // Shooter power variable with edge detection flags
        double shooterPower = 1.0;      // start full power
        boolean dpadUpLast = false;
        boolean dpadDownLast = false;

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {

            if (gamepad1.dpad_up && !dpadUpLast) {
                shooterPower += 0.1;
                if (shooterPower > 1.0) shooterPower = 1.0;
            }
            if (gamepad1.dpad_down && !dpadDownLast) {
                shooterPower -= 0.1;
                if (shooterPower < 0.0) shooterPower = 0.0;
            }
            dpadUpLast = gamepad1.dpad_up;
            dpadDownLast = gamepad1.dpad_down;

            if (gamepad1.right_trigger > 0.1) {
                intake.setPower(1.0);
            } else if (gamepad1.left_trigger > 0.1) {
                intake.setPower(-1.0);
            } else {
                intake.setPower(0.0);
            }

            if (gamepad1.y) {
                shooterLeft.setPower(shooterPower);
                shooterRight.setPower(shooterPower);
            } else {
                shooterLeft.setPower(0.0);
                shooterRight.setPower(0.0);
            }

            // ---------- Servo kicker ----------
            if (gamepad1.a) {
                servoKickers.setPosition(0.7);
            } else {
                servoKickers.setPosition(0.3);
            }

            // ---------- Telemetry ----------
            telemetry.addData("Shooter Power", "%.1f", shooterPower);
            telemetry.addData("Intake Power", intake.getPower());
            telemetry.update();
        }
    }
}
