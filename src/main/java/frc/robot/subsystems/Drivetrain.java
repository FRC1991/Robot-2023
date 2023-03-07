// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import java.util.concurrent.atomic.AtomicReference;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.Pigeon2.AxisDirection;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Drivetrain extends SubsystemBase {

//Drivetrain Variable
private boolean leftDriveInverted = false;
private boolean rightDriveInverted = true;

//Motor declaration
  private static CANSparkMax leftDriveMotor1,
   leftDriveMotor2,  
   leftDriveMotor3,
   rightDriveMotor1,
   rightDriveMotor2,
   rightDriveMotor3;

  MotorControllerGroup leftDriveMotors,
  rightDriveMotors;
//Gyro and drive declaration 
  private final Pigeon2 pigeon; 

  DifferentialDrive differentialDrive;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
//gyro config
    pigeon = new Pigeon2(Constants.pigeonIMU);

    pigeon.configMountPose(AxisDirection.NegativeY, AxisDirection.PositiveZ);
    resetGyro();
//Motor and motor group setup 
    leftDriveMotor1 = new CANSparkMax(Constants.leftDriveMotor1, MotorType.kBrushless);
    leftDriveMotor2 = new CANSparkMax(Constants.leftDriveMotor2, MotorType.kBrushless);
    leftDriveMotor3 = new CANSparkMax(Constants.leftDriveMotor3, MotorType.kBrushless);
    rightDriveMotor1 = new CANSparkMax(Constants.rightDriveMotor1, MotorType.kBrushless);
    rightDriveMotor2 = new CANSparkMax(Constants.rightDriveMotor2, MotorType.kBrushless);
    rightDriveMotor3 = new CANSparkMax(Constants.rightDriveMotor3, MotorType.kBrushless);

    leftDriveMotors = new MotorControllerGroup(leftDriveMotor1, leftDriveMotor2, leftDriveMotor3);
    rightDriveMotors = new MotorControllerGroup(rightDriveMotor1, rightDriveMotor2, rightDriveMotor3);
    
    differentialDrive = new DifferentialDrive(leftDriveMotors, rightDriveMotors);

    leftDriveMotors.setInverted(leftDriveInverted);
    rightDriveMotors.setInverted(rightDriveInverted);

//deadband
    differentialDrive.setDeadband(Constants.globalDeadband);
  }
  

//Drivetrain setup
public void GameDrive(double forward, double backward, double curve, boolean fastTurn){
  double netspeed = forward - backward;
  differentialDrive.curvatureDrive(netspeed, curve, fastTurn);
  differentialDrive.feed();
}

//Tankdrive 
  public void tankDrive(double leftSpeed, double rightSpeed){
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
    differentialDrive.feed();

  }
//Arcadedrive
  public void arcadeDrive(double speed, double rotation){
    differentialDrive.arcadeDrive(speed, rotation);
    differentialDrive.feed();

}

//Stop drivetrain
  public void stopDrivetrain(){
    differentialDrive.arcadeDrive(0, 0);
    
    leftDriveMotor1.setIdleMode(IdleMode.kBrake);
    leftDriveMotor2.setIdleMode(IdleMode.kBrake);
    leftDriveMotor3.setIdleMode(IdleMode.kBrake);
    rightDriveMotor1.setIdleMode(IdleMode.kBrake);
    rightDriveMotor2.setIdleMode(IdleMode.kBrake);
    rightDriveMotor3.setIdleMode(IdleMode.kBrake);

    Timer.delay(0.5);


    rightDriveMotor1.setIdleMode(IdleMode.kCoast);
    rightDriveMotor2.setIdleMode(IdleMode.kCoast);
    rightDriveMotor3.setIdleMode(IdleMode.kCoast);
    leftDriveMotor1.setIdleMode(IdleMode.kCoast);
    leftDriveMotor2.setIdleMode(IdleMode.kCoast);
    leftDriveMotor3.setIdleMode(IdleMode.kCoast);

  }

//Encoders
  public double getLeftDrive1Pos(){
    return leftDriveMotor1.getEncoder().getPosition();
  }

  public double getLeftDrive2Pos(){
    return leftDriveMotor2.getEncoder().getPosition();
  }

  public double getLeftDrive3Pos(){
    return leftDriveMotor3.getEncoder().getPosition();
  }
  
  public double getRightDrive1Pos(){
    return rightDriveMotor1.getEncoder().getPosition();
  }

  public double getRightDrive2Pos(){
    return rightDriveMotor2.getEncoder().getPosition();
  }

  public double getRightDrive3Pos(){
    return rightDriveMotor3.getEncoder().getPosition();
  }

  
  public void resetEncoders(){
    leftDriveMotor1.getEncoder().setPosition(0);
    leftDriveMotor2.getEncoder().setPosition(0);
    leftDriveMotor3.getEncoder().setPosition(0);
    rightDriveMotor1.getEncoder().setPosition(0);
    rightDriveMotor2.getEncoder().setPosition(0);
    rightDriveMotor3.getEncoder().setPosition(0);
  }
