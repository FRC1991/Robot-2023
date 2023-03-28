// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

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
  public static final int clawMotor = 13;
  //Gyro
  public static final int pigeonIMU = 0;
  //Global Deadband and Motor Multiplier
  public static final double globalDeadband = 0.3;

  //PID turn Constants
public static final double kTurnP = 0.1;
public static final double kTurnI = 0;
public static final double kTurnD = 0.01;

public static final double kMaxTurnRateDegPerS = 45;
public static final  double kMaxTurnAccelerationDegPerSSquared = 90;

public static final double kTurnToleranceDeg = 2;
public static final double kTurnRateToleranceDegPerS = 10; 

  //Trajectory following Constants
  
public static final double ksVolt = 0.30037;
public static final double kvVolt = 12.498;
public static final double kaVolt = 4.2915;
public static final double kpVolt = 0.94873;
public static final double trackWitdh = 2.1;

public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(trackWitdh);

public static final double kMaxSpeed = 0.6;
public static final double kMaxAcceleration= 2;

public static final double kRamseteB = 6.5;
public static final double kRamseteZeta = 2.2;


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