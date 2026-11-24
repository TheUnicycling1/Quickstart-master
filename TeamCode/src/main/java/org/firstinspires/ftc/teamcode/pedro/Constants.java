package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.36373911119910407);
                Controller secondaryTranslationalForward = Controller.proportional(0.13439188230001503);
                Controller primaryTranslationalLateral = Controller.proportional(0.6019481470822818);
                Controller secondaryTranslationalLateral = Controller.proportional(0.22240375599618337);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.016551035154183064));
                c.brake.set(Controller.proportionalFeedforward(0.014068379881055604));

                c.headingFeedback.set(Controller.proportional(6.029008849513724));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.057868273990002876, 0.0036723504784385587));

                c.linearBrakeCoefficients.set(Matrix.diag(0.09427726755626761, 0.05834025870459208));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0012174615304765487, 0.0016243120105979751));

                c.maxAchievableForwardVelocity.set(62.11514968338069);
                c.maxAchievableStrafeVelocity.set(49.45880539272765);
                c.naturalForwardDeceleration.set(27.963510064384355);
                c.naturalStrafeDeceleration.set(68.24975023461626);
            }
    );

    public static MecanumConfig drivetrainConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("FrontLeftMotor");
                c.frontRightName.set("FrontRightMotor");
                c.backLeftName.set("BackLeftMotor");
                c.backRightName.set("BackRightMotor");
                c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
            }
    );
    public static PinpointConfig localizerConfig = new PinpointConfig(
            c -> {
             c.name.set("pinpoint");
             c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
             c.xPodOffset.set(3.2856137733759843);
             c.yPodOffset.set(7.085455947034941);
             c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
             c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
             c.globalDistanceUnit.set(DistanceUnit.INCH);
             c.offsetUnits.set(DistanceUnit.INCH);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}