// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
//Motor declaration 

  public final CANSparkMax armMotor;

  public Arm() {

    armMotor = new CANSparkMax(Constants.armMotor , MotorType.kBrushless);
    //Reset encoders before match
    resetArmEncoder();
    //Limiters
    armMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    armMotor.enableSoftLimit(SoftLimitDirection.kForward, true);//check if needed with limelight

  }

  public void setArm(double speed){
    armMotor.set(speed);
  }

  public double getArmPos(){
    return armMotor.getEncoder().getPosition();
  }
  
  public void resetArmEncoder(){
    armMotor.getEncoder().setPosition(0);
  }

  public void stopArm(){
    armMotor.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
