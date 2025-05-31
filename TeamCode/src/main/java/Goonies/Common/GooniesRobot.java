package Goonies.Common;

import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GooniesRobot implements IRobot{
    private final HardwareMap _hardwareMap;
    private IDriveTrain _mecanumDriveTrain;

    public GooniesRobot(HardwareMap hardwareMap){
        _hardwareMap = hardwareMap;
    }

    @Override
    public void Initialize(boolean forAutonomous) {
        Pose startingPose = new Pose(0, 0, 0);
        _mecanumDriveTrain = new MecanumDriveTrain(_hardwareMap, startingPose);

        if (!forAutonomous) {
            _mecanumDriveTrain.getFollower().startTeleopDrive();
        }
    }

    @Override
    public IDriveTrain getDriveTrain() {
        return _mecanumDriveTrain;
    }
}
