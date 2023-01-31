// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.Pigeon2.AxisDirection;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//==============================ADD SHAFT ENCODING============================

public class Drivetrain extends SubsystemBase {

//Drivetrain Variable
private boolean leftDriveInverted = false;
private boolean rightDriveInverted = true;
private double deadband = Constants.globalDeadband;

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
  public final Pigeon2 pigeon; 

  DifferentialDrive differentialDrive;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
//gyro config
    pigeon = new Pigeon2(Constants.pigeonIMU);

    pigeon.configMountPose(AxisDirection.NegativeY, AxisDirection.PositiveZ);
    pigeon.setYaw(0);

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

  //PID Setup
  //pidControllerLeftDrive = leftDriveMotor1.getPIDController();

  }
  
//Drivetrain setup
  public  void setDrivetrain(double leftSpeed, double rightSpeed){
  leftDriveMotors.set(leftSpeed);
  rightDriveMotors.set(rightSpeed);
  }

  public void setDrivetrain(double leftSpeed, double rightSpeed, double multiplier){
    setDrivetrain(leftSpeed * multiplier, rightSpeed * multiplier);
  }

  public void setDrivetrain(double leftSpeed, double rightSpeed, double multiplier, boolean isDeadbandActive){
    if(isDeadbandActive){
      if(Math.abs(leftSpeed) > deadband) leftDriveMotors.set(leftSpeed);
      else leftDriveMotors.set(0);
      if(Math.abs(rightSpeed) > deadband) rightDriveMotors.set(rightSpeed);
      else rightDriveMotors.set(0);
    }else{
      setDrivetrain(leftSpeed, rightSpeed, multiplier);
    }
  }


//Tankdrive 
  public void tankDrive(double leftSpeed, double rightSpeed){
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }
//Arcadedrive
  public void arcadeDrive(double speed, double rotation){
    differentialDrive.tankDrive(speed, rotation);
}
//MaanitDrive
  public void setMaanitDrive( double forwardSpeed, double backwardSpeed, double rotation, boolean isFastTurn, double multiplier){

    forwardSpeed = multiplier * forwardSpeed;
    backwardSpeed = -1 * backwardSpeed * multiplier;
    double totalSpeed = forwardSpeed + backwardSpeed;

    if(Math.abs(totalSpeed) > deadband * multiplier){
      differentialDrive.curvatureDrive(totalSpeed, multiplier * -rotation, isFastTurn);
    }else if(Math.abs(totalSpeed) > 0.01 * multiplier){
      setDrivetrain(rotation, rotation, multiplier);
    }else if(isFastTurn){
      differentialDrive.curvatureDrive(0, -rotation * multiplier, true);
    } else {
      differentialDrive.curvatureDrive(0, -rotation * multiplier, true);
    }
  }

//StopDrivetrain  
  public void stopDrivetrain(){
    setDrivetrain(0, 0);
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
//Distance in ft 
  public double getDistanceFeet(){
    double avgDistanceInRotations = (leftDriveMotor1.getEncoder().getPosition() 
      + leftDriveMotor1.getEncoder().getPosition()
      + leftDriveMotor1.getEncoder().getPosition()
      + rightDriveMotor1.getEncoder().getPosition()
      + rightDriveMotor1.getEncoder().getPosition()
      + rightDriveMotor1.getEncoder().getPosition())

      / 6.0;

    double avgDistanceInRotationsOfShaft = avgDistanceInRotations / 14.17;
    return Math.PI * avgDistanceInRotationsOfShaft; //6 in wheels, so circumfrence in ft is pi
  }

//PID setup 

//public void setGyroPID()

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

}