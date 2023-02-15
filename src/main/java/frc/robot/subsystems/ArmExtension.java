// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmExtension extends SubsystemBase {
//Motor declaration 

  public final CANSparkMax armExtendMotor;

  public ArmExtension() {

    armExtendMotor = new CANSparkMax(Constants.armMotor , MotorType.kBrushless);
    //Reset encoders before match
    resetArmEncoder();
    //Limiters
    armExtendMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    armExtendMotor.setSoftLimit(SoftLimitDirection.kReverse, 35);
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

  }

  public void setArm(double speed){
    armExtendMotor.set(speed);
  }

  public double getArmPos(){
    return armExtendMotor.getEncoder().getPosition();
  }
  
  public void resetArmEncoder(){
    armExtendMotor.getEncoder().setPosition(0);
  }

  public void stopArm(){
    armExtendMotor.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
