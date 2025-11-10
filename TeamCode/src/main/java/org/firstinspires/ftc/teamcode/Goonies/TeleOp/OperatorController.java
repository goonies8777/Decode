package org.firstinspires.ftc.teamcode.Goonies.TeleOp;

import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.RobotState;

public class OperatorController implements IController{
    private final GamepadEx _gamePad;
    private final IRobot _robot;

    public OperatorController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
    }
    @Override
    public void HandleInput()
    {
        switch(_robot.getState())
        {
            case Driving:
                HandleDrivingState();
                break;
            case Intake:
                HandleIntakeState();
                break;
            case Shooting:
                HandleShootingState();
                break;
        }
    }

    private void HandleDrivingState()
    {
        if (_gamePad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
            _robot.setState(RobotState.Intake);
        }

        if (_gamePad.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
            _robot.setState(RobotState.Shooting);
        }
    }

    private void HandleIntakeState()
    {
        if (_gamePad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
            _robot.getIntake().stop();
            _robot.setState(RobotState.Driving);
        }

        if (_gamePad.getButton(GamepadKeys.Button.Y)) {
            _robot.getIntake().start();
        }

        if (_gamePad.getButton(GamepadKeys.Button.A)) {
            _robot.getIntake().stop();
        }

        if (_gamePad.getButton(GamepadKeys.Button.B)) {
            _robot.getIntake().vomit();
        }
    }

    private void HandleShootingState()
    {
        if (_gamePad.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
            _robot.getShooter().stop();
            _robot.setState(RobotState.Driving);
        }

        if (_gamePad.getButton(GamepadKeys.Button.Y)) {
            _robot.getShooter().rampUp();
        }

        if (_gamePad.getButton(GamepadKeys.Button.X)) {
            _robot.getShooter().advance();
        }

        if (_gamePad.getButton(GamepadKeys.Button.A)) {
            _robot.getShooter().stop();
        }

        if (_gamePad.getButton(GamepadKeys.Button.B)) {
            _robot.getShooter().shoot();
        }
    }
}
