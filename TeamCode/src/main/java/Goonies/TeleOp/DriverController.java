package Goonies.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import Goonies.Common.DriveMode;
import Goonies.Common.IRobot;

public class DriverController implements IController {

    private final GamepadEx _gamePad;
    private final IRobot _robot;

    public DriverController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
    }

    @Override
    public void HandleInput() {
        _robot.getDriveTrain()
                .getFollower()
                .setTeleOpMovementVectors(
                        _gamePad.getLeftY(),
                        -_gamePad.getLeftX(),
                        -_gamePad.getRightX(),
                        (_robot.getDriveTrain().getDriveMode() == DriveMode.ROBOT_CENTRIC));
        _robot.getDriveTrain().getFollower().update();
    }
}
