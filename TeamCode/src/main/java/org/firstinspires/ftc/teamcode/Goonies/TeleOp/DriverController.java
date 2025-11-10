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
    private final TelemetryManager _telemetryManager;
    private final Follower _follower;

    public DriverController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
        _telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();
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
        if (_gamePad.isDown(GamepadKeys.Button.RIGHT_BUMPER))
        {
            _robot.setAutomating(true);
            _follower.followPath(PathBuilder.shootingPath().get());
        } else if (_gamePad.isDown(GamepadKeys.Button.LEFT_BUMPER))
        {
            _robot.setAutomating(true);
            _follower.followPath(PathBuilder.parkingPath().get());
        } else
        {
            if (_gamePad.getButton(GamepadKeys.Button.Y)){
                Pose currentPose = _follower.getPose();
                currentPose.setHeading(Math.toRadians((0)));

                _follower.setPose(currentPose);
            }

            _follower.setTeleOpDrive(
                    _gamePad.getLeftY(),
                    -_gamePad.getLeftX(),
                    -_gamePad.getRightX(),
                    (_robot.getDriveTrain().getDriveMode() == DriveMode.ROBOT_CENTRIC));
            _follower.update();

        }
    }
}
