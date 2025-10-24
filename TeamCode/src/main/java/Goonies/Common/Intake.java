package Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Intake {
    private DcMotor _motor;

    public Intake(DcMotor motor)
    {
        _motor = motor;
        _motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void start()
    {
        _motor.setPower(1);
    }

    public void stop ()
    {
        _motor.setPower(0);
    }

    public void vomit()
    {
        _motor.setDirection(DcMotorSimple.Direction.FORWARD);
        _motor.setPower(1);

        //TODO: Add Timer

        _motor.setPower(0);
        _motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
