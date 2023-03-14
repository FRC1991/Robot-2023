// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.VisionCommands;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class RunForTarget extends CommandBase {
  /** Creates a new RunForTag. */

  private double steerScale = Constants.visionConstant;
  private double adjustSteer = 0;
  private double speedSet, xSteer, stopDist, areaOfTargetSup; 
   private AtomicReference<Double> xSteerSup;


  
  public RunForTarget(AtomicReference <Double> xSteering, AtomicReference<Double> areaOfTarget) {
    addRequirements(RobotContainer.mDrivetrain);
    xSteerSup = xSteering;
    xSteer = xSteerSup.get();
    speedSet = 0.65;
    areaOfTargetSup = areaOfTarget.get();
    areaOfTargetSup = 0.35;

  }

  public RunForTarget(double speed, AtomicReference <Double> xSteering, double whenToStop) {

    addRequirements(RobotContainer.mDrivetrain);
    speedSet = speed;
    xSteerSup = xSteering;
    xSteer = xSteerSup.get();
    whenToStop = stopDist;
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    RobotContainer.mButtonBind.singleDriveVibrate();
    RobotContainer.mButtonBind.singleAuxVibrate();


    NetworkTableInstance.getDefault()
        .getTable("Shuffleboard")
        .getSubTable("Main")
        .getEntry("Is Chasing Target")
        .setBoolean(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  
    xSteer = xSteerSup.get();
    
    if(xSteer > 0.2){
      adjustSteer = xSteer * 0.015;
      adjustSteer = adjustSteer * steerScale;
    }else if(xSteer < -0.2){
      adjustSteer = xSteer * 0.015;
      adjustSteer = adjustSteer * steerScale;
    }else{
      adjustSteer = 0;
    }
    RobotContainer.mDrivetrain.arcadeDrive(speedSet, adjustSteer * -1000 * 1.65);//Fix later 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    NetworkTableInstance.getDefault()
        .getTable("Shuffleboard")
        .getSubTable("Main")
        .getEntry("Is Chasing Target")
        .setBoolean(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(areaOfTargetSup <= 0.35){
      return true;
    }else{
      return false;
    }
  }
}
