// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
//Motor declaration 

  private final CANSparkMax armExtendMotor, armLiftMotor;

  private SparkMaxPIDController armExtendPID, armLiftMotorPID;

  public Arm() {

//Arm Motors setup
    armExtendMotor = new CANSparkMax(Constants.armMotorExtend , MotorType.kBrushless);
    armLiftMotor = new CANSparkMax(Constants.armLiftMotor, MotorType.kBrushless);
    
//Reset encoders before match
    resetArmExtensionEncoder();
    resetArmLiftEncoder();
    
//Limiters for extension
    armExtendMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    armExtendMotor.setSoftLimit(SoftLimitDirection.kReverse, 35);
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

//Limiters for raising
    armLiftMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    armLiftMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    armLiftMotor.setSoftLimit(SoftLimitDirection.kReverse, 35);
    armLiftMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

  
//Arm PID Motor setup
    armExtendPID = armExtendMotor.getPIDController();
    armLiftMotorPID = armLiftMotor.getPIDController();

//PID Values for extender
    armExtendPID.setP(Constants.kArmExtendP);
    armExtendPID.setI(Constants.kArmExtendI);
    armExtendPID.setD(Constants.kArmExtendD);
    armExtendPID.setOutputRange(Constants.kArmExtendMinOut, Constants.kArmExtendMaxOut);

//PID values for Lifter
    armLiftMotorPID.setP(Constants.kArmLiftP);
    armLiftMotorPID.setI(Constants.kArmLiftI);
    armLiftMotorPID.setD(Constants.kArmLiftD);
    armLiftMotorPID.setOutputRange(Constants.kArmLiftMinOut, Constants.kArmLiftMaxOut);
  
  }

//PID Setup
  public void setArmExtendPID(double armExtenderPos){
    armExtendPID.setReference(armExtenderPos, CANSparkMax.ControlType.kPosition);
  }

  public void setArmLiftPID(double armLiftPos){
    armLiftMotorPID.setReference(armLiftPos, CANSparkMax.ControlType.kPosition);
  }

//Extender Speed Set
  public void setArmExtend(double speed){
    armExtendMotor.set(speed);
  }

//Lifter Speed set
   public void setArmLift(double speed){
    armLiftMotor.set(speed);
  }

//get the pos of arm extender
  public double getArmExtendPos(){
    return armExtendMotor.getEncoder().getPosition();
  }

//get the pos of arm lifter
  public double getArmLiftPos(){
    return armLiftMotor.getEncoder().getPosition();
  }

//reset arm extender pos 
  public void resetArmExtensionEncoder(){
    armExtendMotor.getEncoder().setPosition(0);
  }

//reset arm lift pos
  public void resetArmLiftEncoder(){
    armLiftMotor.getEncoder().setPosition(0);
  }

//Stop the arm extension 
  public void stopArmExtension(){
    armExtendMotor.set(0);
    //armExtendMotor.setIdleMode(IdleMode.kBrake);
  }

//Stop the arm lift
  public void stopArmLift(){
    armLiftMotor.set(0);
  }


//Distance in feet for arm extension
  public double getArmExtendDist(){
    double distInExtension = getArmExtendPos() / 60; // replace with new gear ratio

    return Math.PI * distInExtension; //replace with circumfrence 
  }

//Distance in feet for an arm lifter
  public double getArmLiftDist(){
    double distForLift = getArmLiftPos() / 60; //replace with new gear ratio

    return Math.PI * distForLift;
  }

  
}
