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

public class ArmExtension extends SubsystemBase {
//Motor declaration 

  private final CANSparkMax armExtendMotor;

  public ArmExtension() {

//Arm Motors setup
    armExtendMotor = new CANSparkMax(Constants.armMotorExtend , MotorType.kBrushless);
    

//invert motor
   
    armExtendMotor.setInverted(true);
//Reset encoders before match
    resetArmExtensionEncoder();
// Change idle Mode

  armExtendMotor.setIdleMode(IdleMode.kBrake);

//Limiters for extension
    armExtendMotor.setSoftLimit(SoftLimitDirection.kForward, 100);
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

    armExtendMotor.setSoftLimit(SoftLimitDirection.kReverse, 0);
    armExtendMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

//Limiters for raising
  
  }


//Extender Speed Set
  public void setArmExtend(double speed){
    armExtendMotor.setIdleMode(IdleMode.kCoast);

    armExtendMotor.set(speed);
  }

//Lifter Speed set
  

//get the pos of arm extender
  public double getArmExtendPos(){
    return armExtendMotor.getEncoder().getPosition();
  }

//get the pos of arm lifter
 

//reset arm extender pos 
  public void resetArmExtensionEncoder(){
    armExtendMotor.getEncoder().setPosition(0);
  }

//reset arm lift pos
  

//Stop the arm extension 
  public void stopArmExtension(){
    armExtendMotor.set(0);

    armExtendMotor.setIdleMode(IdleMode.kBrake);
  }

//Stop the arm lift
  
  
//Motor Getters 
public CANSparkMax getArmExtender(){
  return armExtendMotor;
}






//Distance in feet for arm extension
  public double getArmExtendDist(){
    double distInExtension = getArmExtendPos() * 0.1; // one rotation moves it 0.1 inches

    return distInExtension; 
  }

  
}
