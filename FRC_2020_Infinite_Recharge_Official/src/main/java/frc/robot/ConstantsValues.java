/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class ConstantsValues {

    //Constants used as variables for things like speeds etc.
    
    //VARIABLES
    //Distance Per Pulses and PPR
    public static final int driveDisPerPulse = 0;
    public static final double shooterInDisPerPulse = 0;
    public static final double shooterMeterDisPerPulse = 0;
    public static final double tiltDisPerPulse = 0;
    public static final double panDisPerPulse = 0;
    public static final double tiltEncPPR = 2000;
    public static final double panEncPPR = 2000;

    //Min speeds
    public static final double genericMinSpeed = .1;
    public static final double shooterMinSpeed = .1;
    public static final double loaderMinSpeed = .1;
    public static final double panMinSpeed = .1;
    public static final double tiltMinSpeed = .1;

    //PID AutoAim
    public static final double kPanP = .1;
    public static final double kPanI = 0;
    public static final double kPanD = 0;
    public static final double kTiltP = .1;
    public static final double kTiltI = 0;
    public static final double kTiltD = 0;
    public static final double tiltTolerance = 5;
    public static final double panTolerance = 5;

    //Misc.
    public static final double intakeSpeed = 0.5;
    public static final double axisThreshold = .08;
    public static final double acceleration = -9.8; 
    public static final double negativeTargetVelocity = -.5;    
    public static final double targetHeightLowered = 2;
    public static final double targetHeightLifted = 1.9;
    public static final double limlightAngleLow = 20;
    public static final double limlightAngleHigh = 20;
    
}
