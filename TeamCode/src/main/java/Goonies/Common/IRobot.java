package Goonies.Common;

public interface IRobot {
    void Initialize(boolean forAutonomous);
    IDriveTrain getDriveTrain();
    Intake getIntake();
    Shooter getShooter();
    RobotState getState();
    void setState(RobotState state);
}
