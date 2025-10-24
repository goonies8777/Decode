package Goonies.Common;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class GooniesRobot implements IRobot{
    private final HardwareMap _hardwareMap;
    private IDriveTrain _mecanumDriveTrain;
    private Intake _intake;

    public GooniesRobot(HardwareMap hardwareMap){
        _hardwareMap = hardwareMap;
    }

    @Override
    public void Initialize(boolean forAutonomous) {
        Pose startingPose = new Pose();
        _mecanumDriveTrain = new MecanumDriveTrain(_hardwareMap, startingPose);

        //DcMotor intakeMotor = _hardwareMap.get(DcMotor.class, "intake");
        //_intake = new Intake(intakeMotor);

        if (!forAutonomous) {
            _mecanumDriveTrain.getFollower().startTeleopDrive();
            _mecanumDriveTrain.getFollower().update();
        }
    }

    @Override
    public IDriveTrain getDriveTrain() {
        return _mecanumDriveTrain;
    }

    public Intake getIntake() { return _intake; }
}
