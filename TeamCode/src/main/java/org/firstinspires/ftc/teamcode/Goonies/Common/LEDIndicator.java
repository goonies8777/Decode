package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.DigitalChannel;

public class LEDIndicator implements IIndicator{
    private final DigitalChannel _redLED;
    private final DigitalChannel _greenLED;

    public LEDIndicator(DigitalChannel redLED, DigitalChannel greenLED)
    {
        _redLED = redLED;
        _greenLED = greenLED;

        _redLED.setMode(DigitalChannel.Mode.OUTPUT);
        _greenLED.setMode(DigitalChannel.Mode.OUTPUT);
    }

    public void showRed()
    {
        _redLED.setState(true);
        _greenLED.setState(false);
    }

    public void showGreen()
    {
        _redLED.setState(false);
        _greenLED.setState(true);
    }

    public void showAmber()
    {
        _redLED.setState(false);
        _greenLED.setState(false);
    }

    public void off()
    {
        _redLED.setState(true);
        _greenLED.setState(true);
    }

    @Override
    public void Update(RobotState robotState) {
        switch (robotState)
        {
            case Intake:
                showRed();
                break;
            case Shooting:
                showGreen();
                break;
            case Driving:
            default:
                showAmber();
                break;

        }
    }
}
