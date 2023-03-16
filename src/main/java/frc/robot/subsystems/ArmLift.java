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


public class ArmLift extends SubsystemBase {
  /** Creates a new ArmLift. */
  private final CANSparkMax  armLiftMotor1, armLiftMotor2;
  private final MotorControllerGroup armLiftMotors;

  public ArmLift() {
    armLiftMotor1 = new CANSparkMax(Constants.armLiftMotor1, MotorType.kBrushless);
    armLiftMotor2 = new CANSparkMax(Constants.armLiftMotor2, MotorType.kBrushless);

    armLiftMotors = new MotorControllerGroup(armLiftMotor1, armLiftMotor2);

    armLiftMotor1.setInverted(true);
    armLiftMotor2.setInverted(true);

    resetArmLiftEncoder();

    armLiftMotor1.setIdleMode(IdleMode.kBrake);
    armLiftMotor2.setIdleMode(IdleMode.kBrake);
    
    armLiftMotor1.setSoftLimit(SoftLimitDirection.kForward, 44);
    armLiftMotor1.enableSoftLimit(SoftLimitDirection.kForward, true);

    armLiftMotor1.setSoftLimit(SoftLimitDirection.kReverse, 0);
    armLiftMotor1.enableSoftLimit(SoftLimitDirection.kReverse, true);

    armLiftMotor2.setSoftLimit(SoftLimitDirection.kForward, 44);
    armLiftMotor2.enableSoftLimit(SoftLimitDirection.kForward, true);

    armLiftMotor2.setSoftLimit(SoftLimitDirection.kReverse, 0);
    armLiftMotor2.enableSoftLimit(SoftLimitDirection.kReverse, true);
  }

  public void setArmLift(double speed){
    armLiftMotor1.setIdleMode(IdleMode.kCoast);
    armLiftMotor2.setIdleMode(IdleMode.kCoast);
    
    armLiftMotors.set(speed);
  }

  public double getArmLiftOnePos(){
    return armLiftMotor1.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192).getPosition();
  }

  public double getArmLiftTwoPos(){
    return armLiftMotor2.getEncoder().getPosition();
  }

  public void resetArmLiftEncoder(){
    armLiftMotor1.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192).getPosition();
    armLiftMotor2.getEncoder().setPosition(0);

  }
  public void stopArmLift(){
    armLiftMotors.set(0);

    armLiftMotor1.setIdleMode(IdleMode.kBrake);
    armLiftMotor2.setIdleMode(IdleMode.kBrake);
  }
  
  public CANSparkMax getArmLifterOne(){
    return armLiftMotor1;
  }
  
  public CANSparkMax getArmLifterTwo(){
    return armLiftMotor2;
  }
}
