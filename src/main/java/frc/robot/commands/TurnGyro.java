// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class TurnGyro extends CommandBase {
  /** Creates a new TurnGyro. */

  private double initialAngle, currentAngle, setpointAngle, turnSpeed = 0;
  private final Drivetrain mDrivetrain;

  
  public TurnGyro(double angle, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    setpointAngle = angle;
    mDrivetrain = RobotContainer.mDrivetrain;
    setpointAngle = angle;
    turnSpeed = speed;
    addRequirements(mDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialAngle = mDrivetrain.getYaw();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentAngle = mDrivetrain.getYaw();
    mDrivetrain.arcadeDrive(0, turnSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mDrivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs((initialAngle - currentAngle)) >= setpointAngle);
  }
}
