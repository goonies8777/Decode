package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.PedroPathing.Constants.Constants;

import java.util.concurrent.TimeUnit;

public class MecanumDriveTrain implements IDriveTrain{

    private DriveMode _driveMode = DriveMode.ROBOT_CENTRIC;
    private final Follower _follower;
    //
    public MecanumDriveTrain(HardwareMap hardwareMap, Pose startingPose){
        _follower = Constants.createFollower(hardwareMap);
        _follower.setStartingPose(startingPose);
        _follower.update();
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

    public void driveForwardForMiliseconds(int miliseconds){
        Timing.Timer timer = new Timing.Timer(miliseconds, TimeUnit.MILLISECONDS);

        _follower.startTeleOpDrive();

        timer.start();

        while(!timer.done()) {
            _follower.setTeleOpDrive(0.5, 0, 0, true);
            _follower.update();
        }

        _follower.setTeleOpDrive(0,0,0, true);
    }

    public void driveBackwardForMiliseconds(int miliseconds){
        Timing.Timer timer = new Timing.Timer(miliseconds, TimeUnit.MILLISECONDS);

        _follower.startTeleOpDrive();

        timer.start();

        while(!timer.done()) {
            _follower.setTeleOpDrive(-0.5, 0, 0, true);
            _follower.update();
        }

        _follower.setTeleOpDrive(0,0,0, true);
    }

    public void driveLeftForMiliseconds(int miliseconds){
        Timing.Timer timer = new Timing.Timer(miliseconds, TimeUnit.MILLISECONDS);

        _follower.startTeleOpDrive();

        timer.start();

        while(!timer.done()) {
            _follower.setTeleOpDrive(0, 0.5, 0, true);
            _follower.update();
        }

        _follower.setTeleOpDrive(0,0,0, true);
    }

    public void driveRightForMiliseconds(int miliseconds){
        Timing.Timer timer = new Timing.Timer(miliseconds, TimeUnit.MILLISECONDS);

        _follower.startTeleOpDrive();

        timer.start();

        while(!timer.done()) {
            _follower.setTeleOpDrive(0, -0.5, 0, true);
            _follower.update();
        }

        _follower.setTeleOpDrive(0,0,0, true);
    }
}
