// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.VisionCommands;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TurnTillTarget extends CommandBase {
  /** Creates a new TurnTillTag. */

  private double isTagVisibleEntry;
  private double turnSpeed;
  private boolean tagFound = false;
  private AtomicReference<Double> tagVis;

  public TurnTillTarget(double speed) {

    addRequirements(RobotContainer.mDrivetrain);
    turnSpeed = speed;
    tagVis = RobotContainer.aprilTagID;
    isTagVisibleEntry = tagVis.get();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mDrivetrain.arcadeDrive(0, turnSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    tagVis = RobotContainer.aprilTagID;
    isTagVisibleEntry = tagVis.get();

    RobotContainer.mDrivetrain.arcadeDrive(0, turnSpeed);

    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    while(Robot.isRedAlliance == true){
      if(isTagVisibleEntry >= 1 || isTagVisibleEntry <= 4){
        tagFound = true;
      }
    }
  
      while(Robot.isRedAlliance == false){
      if(isTagVisibleEntry >= 5 || isTagVisibleEntry <= 8){
          tagFound = true;
      }
    }
    return tagFound;
}
}
