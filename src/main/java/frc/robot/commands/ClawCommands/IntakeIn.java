// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClawCommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class IntakeIn extends CommandBase {
  /** Creates a new IntakeIn. */
  public IntakeIn() {
    addRequirements(RobotContainer.mClaw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Intake in?")
    .setBoolean(true);
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.mClaw.setClaw(-0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Intake in?")
    .setBoolean(false);
    RobotContainer.mClaw.stopClaw();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.mButtonBind.intakeStop.getAsBoolean() == true;
  }
}
