// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
//Motor declaration 
  public final CANSparkMax turretMotor;
  public Turret() {
    turretMotor = new CANSparkMax(Constants.turretMotor, MotorType.kBrushless); 
    //reset before match starts
    resetTurretEncoder();
    //Limiters
    turretMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    turretMotor.enableSoftLimit(SoftLimitDirection.kForward, true);//check if needed with limelight
  }
  
  public void setTurret(double speed){
    turretMotor.set(speed);
  } 

  public double getTurretPos(){
    return turretMotor.getEncoder().getPosition();
  }

  public void resetTurretEncoder(){
    turretMotor.getEncoder().setPosition(0);
  }

  public void stopTurret() {
    turretMotor.set(0);
  }


}