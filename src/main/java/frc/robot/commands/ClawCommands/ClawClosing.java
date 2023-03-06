// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ClawClosing extends CommandBase {
  /** Creates a new ClawCone. */

  private double closingDistance;

  public ClawClosing(double clawClose) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mClaw);
    closingDistance = clawClose;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.mClaw.setClaw(0.8);
    RobotContainer.mClaw.getClawPos();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mClaw.stopClaw();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
     if((Math.abs(RobotContainer.mClaw.getClawPos()) >= closingDistance) == true){
      return true;
     }else{
      return false;
     }
  }
}
