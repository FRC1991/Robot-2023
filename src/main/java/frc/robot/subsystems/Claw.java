// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
//Motor declaration 

  private final  CANSparkMax clawMotor, clawTurretMotor;

  public Claw() {
    clawMotor = new CANSparkMax(Constants.clawMotor , MotorType.kBrushless);
    clawTurretMotor = new CANSparkMax(Constants.clawTurretMotor , MotorType.kBrushless);

//Reset Encoders before match
   resetClawEncoder();
   resetClawTurretEncoder();

//Set idle mode
    clawTurretMotor.setIdleMode(IdleMode.kBrake);
    clawMotor.setIdleMode(IdleMode.kBrake);
// Current limiter for neo 550
    clawMotor.setSmartCurrentLimit(30, 45);

//Limiter for claw
    clawMotor.setSoftLimit(SoftLimitDirection.kForward, 35);
   clawMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    clawMotor.setSoftLimit(SoftLimitDirection.kReverse, -20);
    clawMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

//Limiter for claw turret   
    clawTurretMotor.setSoftLimit(SoftLimitDirection.kForward, 18);//check how many rotations
    clawTurretMotor.enableSoftLimit(SoftLimitDirection.kForward, false);

    clawTurretMotor.setSoftLimit(SoftLimitDirection.kReverse, 18);//check how many rotations
    clawTurretMotor.enableSoftLimit(SoftLimitDirection.kReverse, false);


  }
  
//Claw Motor values
  public void setClaw(double speed){
    clawMotor.setIdleMode(IdleMode.kCoast);

    clawMotor.set(speed);
  }

//Claw turret Motor values
public void setClawTurret( double speed){
  clawTurretMotor.setIdleMode(IdleMode.kCoast);
  clawTurretMotor.set(speed);
}

//Get claw Pos
  public double getClawPos(){
    return clawMotor.getEncoder().getPosition();
  }

//get claw turret pos
  public double getClawTurretPos(){
    return clawTurretMotor.getEncoder().getPosition();
  }  

 //Reset claw encoder  
  public void resetClawEncoder(){
    clawMotor.getEncoder().setPosition(0);
  }

 //Reset claw turret encoder 
  public void resetClawTurretEncoder(){
    clawTurretMotor.getEncoder().setPosition(0);
  }

//Stop claw   
  public void stopClaw(){
    clawMotor.setIdleMode(IdleMode.kBrake);
    clawMotor.set(0);
  }

//Stop claw turret 
  public void stopClawTurret(){
    clawTurretMotor.setIdleMode(IdleMode.kBrake);
    clawTurretMotor.set(0);
  }

  //Motor Getters

public CANSparkMax getClawMotor(){
    return clawMotor;
  }

  public CANSparkMax getClawTurretMotor(){
    return clawTurretMotor;
  }


}
