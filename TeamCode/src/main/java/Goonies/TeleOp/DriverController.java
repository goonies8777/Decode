package Goonies.TeleOp;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import java.util.function.Supplier;

import Goonies.Common.DriveMode;
import Goonies.Common.IRobot;

public class DriverController implements IController {

    private final GamepadEx _gamePad;
    private final IRobot _robot;
    private final TelemetryManager _telemetryManager;
    private final Supplier<PathChain> _shootingPathChain;
    private final Supplier<PathChain> _parkingPathChain;
    private final Follower _follower;
    private boolean automatedDrive = false;

    public DriverController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
        _telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();
        _follower = _robot.getDriveTrain().getFollower();

        _shootingPathChain = () -> _follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(_follower::getPose, new Pose(45, 98))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(_follower::getHeading, Math.toRadians(45), 0.8))
                .build();

        _parkingPathChain = () -> _follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(_follower::getPose, new Pose(45, 98))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(_follower::getHeading, Math.toRadians(0), 0.8))
                .build();
    }

    @Override
    public void HandleInput() {

        if (!automatedDrive) {
            _follower.setTeleOpDrive(
                    _gamePad.getLeftY(),
                    _gamePad.getLeftX(),
                    _gamePad.getRightX(),
                    (_robot.getDriveTrain().getDriveMode() == DriveMode.ROBOT_CENTRIC));
            _follower.update();
        }

        if (!automatedDrive && _gamePad.isDown(GamepadKeys.Button.RIGHT_BUMPER))
        {
            _follower.followPath(_shootingPathChain.get());
            automatedDrive = true;
        }

        if (!automatedDrive && _gamePad.isDown(GamepadKeys.Button.LEFT_BUMPER))
        {
            _follower.followPath(_parkingPathChain.get());
            automatedDrive = true;
        }

        //Stop automated following if the follower is done
        if (automatedDrive &&
            (
                (
                    !_gamePad.isDown(GamepadKeys.Button.RIGHT_BUMPER)
                    && !_gamePad.isDown(GamepadKeys.Button.LEFT_BUMPER)
                )
                || !_follower.isBusy()
            )
        )
        {
            _follower.startTeleopDrive();
            automatedDrive = false;
        }

        _telemetryManager.debug("Position:", _follower.getPose());
        _telemetryManager.debug("Velocity:", _follower.getVelocity());
        _telemetryManager.debug("Automated Drive:", automatedDrive);
        _telemetryManager.debug("Drive Mode:", _robot.getDriveTrain().getDriveMode().toString());
    }
}
