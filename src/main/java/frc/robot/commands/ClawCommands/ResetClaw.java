// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ResetClaw extends CommandBase {
  /** Creates a new ResetCone. */
  public ResetClaw() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mClaw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.mClaw.setClaw(-0.7);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mClaw.stopClaw();
    RobotContainer.mClaw.resetClawEncoder();
    RobotContainer.mClaw.resetClawTurretEncoder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(RobotContainer.mButtonBind.clawLimit.getAsBoolean() == false){
      return true;
    }else{
    return false;
  }
  }
}
