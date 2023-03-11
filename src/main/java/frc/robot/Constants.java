// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  //Drivetrain Motors
  public static final int leftDriveMotor1 = 2;
  public static final int leftDriveMotor2 = 3;
  public static final int leftDriveMotor3 = 4;
  public static final int rightDriveMotor1 = 5;
  public static final int rightDriveMotor2 = 6;
  public static final int rightDriveMotor3 = 7;
  //Turret Motors
  public static final int turretMotor1 = 8;
  public static final int turretMotor2 = 9;

  //Arm Motor
  public static final int armMotorExtend = 10;
  public static final int armLiftMotor1 = 11;
  public static final int armLiftMotor2 = 12;

  //Claw Motors
  public static final int clawTurretMotor = 13;
  public static final int clawMotor = 14;
  //Gyro
  public static final int pigeonIMU = 0;
  //Global Deadband and Motor Multiplier
  public static final double globalDeadband = 0.1;

  //PID turn Constants
public static final double kTurnP = 0.1;
public static final double kTurnI = 0;
public static final double kTurnD = 0.01;

public static final double kMaxTurnRateDegPerS = 45;
public static final  double kMaxTurnAccelerationDegPerSSquared = 90;

public static final double kTurnToleranceDeg = 2;
public static final double kTurnRateToleranceDegPerS = 10; 

  //PID ChargeStation Constants
    
  public static final double kChargeP = 1;
  public static final double kChargeI = 0;
  public static final double kChargeD = 0;
  
  public static final double kMaxChargeRateDegPerS = 40;
  public static final  double kMaxChargeAccelerationDegPerSSquared = 85;
  
  public static final double kChargeToleranceDeg = 2;
  public static final double kChargeRateToleranceDegPerS = 10;

  //PID For Arm Extension

  public static final double kArmExtendP = 1;
  public static final double kArmExtendI = 0;
  public static final double kArmExtendD = 0;

  public static final double kMaxArmExtendRatePerS = 5;
  public static final  double kMaxArmExtendAccelerationPerSSquared = 10;

  public static final double kArmExtendRateTolerancePerS = 0.166667;
  public static final double kArmExtendTolerance = 2; 

  


  //PID for arm Lifter

  public static final double kArmLiftP = 1;
  public static final double kArmLiftI = 0;
  public static final double kArmLiftD = 0;

  public static final double kMaxArmLiftRatePerS = 5;
  public static final  double kMaxArmLiftAccelerationPerSSquared = 10;

  public static final double kArmLiftToleranceDeg = 0.0833333;
  public static final double kArmLiftRateToleranceDegPerS = 2;


  //Vision constant

    public static final double visionConstant = 0.0015;

//Drive Distance PID Constants

    public static final double kDriveP = 1;
    public static final double kDriveI = 0;
    public static final double kDriveD = 0;

    public static final double kMaxDistPerS = 45;
    public static final double kMaxAccelerationPerS = 90;

    public static final double kDistTolerance = 0.183333;
    public static final double kDistRateTolerancePerS = 1;
    
    

  }