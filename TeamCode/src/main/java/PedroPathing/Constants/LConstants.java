package PedroPathing.Constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
//        ThreeWheelConstants.forwardTicksToInches = .001989436789;
//        ThreeWheelConstants.strafeTicksToInches = .001989436789;
//        ThreeWheelConstants.turnTicksToInches = .001989436789;
//        ThreeWheelConstants.leftY = 1;
//        ThreeWheelConstants.rightY = -1;
//        ThreeWheelConstants.strafeX = -2.5;
//        ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
//        ThreeWheelConstants.rightEncoder_HardwareMapName = "rightRear";
//        ThreeWheelConstants.strafeEncoder_HardwareMapName = "rightFront";
//        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
//        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
//        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;

        DriveEncoderConstants.forwardTicksToInches = 0.0047;
        DriveEncoderConstants.strafeTicksToInches = 0.0056;
        DriveEncoderConstants.turnTicksToInches = 0.0118;

        DriveEncoderConstants.robot_Width = 16.5;
        DriveEncoderConstants.robot_Length = 17;

        DriveEncoderConstants.leftFrontEncoderDirection = Encoder.FORWARD;
        DriveEncoderConstants.leftRearEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.rightFrontEncoderDirection = Encoder.REVERSE;
        DriveEncoderConstants.rightRearEncoderDirection = Encoder.FORWARD;
    }
}
