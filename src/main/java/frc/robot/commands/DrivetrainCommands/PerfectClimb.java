// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class PerfectClimb extends CommandBase {
  /** Creates a new PerfectClimb. */

  private double areaOfTagSuplier;
  private AtomicReference<Double> areaOfTagSup;
  private NetworkTable aimmingNT;

  
  public PerfectClimb(AtomicReference<Double> areaOfTag) {
    addRequirements(RobotContainer.mDrivetrain);
    areaOfTagSup = areaOfTag;
    areaOfTagSuplier = areaOfTagSup.get();
    aimmingNT = NetworkTableInstance.getDefault().getTable("limelight-cargo");


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    aimmingNT.getEntry("pipeline").setNumber(3);
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Climb Active")
    .setBoolean(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    areaOfTagSuplier = areaOfTagSup.get();
    if(areaOfTagSuplier < 0.3){
    RobotContainer.mDrivetrain.arcadeDrive(0.35, 0);
    }else if(areaOfTagSuplier < 0.4){
    RobotContainer.mDrivetrain.arcadeDrive(0.3, 0);
    }else if(areaOfTagSuplier < 0.5){
    RobotContainer.mDrivetrain.arcadeDrive(0.25, 0);
    }else if(areaOfTagSuplier < 0.6){
    RobotContainer.mDrivetrain.arcadeDrive(0.20, 0);
    }else if(areaOfTagSuplier < 0.7){
   RobotContainer.mDrivetrain.arcadeDrive(0.15, 0);
    }   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    aimmingNT.getEntry("pipeline").setNumber(0);
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Climb Active")
    .setBoolean(false);
    RobotContainer.mDrivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(areaOfTagSuplier >= 1){
    return true;
    }else{
      return false;
    }
  }
}
