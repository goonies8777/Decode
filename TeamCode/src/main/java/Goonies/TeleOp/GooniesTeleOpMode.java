package Goonies.TeleOp;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Goonies.Common.GooniesRobot;
import Goonies.Common.IRobot;
import Goonies.Common.RobotState;

@TeleOp(group = "Goonies", name = "MiniVinnie")
public class GooniesTeleOpMode extends LinearOpMode {
    IRobot _robot;
    IController _driverController;
    IController _operatorController;

    @Override
    public void runOpMode() {
        _robot = new GooniesRobot(hardwareMap);

        Pose startingPose =  new Pose(48, 9, Math.toRadians(90));
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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if ((_driverController != null || _operatorController != null)) {
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                if (_driverController != null) {
                    _driverController.HandleInput();
                }

                if (_operatorController != null) {
                    _operatorController.HandleInput();
                }
            }
        } else {
            _telemetryManager.debug("GamePads", "No GamePads Instantiated");
        }
    }
}
