// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RotateClawTurret extends CommandBase {
  /** Creates a new RotateClawTurret. */


  private double speedSet;
  public RotateClawTurret(double speed) {
    addRequirements(RobotContainer.mClaw);
    speedSet = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //double forSpeed = ButtonBind.auxController.getLeftTriggerAxis();
    //double backSpeed = ButtonBind.auxController.getRightTriggerAxis();
    //double netSpeed = forSpeed - backSpeed;
    RobotContainer.mClaw.setClawTurret(speedSet);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mClaw.stopClawTurret();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
