package Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.util.Timing;

import java.util.concurrent.TimeUnit;

public class Shooter {
    private final DcMotor _leftMotor;
    private final DcMotor _rightMotor;
    private final Servo _servo;
    private final IConveyor _conveyor;

    public Shooter(DcMotor leftMotor, DcMotor rightMotor, Servo servo, IConveyor conveyor)
    {
        _leftMotor = leftMotor;
        _rightMotor = rightMotor;

        _leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        _leftMotor.setDirection(DcMotor.Direction.REVERSE);
        _rightMotor.setDirection(DcMotor.Direction.FORWARD);

        _servo = servo;
        _conveyor = conveyor;
    }

    public void rampUp()
    {
        _leftMotor.setPower(1);
        _rightMotor.setPower(1);
    }

    public void shoot()
    {
        _servo.setPosition(1);
        _servo.setPosition(0);
    }

    private void advance()
    {
        Timing.Timer timer = new Timing.Timer(2, TimeUnit.SECONDS);

        _conveyor.start();

        timer.start();
        while(!timer.done()){}

        _conveyor.stop();
    }

    public void stop()
    {
        _leftMotor.setPower(0);
        _rightMotor.setPower(0);
    }
}
