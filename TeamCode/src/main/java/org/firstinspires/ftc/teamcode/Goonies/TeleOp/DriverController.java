package org.firstinspires.ftc.teamcode.Goonies.TeleOp;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Goonies.Common.DriveMode;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.PathBuilder;

public class DriverController implements IController {

    private final GamepadEx _gamePad;
    private final IRobot _robot;
    private final Follower _follower;

    public DriverController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
        _follower = _robot.getDriveTrain().getFollower();
    }

    @Override
    public void HandleInput() {
        //Stop automated following if the follower is done
        if (_robot.isAutomated())
        {
            HandleAutomated();
        }
        else
        {
            HandleManual();
        }
    }

    private void HandleAutomated()
    {
        if ((
                !_gamePad.isDown(GamepadKeys.Button.RIGHT_BUMPER)
                && !_gamePad.isDown(GamepadKeys.Button.LEFT_BUMPER)
            )
            || !_follower.isBusy()
        ) {
            _follower.startTeleopDrive();
            _robot.setAutomating(false);
        }
    }

    private void HandleManual()
    {
        if (_gamePad.getButton(GamepadKeys.Button.Y)){
            Pose currentPose = _follower.getPose();
            Pose updatedHeading = new Pose(currentPose.getX(), currentPose.getY(), Math.toRadians(0));

            _follower.setPose(updatedHeading);
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_UP)){
            _robot.getShooter().setShooterSpeed(.65);
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_RIGHT)){
            _robot.getShooter().setShooterSpeed(.70);
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_DOWN)){
            _robot.getShooter().setShooterSpeed(.75);
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_LEFT)){
            _robot.getShooter().setShooterSpeed(.80);
        }

        if (_gamePad.getButton(GamepadKeys.Button.LEFT_BUMPER)){
            _robot.getShooter().setShooterSpeed(.85);
        }

        if (_gamePad.getButton(GamepadKeys.Button.RIGHT_BUMPER)){
            _robot.getShooter().setShooterSpeed(.90);
        }

        if (_gamePad.getButton(GamepadKeys.Button.B)){
            _robot.getShooter().resetShooterSpeed();
        }

        _follower.setTeleOpDrive(
                _gamePad.getLeftY(),
                -_gamePad.getLeftX(),
                -_gamePad.getRightX(),
                (_robot.getDriveTrain().getDriveMode() == DriveMode.ROBOT_CENTRIC));
        _follower.update();

    }
}
