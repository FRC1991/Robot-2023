// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
//Motor declaration 

  private final CANSparkMax armExtendMotor, armLiftMotor1, armLiftMotor2;
  private final MotorControllerGroup armLiftMotors;

  public Arm() {

//Arm Motors setup
    armExtendMotor = new CANSparkMax(Constants.armMotorExtend , MotorType.kBrushless);
    armLiftMotor1 = new CANSparkMax(Constants.armLiftMotor1, MotorType.kBrushless);
    armLiftMotor2 = new CANSparkMax(Constants.armLiftMotor2, MotorType.kBrushless);

    armLiftMotors = new MotorControllerGroup(armLiftMotor1, armLiftMotor2);
//invert motor
    armLiftMotor1.setInverted(true);
    armLiftMotor2.setInverted(true);
    armExtendMotor.setInverted(true);
//Reset encoders before match
    resetArmExtensionEncoder();
    resetArmLiftEncoder();
// Change idle Mode
  armLiftMotor1.setIdleMode(IdleMode.kBrake);
  armLiftMotor2.setIdleMode(IdleMode.kBrake);
  armExtendMotor.setIdleMode(IdleMode.kBrake);

//Limiters for extension
    armExtendMotor.setSoftLimit(SoftLimitDirection.kForward, 100);
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    armExtendMotor.setSoftLimit(SoftLimitDirection.kReverse, 0);
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

//Limiters for raising
    armLiftMotor1.setSoftLimit(SoftLimitDirection.kForward, 44);
    armLiftMotor1.enableSoftLimit(SoftLimitDirection.kForward, true);

    armLiftMotor1.setSoftLimit(SoftLimitDirection.kReverse, 0);
    armLiftMotor1.enableSoftLimit(SoftLimitDirection.kReverse, true);

    armLiftMotor2.setSoftLimit(SoftLimitDirection.kForward, 44);
    armLiftMotor2.enableSoftLimit(SoftLimitDirection.kForward, true);

    armLiftMotor2.setSoftLimit(SoftLimitDirection.kReverse, 0);
    armLiftMotor2.enableSoftLimit(SoftLimitDirection.kReverse, true);

  
  }


//Extender Speed Set
  public void setArmExtend(double speed){
    armExtendMotor.setIdleMode(IdleMode.kCoast);

    armExtendMotor.set(speed);
  }

//Lifter Speed set
   public void setArmLift(double speed){
    armLiftMotor1.setIdleMode(IdleMode.kCoast);
    armLiftMotor2.setIdleMode(IdleMode.kCoast);
    
    armLiftMotors.set(speed);
  }

//get the pos of arm extender
  public double getArmExtendPos(){
    return armExtendMotor.getEncoder().getPosition();
  }

//get the pos of arm lifter
  public double getArmLiftOnePos(){
    return armLiftMotor1.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192).getPosition();
  }

  public double getArmLiftTwoPos(){
    return armLiftMotor2.getEncoder().getPosition();
  }

//reset arm extender pos 
  public void resetArmExtensionEncoder(){
    armExtendMotor.getEncoder().setPosition(0);
  }

//reset arm lift pos
  public void resetArmLiftEncoder(){
    armLiftMotor1.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192).getPosition();
    armLiftMotor2.getEncoder().setPosition(0);

  }

//Stop the arm extension 
  public void stopArmExtension(){
    armExtendMotor.set(0);

    armExtendMotor.setIdleMode(IdleMode.kBrake);
  }

//Stop the arm lift
  public void stopArmLift(){
    armLiftMotors.set(0);

    armLiftMotor1.setIdleMode(IdleMode.kBrake);
    armLiftMotor2.setIdleMode(IdleMode.kBrake);
  }
  
//Motor Getters 
public CANSparkMax getArmExtender(){
  return armExtendMotor;
}

public CANSparkMax getArmLifterOne(){
  return armLiftMotor1;
}

public CANSparkMax getArmLifterTwo(){
  return armLiftMotor2;
}


//Distance in feet for arm extension
  public double getArmExtendDist(){
    double distInExtension = getArmExtendPos() * 0.1; // one rotation moves it 0.1 inches

    return distInExtension; 
  }

  
}
