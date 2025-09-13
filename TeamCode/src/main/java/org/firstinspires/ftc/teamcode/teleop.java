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

        // Drivetrain motors
     /*   DcMotor frontLeftMotor  = hardwareMap.dcMotor.get("leftFront");
        DcMotor backLeftMotor   = hardwareMap.dcMotor.get("leftBack");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rightFront");
        DcMotor backRightMotor  = hardwareMap.dcMotor.get("rightBack");*/

        // Mechanism motors
        DcMotor intake       = hardwareMap.get(DcMotor.class, "int");
        DcMotor shooterLeft  = hardwareMap.get(DcMotor.class, "sl");
        DcMotor shooterRight = hardwareMap.get(DcMotor.class, "sr");

        // Servo
        Servo servoKickers   = hardwareMap.get(Servo.class, "sk");

        // Reverse right side for standard mecanum
//        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
//        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // IMU init (field-centric drive)
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double speedFactor = 1.0;
            if (gamepad1.left_bumper)  speedFactor = 0.7;
            if (gamepad1.right_bumper) speedFactor = 0.5;

            double y  = -gamepad1.left_stick_y;
            double x  =  gamepad1.left_stick_x;
            double rx =  gamepad1.right_stick_x;

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
            rotX *= 1.1;

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower  = (rotY + rotX + rx) / denominator * speedFactor;
            double backLeftPower   = (rotY - rotX + rx) / denominator * speedFactor;
            double frontRightPower = (rotY - rotX - rx) / denominator * speedFactor;
            double backRightPower  = (rotY + rotX - rx) / denominator * speedFactor;


//            frontLeftMotor.setPower(frontLeftPower);
//            backLeftMotor.setPower(backLeftPower);
//            frontRightMotor.setPower(frontRightPower);
//            backRightMotor.setPower(backRightPower);

            if (gamepad1.right_trigger > 0.1) {
                intake.setPower(1.0);                 // intake in
            } else if (gamepad1.left_trigger > 0.1) {
                intake.setPower(-1.0);                // intake reverse
            } else {
                intake.setPower(0.0);                 // stop
            }

            if (gamepad1.y) {
                shooterLeft.setPower(-1.0);
                shooterRight.setPower(1.0);
            } else {
                shooterLeft.setPower(0.0);
                shooterRight.setPower(0.0);
            }

            if (gamepad1.a) {
                servoKickers.setPosition(0.7);
            } else {
                servoKickers.setPosition(0.3);
            }

            telemetry.addData("Drive Speed Factor", speedFactor);
            telemetry.addData("Intake Power", intake.getPower());
            telemetry.update();
        }
    }
}
