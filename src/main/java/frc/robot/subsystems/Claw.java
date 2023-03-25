// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
//Motor declaration 

  private final  CANSparkMax clawMotor;

  public Claw() {
    clawMotor = new CANSparkMax(Constants.clawMotor , MotorType.kBrushless);

//Reset Encoders before match
   resetClawEncoder();

//Set idle mode
    clawMotor.setIdleMode(IdleMode.kCoast);
// Current limiter 
    clawMotor.setSmartCurrentLimit(30, 45, 2000);

  }
  
//Claw Motor values
  public void setClaw(double speed){
    clawMotor.setIdleMode(IdleMode.kCoast);

    clawMotor.set(speed);
  }




//Get claw Pos
  public double getClawPos(){
    return clawMotor.getEncoder().getPosition();
  }


 //Reset claw encoder  
  public void resetClawEncoder(){
    clawMotor.getEncoder().setPosition(0);
  }


//Stop claw   
  public void stopClaw(){
    clawMotor.setIdleMode(IdleMode.kBrake);
    clawMotor.set(0);
  }


  //Motor Getters

public CANSparkMax getClawMotor(){
    return clawMotor;
  }

  


}
