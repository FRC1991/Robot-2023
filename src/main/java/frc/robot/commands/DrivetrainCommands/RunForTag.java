// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class RunForTag extends CommandBase {
  /** Creates a new RunForTag. */

  private final double steerScale = Constants.visionConstant;
  private double adjustSteer = 0;
  private double speeder, xSteer;

  
  public RunForTag(double speed) {

    addRequirements(RobotContainer.mDrivetrain);
    speeder = speed;
    speed = 0.90;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
