package Goonies.TeleOp;

import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import Goonies.Common.DriveMode;
import Goonies.Common.IRobot;


public class OperatorController implements IController{
    private final GamepadEx _gamePad;
    private final IRobot _robot;

    public OperatorController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
    }
    @Override
    public void HandleInput() {

        if (_gamePad.getButton(GamepadKeys.Button.Y))
        {
            _robot.getIntake().start();
        }

        if (_gamePad.getButton(GamepadKeys.Button.A))
        {
            _robot.getIntake().stop();
        }

        if (_gamePad.getButton(GamepadKeys.Button.B))
        {
            _robot.getIntake().vomit();
        }

    }
}
