// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
//Motor declaration 

  public final  CANSparkMax clawMotor, clawTurretMotor;

  public Claw() {
    clawMotor = new CANSparkMax(Constants.clawMotor , MotorType.kBrushless);
    clawTurretMotor = new CANSparkMax(Constants.clawTurretMotor , MotorType.kBrushless);
    //Reset Encoders before match
    resetClawEncoder();
    resetClawTurretEncoder();
    //Limiters
    clawMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    clawMotor.enableSoftLimit(SoftLimitDirection.kForward, true);//check if needed with limelight
    clawTurretMotor.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    clawTurretMotor.enableSoftLimit(SoftLimitDirection.kForward, true);//check if needed with limelight

  }
  
//Claw Motor values
  public void setClaw(double speed){
    clawMotor.set(speed);
  }

  public double getClawPos(){
    return clawMotor.getEncoder().getPosition();
  }

  public void resetClawEncoder(){
    clawMotor.getEncoder().setPosition(0);
  }

  public void stopClaw(){
    clawMotor.set(0);
  }

//Claw turret Motor values
  public void setClawTurret(double speed){
    clawTurretMotor.set(speed);
  }

  public double getClawTurretPos(){
    return clawTurretMotor.getEncoder().getPosition();
  }

  public void resetClawTurretEncoder(){
    clawTurretMotor.getEncoder().setPosition(0);
  }

  public void stopClawTurret(){
    clawTurretMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Claw Motors Active?", clawMotor.get());
    SmartDashboard.putNumber("Claw Turret Motors Active?", clawTurretMotor.get());

  }


}
