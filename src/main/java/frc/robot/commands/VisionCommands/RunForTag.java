// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.VisionCommands;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class RunForTag extends CommandBase {
  /** Creates a new RunForTag. */

  private double steerScale = Constants.visionConstant;
  private double adjustSteer = 0;
  private double speedSet, xSteer;
  private AtomicReference <Double> xSteering = RobotContainer.xDistanceAim;

  
  public RunForTag() {
    addRequirements(RobotContainer.mDrivetrain);
    xSteer = xSteering.get();
    speedSet = 0.90;

  }

  public RunForTag(double speed) {

    addRequirements(RobotContainer.mDrivetrain);
    speedSet = speed;
    xSteer = xSteering.get();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    RobotContainer.mButtonBind.singleDriveVibrate();
    RobotContainer.mButtonBind.singleAuxVibrate();

    NetworkTableInstance.getDefault()
        .getTable("Shuffleboard")
        .getSubTable("Main")
        .getEntry("Is Chasing Tag")
        .setBoolean(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // if target is off by more than 1 degree, adjust steering, otherwise, do nothing
    // note that this is a very rough approximation, and may need to be adjusted
    // multiplying by 0.015 to normalize the degree value to between -1 and 1
    if (xSteer > 0.2) {
      adjustSteer = xSteer * 0.015;
      adjustSteer = adjustSteer * steerScale;
    } else if (xSteer < -0.2) {
      adjustSteer = xSteer * 0.015;
      adjustSteer = adjustSteer * steerScale;
    } else {
      steerScale = 0;
    }

    RobotContainer.mDrivetrain.arcadeDrive(speedSet, adjustSteer);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    NetworkTableInstance.getDefault()
        .getTable("Shuffleboard")
        .getSubTable("Main")
        .getEntry("Is Chasing Tag")
        .setBoolean(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.mDrivetrain.distanceFromTargetInFeet() <= 1;
  }
}
