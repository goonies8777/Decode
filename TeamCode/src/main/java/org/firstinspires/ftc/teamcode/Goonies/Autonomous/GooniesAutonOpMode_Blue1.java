package org.firstinspires.ftc.teamcode.Goonies.Autonomous;

import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.PoseHistory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Goonies.Common.GooniesRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.PathBuilder;
import org.firstinspires.ftc.teamcode.Goonies.Panels.Drawing;
import org.firstinspires.ftc.teamcode.Goonies.TeleOp.DriverController;
import org.firstinspires.ftc.teamcode.Goonies.TeleOp.OperatorController;

@Autonomous(name="Goonies Auto Red 1", group="Goonies")
public class GooniesAutonOpMode_Blue1 extends LinearOpMode {
    IRobot _robot;

    @IgnoreConfigurable
    static PoseHistory _poseHistory;

    @Override
    public void runOpMode() throws InterruptedException {
        _robot = new GooniesRobot(hardwareMap);

        Pose startingPose =  new Pose(96, 9, Math.toRadians(90));
        TelemetryManager _telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();

        if (_robot != null) {
            _robot.Initialize(true, startingPose);
        }else {
            _telemetryManager.debug("Robot", "Robot was not Instantiated");
        }

        if (_robot != null) {
            PathBuilder.follower = _robot.getDriveTrain().getFollower();
            _poseHistory = _robot.getDriveTrain().getFollower().getPoseHistory();
            _robot.updateIndicators();
        }

        _telemetryManager.update(telemetry);

        Drawing.init();
        drawCurrent();

        waitForStart();

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
