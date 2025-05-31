package Goonies.Common;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import PedroPathing.Constants.FConstants;
import PedroPathing.Constants.LConstants;

public class MecanumDriveTrain implements IDriveTrain{

    private DriveMode _driveMode = DriveMode.FIELD_CENTRIC;
    private final Follower _follower;

    public MecanumDriveTrain(HardwareMap hardwareMap, Pose startingPose){
        _follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        _follower.setStartingPose(startingPose);
    }

    @Override
    public void setDriveMode(DriveMode driveMode) {
        _driveMode = driveMode;
    }

    @Override
    public DriveMode getDriveMode() {
        return _driveMode;
    }

    @Override
    public Follower getFollower() {
        return _follower;
    }

    @Override
    public Pose getPose() {
        return _follower.getPose();
    }

    @Override
    public void setStartingPose(Pose startingPose) {
        _follower.setStartingPose(startingPose);
    }

    @Override
    public void setPose(Pose pose){
        _follower.setPose(pose);
    }
}
