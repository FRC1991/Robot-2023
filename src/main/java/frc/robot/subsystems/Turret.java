// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
//Motor declaration 
  private final CANSparkMax turretMotor1, turretMotor2;
  private final MotorControllerGroup turretMotors;
  
  public Turret() {
    turretMotor1 = new CANSparkMax(Constants.turretMotor, MotorType.kBrushless); 
    turretMotor2 = new CANSparkMax(Constants.turretMotor, MotorType.kBrushless); 

    turretMotors = new MotorControllerGroup(turretMotor1, turretMotor2);

    //reset before match starts
    resetTurretEncoder();
    //Limiters
    turretMotor1.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    turretMotor1.enableSoftLimit(SoftLimitDirection.kForward, true);

    turretMotor2.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    turretMotor2.enableSoftLimit(SoftLimitDirection.kForward, true);


  }
//Turret speed set
  public void setTurret(double speed){
    turretMotors.set(speed);
  } 

//Get turret pos
  public double getTurretPos(){
    double turretPos = turretMotor1.getEncoder().getPosition() 
    + turretMotor2.getEncoder().getPosition() / 2.0;
    
    return turretPos;
  }

//Reset turret encoders
  public void resetTurretEncoder(){
    turretMotor1.getEncoder().setPosition(0);
    turretMotor2.getEncoder().setPosition(0);

  }

//Stop turret  
  public void stopTurret() {
    turretMotors.set(0);
    turretMotor1.setIdleMode(IdleMode.kBrake);
    turretMotor2.setIdleMode(IdleMode.kBrake);

  }

 
}