//Distance in ft ADJUST FOR WHEELS
  public double getDistanceFeet(){ 
    double avgDistanceInRotations = (leftDriveMotor1.getEncoder().getPosition()
      + leftDriveMotor2.getEncoder().getPosition()
      + leftDriveMotor3.getEncoder().getPosition()
      + -rightDriveMotor1.getEncoder().getPosition()
      + -rightDriveMotor2.getEncoder().getPosition()
      + -rightDriveMotor3.getEncoder().getPosition())

      / 6.0;

    return 0.183333 * avgDistanceInRotations;
  }


//Distance From Target
public double distanceFromTagInFeet(){
  AtomicReference <Double> targetY = RobotContainer.yDistanceAim;  
  double tarY = targetY.get();
  double limelightAngleDeg = 17.0;//For aimming limelight
  double limelightHeightInch = 17.3;//For the aimming limelight
  double targetHeightInch = 10.0;
  //if(RobotContainer.mTurret.visionGamePipelineSwitch() == 0){
  // targetHeightInch = 10.0;
  //}else{
  // targetHeightInch = 20.0; 
 // }
  double angleToTagDeg = limelightAngleDeg + tarY;
  double angleToTagRad = angleToTagDeg * (3.14159 / 180.0);

  double distanceToTargetInches = (targetHeightInch - limelightHeightInch) / Math.tan(angleToTagRad);
  double distanceToTargetFeet = distanceToTargetInches / 12;

  return distanceToTargetFeet;
}


public double distanceFromTapeLowInFeet(){
  AtomicReference <Double> targetY = RobotContainer.yDistanceAim;  
  double tarY = targetY.get();
  double limelightAngleDeg = 17.0;//For aimming limelight
  double limelightHeightInch = 17.3;//For the aimming limelight
  double targetHeightInch = 22.5;
 
  double angleToTagDeg = limelightAngleDeg + tarY;
  double angleToTagRad = angleToTagDeg * (3.14159 / 180.0);

  double distanceToTargetInches = (targetHeightInch - limelightHeightInch) / Math.tan(angleToTagRad);
  double distanceToTargetFeet = distanceToTargetInches / 12;

  return distanceToTargetFeet;
}


public double distanceFromTapeHighInFeet(){
  AtomicReference <Double> targetY = RobotContainer.yDistanceAim;  
  double tarY = targetY.get();
  double limelightAngleDeg = 17.0;//For aimming limelight
  double limelightHeightInch = 17.3;//For the aimming limelight
  double targetHeightInch = 42.0;
 
  double angleToTagDeg = limelightAngleDeg + tarY;
  double angleToTagRad = angleToTagDeg * (3.14159 / 180.0);

  double distanceToTargetInches = (targetHeightInch - limelightHeightInch) / Math.tan(angleToTagRad);
  double distanceToTargetFeet = distanceToTargetInches / 12;

  return distanceToTargetFeet;
}

public double distanceFromCargoInFeet(){
  AtomicReference <Double> targetY = RobotContainer.yDistanceGamePiece;  
  double tarY = targetY.get();
  double limelightAngleDeg = 17.0;//For aimming limelight
  double limelightHeightInch = 17.3;//For the aimming limelight
  double targetHeightInch = 2.0;
 
  double angleToTagDeg = limelightAngleDeg + tarY;
  double angleToTagRad = angleToTagDeg * (3.14159 / 180.0);

  double distanceToTargetInches = (targetHeightInch - limelightHeightInch) / Math.tan(angleToTagRad);
  double distanceToTargetFeet = distanceToTargetInches / 12;

  return distanceToTargetFeet;
}



//Motor getters
  public CANSparkMax getLeftDrive1(){
    return leftDriveMotor1;
  }

  public CANSparkMax getLeftDrive2(){
    return leftDriveMotor2;
  }

  public CANSparkMax getLeftDrive3(){
    return leftDriveMotor3;
  }
  
  public CANSparkMax getRightDrive1(){
    return rightDriveMotor1;
  }

  public CANSparkMax getRightDrive2(){
    return rightDriveMotor2;
  }

  public CANSparkMax getRightDrive3(){
    return rightDriveMotor3;
  } 

//Yaw is abt +Z
public double getYaw(){
  return pigeon.getYaw() % 360;
}
//Pitch is abt +Y
public double getPitch(){
  return pigeon.getPitch();
}
//Roll is abt +X
public double getRoll(){
  return pigeon.getRoll();
}
//Reset Gyro 
public ErrorCode resetGyro(){
  return pigeon.setYaw(0);
}


@Override
  public void periodic() {

    if(getPitch() > 2 || getPitch() < -2){
      
      boolean range = false;

      NetworkTableInstance.getDefault()
      .getTable("Shuffleboard")
      .getSubTable("Main")
      .getEntry("Is charging station in range?")
      .setBoolean(range);    
      
    }else{
    
      boolean range = true;
    
      NetworkTableInstance.getDefault()
      .getTable("Shuffleboard")
      .getSubTable("Main")
      .getEntry("Is charging station in range?")
      .setBoolean(range);       
     }
    
    }
 
}
