package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.pedropathing.geometry.Pose;

public interface IRobot {
    void Initialize(boolean forAutonomous);
    void Initialize(boolean forAutonomous, Pose startingPose);

    IDriveTrain getDriveTrain();
    Intake getIntake();
    Shooter getShooter();
    void updateIndicators();
    RobotState getState();
    void setState(RobotState state);
    boolean isAutomated();
    void setAutomating(boolean isAutomating);
}
