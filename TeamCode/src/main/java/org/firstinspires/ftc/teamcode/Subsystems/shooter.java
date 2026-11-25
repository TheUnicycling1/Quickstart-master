package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class shooter {
    private final DcMotor ShooterMotor;
    private final double power = 1;


    public shooter(HardwareMap hardwareMap) {
        ShooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
        ShooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void runMotor(double pow){
        ShooterMotor.setPower(pow);
    }

    public void stopMotor() {
        runMotor(0);

    }

    public void outtake(){
        runMotor(power);
    }
}
