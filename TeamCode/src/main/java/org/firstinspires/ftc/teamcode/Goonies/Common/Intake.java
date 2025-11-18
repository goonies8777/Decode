package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.util.Timing;

import java.util.concurrent.TimeUnit;

public class Intake {
    private final DcMotor _motor;
    private final IConveyor _conveyor;

    public Intake(DcMotor motor, IConveyor conveyor)
    {
        _motor = motor;
        _motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _motor.setDirection(DcMotor.Direction.REVERSE);

        _conveyor = conveyor;
    }

    public void start()
    {
        _motor.setPower(1);
        _conveyor.start();
    }

    public void stop ()
    {
        _motor.setPower(0);
        _conveyor.stop();
    }

    public void vomit()
    {
        Timing.Timer timer = new Timing.Timer(2, TimeUnit.SECONDS);

        _motor.setDirection(DcMotor.Direction.FORWARD);
        _motor.setPower(1);
        _conveyor.reverse();

        timer.start();
        while(!timer.done()){}

        _conveyor.stop();
        _motor.setPower(0);
        _motor.setDirection(DcMotor.Direction.REVERSE);
    }
}
