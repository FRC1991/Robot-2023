// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SlowDown extends CommandBase {
  /** Creates a new SlowDown. */
  public SlowDown() {
    addRequirements(RobotContainer.mDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Slow Down Mode Active")
    .setBoolean(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.mDrivetrain.getLeftDrive1().setSmartCurrentLimit(10);
    RobotContainer.mDrivetrain.getLeftDrive2().setSmartCurrentLimit(10);
    RobotContainer.mDrivetrain.getLeftDrive3().setSmartCurrentLimit(10);
    RobotContainer.mDrivetrain.getRightDrive1().setSmartCurrentLimit(10);
    RobotContainer.mDrivetrain.getRightDrive2().setSmartCurrentLimit(10);
    RobotContainer.mDrivetrain.getRightDrive3().setSmartCurrentLimit(10);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Slow Down Mode Active")
    .setBoolean(false);

    RobotContainer.mDrivetrain.getLeftDrive1().setSmartCurrentLimit(80, 20);
    RobotContainer.mDrivetrain.getLeftDrive2().setSmartCurrentLimit(80, 20);
    RobotContainer.mDrivetrain.getLeftDrive3().setSmartCurrentLimit(80, 20);
    RobotContainer.mDrivetrain.getRightDrive1().setSmartCurrentLimit(80, 20);
    RobotContainer.mDrivetrain.getRightDrive2().setSmartCurrentLimit(80, 20);
    RobotContainer.mDrivetrain.getRightDrive3().setSmartCurrentLimit(80, 20);


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
