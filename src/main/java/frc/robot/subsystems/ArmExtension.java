// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
//Speed Set
  public void setArmExtend(double speed){
    armExtendMotor.set(speed);
  }

//get the pos of arm
  public double getArmPos(){
    return armExtendMotor.getEncoder().getPosition();
  }
  //reset arm pos
  public void resetArmEncoder(){
    armExtendMotor.getEncoder().setPosition(0);
  }
//Stop the arm
  public void stopArmExtension(){
    armExtendMotor.set(0);
    //armExtendMotor.setIdleMode(IdleMode.kBrake);
  }

  //Distance in feet
  public double getArmExtendDist(){
    double distInRot = getArmPos() / 60; // replace with new gear ratio

    return Math.PI * distInRot; //replace with circumfrence 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Arm Extension Motors Active?", armExtendMotor.get());

  }

  
}
