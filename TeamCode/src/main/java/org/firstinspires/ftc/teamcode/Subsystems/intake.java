package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {
    private final DcMotor IntakeMotor;
    final double power = 1;

    public intake(HardwareMap hardwareMap) {
        IntakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");
        IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void runMotor(double pow){
        IntakeMotor.setPower(pow);
    }
    public void stopMotor(){
        runMotor(0);
    }
    public void intake(){
        runMotor(power);
    }
    public void outtake(){
        runMotor(-1);
    }
}

