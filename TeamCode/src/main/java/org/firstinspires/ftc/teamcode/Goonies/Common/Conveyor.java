package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.CRServo;

public class Conveyor implements IConveyor{

    private final CRServo _servo;
    private boolean _isRunning = false;

    Conveyor(CRServo servo)
    {
        _servo = servo;
    }

    @Override
    public void start() {
        _servo.setPower(1);
        _isRunning = true;
    }

    @Override
    public void stop(){
        _servo.setPower(0);
        _isRunning = false;
    }

    @Override
    public void reverse()
    {
        _servo.setPower(-1);
        _isRunning = true;
    }

    @Override
    public boolean isRunning(){
        return _isRunning;
    }
}
