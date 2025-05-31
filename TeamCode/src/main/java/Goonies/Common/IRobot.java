package Goonies.Common;

public interface IRobot {
    void Initialize(boolean forAutonomous);
    IDriveTrain getDriveTrain();
}
