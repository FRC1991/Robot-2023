// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Trigger;

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
  public static final int turretMotor = 8;
  //Arm Motor
  public static final int armMotor = 9;
  //Claw Motors
  public static final int clawTurretMotor = 10;
  public static final int clawMotor = 11;
  //Gyro
  public static final int pigeonIMU = 0;
  //Global Deadband and Motor Multiplier
  public static final double Deadband = 0.1;

  public static class OperatorConstants {
    //Controller Ports 
    public static final int DriverControllerPort = 0;
    public static final int AuxControllerPort = 1;
    //Drive Controller Mapping
    public static final int driveLeftStickX = 0;
    public static final int driveLeftStickY = 1;
    public static final int driveLeftTrigger = 2;
    public static final int driveRightTrigger = 3;
    public static final int driveRightStickX = 4;
    public static final int driveRightStickY = 5;
    //Aux controller Mapping 
    public static final int AuxAButton = 6;
    


  }
}
