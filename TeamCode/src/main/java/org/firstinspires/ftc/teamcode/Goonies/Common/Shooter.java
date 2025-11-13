package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.util.Timing;

import java.util.concurrent.TimeUnit;

public class Shooter {
    private final DcMotor _leftMotor;
    private final DcMotor _rightMotor;
    private final Servo _servo;
    private final IConveyor _conveyor;
    private double shooterSpeed = .70;

    public Shooter(DcMotor leftMotor, DcMotor rightMotor, Servo servo, IConveyor conveyor)
    {
        _leftMotor = leftMotor;
        _rightMotor = rightMotor;

        _leftMotor.setDirection(DcMotor.Direction.FORWARD);
        _rightMotor.setDirection(DcMotor.Direction.REVERSE);

        _leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        _leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        _servo = servo;
        _conveyor = conveyor;
    }

    public void rampUp()
    {
        _leftMotor.setPower(shooterSpeed);
        _rightMotor.setPower(shooterSpeed);
    }

    public void shoot()
    {
        Timing.Timer timer = new Timing.Timer(1000, TimeUnit.MILLISECONDS);
        _servo.setPosition(0);

        timer.start();
        while(!timer.done()){}

        _servo.setPosition(1);
    }

    public void startConveyor()
    {
        if (!_conveyor.isRunning()) {
            _conveyor.start();
        }
    }

    public void stopConveyor()
    {
        if (_conveyor.isRunning()) {
            _conveyor.stop();
        }
    }

    public void reverseConveyor(){
        if (!_conveyor.isRunning()){
            _conveyor.reverse();
        }
    }

    public void stop()
    {
        _leftMotor.setPower(0);
        _rightMotor.setPower(0);
    }
}