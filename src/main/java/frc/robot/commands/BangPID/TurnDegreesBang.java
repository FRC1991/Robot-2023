// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BangPID;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TurnDegreesBang extends CommandBase {
  /** Creates a new DriveDistancer. */
  private double driveDistance, driveSpeed = 0;
  public TurnDegreesBang(double speed, double degrees) {
    addRequirements(RobotContainer.mDrivetrain);
    driveSpeed = speed;
    driveDistance = degrees;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mDrivetrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.mDrivetrain.arcadeDrive(0, driveSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(RobotContainer.mDrivetrain.getYaw()) >= driveDistance);
  }
}

