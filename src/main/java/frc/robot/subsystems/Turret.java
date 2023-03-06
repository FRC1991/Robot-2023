// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
//Motor declaration 
  private final CANSparkMax turretMotor1, turretMotor2;
  private final MotorControllerGroup turretMotors;
  
  public Turret() {
    turretMotor1 = new CANSparkMax(Constants.turretMotor1, MotorType.kBrushless); 
    turretMotor2 = new CANSparkMax(Constants.turretMotor2, MotorType.kBrushless); 

    turretMotors = new MotorControllerGroup(turretMotor1, turretMotor2);

    //Idle mode
    turretMotor1.setIdleMode(IdleMode.kBrake);
    turretMotor2.setIdleMode(IdleMode.kBrake);

    //reset before match starts
    resetTurretEncoder();
    //Limiters
    turretMotor1.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    //turretMotor1.enableSoftLimit(SoftLimitDirection.kForward, true);
    
    turretMotor1.setSoftLimit(SoftLimitDirection.kReverse, 0);//check how many rotations
    //turretMotor1.enableSoftLimit(SoftLimitDirection.kReverse, true);
    
    turretMotor2.setSoftLimit(SoftLimitDirection.kForward, 35);//check how many rotations
    //turretMotor2.enableSoftLimit(SoftLimitDirection.kForward, true);

    turretMotor2.setSoftLimit(SoftLimitDirection.kReverse, 0);//check how many rotations
    //turretMotor2.enableSoftLimit(SoftLimitDirection.kReverse, true);
  }
//Turret speed set
  public void setTurret(double speed){
    turretMotor1.setIdleMode(IdleMode.kCoast);
    turretMotor2.setIdleMode(IdleMode.kCoast);
    if(speed > 0){
    turretMotor1.set(speed);
    turretMotor2.set(0);
    }else if(speed < 0){
    turretMotor1.set(0);
    turretMotor2.set(speed);
    }
  
  } 

//Get turret pos
public double getTurretOnePos(){
  return turretMotor1.getEncoder().getPosition();

}

public double getTurretTwoPos(){
  return turretMotor2.getEncoder().getPosition();

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
  
//Motor Getters 
public CANSparkMax getTurret1(){
  return turretMotor1;
}

public CANSparkMax getTurret2(){
  return turretMotor2;
}




//Auto pipeline switch
 // public double visionGamePipelineSwitch(){
  //  AtomicReference<Double> gamePipelinesTV = RobotContainer.gamePieceSeen;
  //  double gamePipePick = gamePipelinesTV.get();
  //  double whichPipeline = 0;
   // Timer.delay(3);

   // if(gamePipePick == 1){
   //   whichPipeline = 0;
   // }else{
   //   whichPipeline = 1;
   // }

  //  return whichPipeline;
 // }

  @Override
  public void periodic() {


    
      NetworkTableInstance.getDefault()
      .getTable("Shuffleboard")
      .getSubTable("Main")
      .getEntry("Turret Pos check for limiter")
      .setNumber(Math.abs(getTurretOnePos()));       
     }
    
    
 
}
