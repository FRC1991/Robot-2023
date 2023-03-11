// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BangPID;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveDistanceBang extends CommandBase {

  private double driveDistance, driveSpeed = 0;

  public DriveDistanceBang(double distance, double speed) {
    driveDistance = distance;
    driveSpeed = speed;
    addRequirements(RobotContainer.mDrivetrain);
  }

  @Override
  public void initialize() {
    RobotContainer.mDrivetrain.resetEncoders();
  }

  @Override
  public void execute() {
    RobotContainer.mDrivetrain.arcadeDrive(driveSpeed, 0);
  }


  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return (Math.abs(RobotContainer.mDrivetrain.getDistanceFeet()) >= driveDistance);
  }
}