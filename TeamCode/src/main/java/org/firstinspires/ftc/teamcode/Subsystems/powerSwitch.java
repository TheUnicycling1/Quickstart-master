package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class powerSwitch {
    private AnalogInput powerSwitch;

    public void powerSwitch(HardwareMap hardwareMap){
        powerSwitch = hardwareMap.get(AnalogInput.class, "powerSwitch");
    }

    public double getVoltage(){
       double voltage = powerSwitch.getVoltage();
       return voltage;
    }

}
