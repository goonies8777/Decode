package Goonies.Common;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class GooniesRobot implements IRobot{
    private final HardwareMap _hardwareMap;
    private IDriveTrain _mecanumDriveTrain;
    private Intake _intake;
    private Shooter _shooter;

    private RobotState _state;

    public GooniesRobot(HardwareMap hardwareMap){
        _hardwareMap = hardwareMap;
    }

    @Override
    public void Initialize(boolean forAutonomous){
        Pose startingPose = new Pose(0,0,90);
        Initialize(forAutonomous, startingPose);
    }

    public void Initialize(boolean forAutonomous, Pose startingPose) {
        _state = RobotState.Driving;

        _mecanumDriveTrain = new MecanumDriveTrain(_hardwareMap, startingPose);

        DcMotor intakeMotor = _hardwareMap.get(DcMotor.class, "intake");
        CRServo conveyorServo = _hardwareMap.get(CRServo.class, "conveyor");

        IConveyor conveyor = new Conveyor(conveyorServo);
        _intake = new Intake(intakeMotor, conveyor);

        DcMotor leftMotor = _hardwareMap.get(DcMotor.class, "shooterLeft");
        DcMotor rightMotor = _hardwareMap.get(DcMotor.class, "shooterRight");
        Servo shooterServo = _hardwareMap.get(Servo.class, "shooter");

        _shooter = new Shooter(leftMotor, rightMotor, shooterServo, conveyor);

        if (!forAutonomous) {
            _mecanumDriveTrain.getFollower().startTeleopDrive();
            _mecanumDriveTrain.getFollower().update();
        }
    }

    @Override
    public IDriveTrain getDriveTrain() {
        return _mecanumDriveTrain;
    }

    @Override
    public Intake getIntake() { return _intake; }

    @Override
    public Shooter getShooter() { return _shooter; }

    @Override
    public RobotState getState(){ return _state; }

    @Override
    public void setState(RobotState state)
    {
        _state = state;
    }
}
