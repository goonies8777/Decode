package org.firstinspires.ftc.teamcode.Goonies.TeleOp;

import com.bylazar.configurables.PanelsConfigurables;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.PoseHistory;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Goonies.Common.GooniesRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.PathBuilder;
import org.firstinspires.ftc.teamcode.Goonies.Panels.Drawing;

@TeleOp(group = "Goonies", name = "MiniVinnie")
public class GooniesTeleOpMode extends LinearOpMode {
    IRobot _robot;
    IController _driverController;
    IController _operatorController;

    @IgnoreConfigurable
    static PoseHistory _poseHistory;

    @Override
    public void runOpMode() {
        _robot = new GooniesRobot(hardwareMap);

        PanelsConfigurables.INSTANCE.refreshClass(this);
        Pose startingPose =  new Pose(48, 9, Math.toRadians(0));
        TelemetryManager _telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();

        if (_robot != null) {
            _robot.Initialize(false, startingPose);
            _driverController = new DriverController(_robot, new GamepadEx(this.gamepad1));
            _operatorController = new OperatorController(_robot, new GamepadEx(this.gamepad2));
        }else {
            _telemetryManager.debug("Robot", "Robot was not Instantiated");
        }

        if (_operatorController != null) {
            _telemetryManager.debug("Status", "Operator Controlled Initialized");
        }

        if (_driverController != null) {
            _telemetryManager.debug("Status", "Driver Controlled Initialized");
        }

        if (_robot != null) {
            PathBuilder.follower = _robot.getDriveTrain().getFollower();
            _poseHistory = _robot.getDriveTrain().getFollower().getPoseHistory();
            _robot.updateIndicators();
        }

        _telemetryManager.update(telemetry);

        Drawing.init();
        drawCurrent();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (_robot != null && (_driverController != null || _operatorController != null)) {
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                if (_driverController != null) {
                    _driverController.HandleInput();
                }

                if (_operatorController != null) {
                    _operatorController.HandleInput();
                }

                _robot.updateIndicators();

                _telemetryManager.debug("Robot State:", _robot.getState().toString());
                _telemetryManager.debug("Position:", _robot.getDriveTrain().getFollower().getPose());
                _telemetryManager.debug("Velocity:", _robot.getDriveTrain().getFollower().getVelocity());
                _telemetryManager.debug("Automated Drive:", _robot.isAutomated());
                _telemetryManager.debug("Drive Mode:", _robot.getDriveTrain().getDriveMode().toString());

                _telemetryManager.debug("x:" + _robot.getDriveTrain().getFollower().getPose().getX());
                _telemetryManager.debug("y:" + _robot.getDriveTrain().getFollower().getPose().getY());
                _telemetryManager.debug("heading:" + _robot.getDriveTrain().getFollower().getPose().getHeading());
                _telemetryManager.debug("total heading:" + _robot.getDriveTrain().getFollower().getTotalHeading());

                _telemetryManager.update(telemetry);

                draw();
                drawCurrentAndHistory();
            }
        } else {
            _telemetryManager.debug("GamePads", "No GamePads Instantiated");
            _telemetryManager.update(telemetry);
        }
    }

    public void draw() {
        Drawing.drawDebug(_robot.getDriveTrain().getFollower());
    }

    public void drawCurrent() {
        try {
            Drawing.drawRobot(_robot.getDriveTrain().getFollower().getPose());
            Drawing.sendPacket();
        } catch (Exception e) {
            throw new RuntimeException("Drawing failed " + e);
        }
    }

    public void drawCurrentAndHistory() {
        Drawing.drawPoseHistory(_poseHistory);
        drawCurrent();
    }
}
