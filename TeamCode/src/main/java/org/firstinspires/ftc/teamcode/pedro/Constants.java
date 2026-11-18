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
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-1.0260884592852255);
        c.yPodOffset.set(5.875220111035926);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.3539411216322665);
                Controller secondaryTranslationalForward = Controller.proportional(0.13077178696217154);
                Controller primaryTranslationalLateral = Controller.proportional(0.6306334254167416);
                Controller secondaryTranslationalLateral = Controller.proportional(0.23300219985601264);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01758129946501655));
                c.brake.set(Controller.proportionalFeedforward(0.014944104545264068));

                c.headingFeedback.set(Controller.proportional(8.01236803283836));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.06527118931656799, 0.0018641914415289949));

                c.linearBrakeCoefficients.set(Matrix.diag(0.08212769331470401, 0.047367762788471955));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0015071839635875288, 0.0021986257070207983));

                c.maxAchievableForwardVelocity.set(60.2502178459482);
                c.maxAchievableStrafeVelocity.set(47.71254102076725);
                c.naturalForwardDeceleration.set(27.868581553818526);
                c.naturalStrafeDeceleration.set(59.95099256745953);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("FrontLeftMotor");
        c.frontRightName.set("FrontRightMotor");
        c.backLeftName.set("BackLeftMotor");
        c.backRightName.set("BackRightMotor");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });
}