// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
//Motor declaration 

  private final  CANSparkMax clawMotor, clawTurretMotor;

  public Claw() {
    clawMotor = new CANSparkMax(Constants.clawMotor , MotorType.kBrushed);
    clawTurretMotor = new CANSparkMax(Constants.clawTurretMotor , MotorType.kBrushless);

//Reset Encoders before match
   resetClawEncoder();
   resetClawTurretEncoder();

//Limiter for claw
    clawMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    clawMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    clawMotor.setSoftLimit(SoftLimitDirection.kReverse, 35);//check how many rotations
    clawMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

//Limiter for claw turret   
    clawTurretMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    clawTurretMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    clawTurretMotor.setSoftLimit(SoftLimitDirection.kReverse, 35);//check how many rotations
    clawTurretMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);


  }
  
//Claw Motor values
  public void setClaw(double speed){
    clawMotor.set(speed);
  }

//Claw turret Motor values
public void setClawTurret( double speed){
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
    clawMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192).setPosition(0);
  }

 //Reset claw turret encoder 
  public void resetClawTurretEncoder(){
    clawTurretMotor.getEncoder().setPosition(0);
  }

//Stop claw   
  public void stopClaw(){
    clawMotor.set(0);
  }

//Stop claw turret 
  public void stopClawTurret(){
    clawTurretMotor.set(0);

    clawTurretMotor.setIdleMode(IdleMode.kBrake);
    
    Timer.delay(0.5);

    clawTurretMotor.setIdleMode(IdleMode.kCoast);
  }


}
