package Goonies.TeleOp;

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
        _driverController = new DriverController(_robot, new GamepadEx(this.gamepad1));
        _operatorController = new OperatorController(_robot, new GamepadEx(this.gamepad2));

        if (_robot != null) {
            _robot.Initialize(false);
        }else {
            telemetry.addData("Robot", "Robot was not Instantiated");
            telemetry.update();
        }

        telemetry.addData("Status", "Operator Controlled Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if ((_driverController != null || _operatorController != null)) {
            _robot.setState(RobotState.Driving);
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                if (_driverController != null) {
                    _driverController.HandleInput();
                }

                if (_operatorController != null) {
                    _operatorController.HandleInput();
                }

                telemetry.update();
            }
        } else {
            telemetry.addData("GamePads", "No GamePads Instantiated");
            telemetry.update();
        }
    }
}
