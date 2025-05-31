package Goonies.Common;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;

public interface IDriveTrain {
    void setDriveMode(DriveMode driveMode);
    DriveMode getDriveMode();
    Follower getFollower();
    Pose getPose();
    void setStartingPose(Pose startingPose);
    void setPose(Pose pose);
}
