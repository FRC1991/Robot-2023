// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ManualClaw extends CommandBase {
  /** Creates a new ManualClaw. */

  private boolean forwardBool, backBool;
             
  public ManualClaw() {
    addRequirements(RobotContainer.mClaw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    forwardBool = RobotContainer.mButtonBind.getDriveRightBumper();
    backBool = RobotContainer.mButtonBind.getDriveLeftBumper();

    if(forwardBool== true){
      RobotContainer.mClaw.setClaw(1);
    }else if(backBool == true){
      RobotContainer.mClaw.setClaw(-1);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mClaw.stopClaw();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
