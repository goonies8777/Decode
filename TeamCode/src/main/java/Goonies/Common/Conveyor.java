package Goonies.Common;

import com.qualcomm.robotcore.hardware.CRServo;

public class Conveyor implements IConveyor{

    private final CRServo _servo;

    Conveyor(CRServo servo)
    {
        _servo = servo;
    }

    @Override
    public void start() {
        _servo.setPower(1);
    }

    @Override
    public void stop(){
        _servo.setPower(0);
    }

    @Override
    public void reverse()
    {
        _servo.setPower(-1);
    }
}
