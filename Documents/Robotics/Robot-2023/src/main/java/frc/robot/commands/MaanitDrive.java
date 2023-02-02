
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class MaanitDrive extends CommandBase {

 // private final Supplier<Double> forwardSpeed, backwardSpeed, rotation, multiplier;
 // private final Supplier<Boolean> isFastTurn;

  public MaanitDrive(){  
  addRequirements(RobotContainer.mDrivetrain);
}


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  

    double forward = RobotContainer.mButtonBind.getDriveRightTrigger();
    double backward = RobotContainer.mButtonBind.getDriveLeftTrigger();
    double curve = RobotContainer.mButtonBind.getDriveLeftX();
    boolean fastTurn = RobotContainer.mButtonBind.getDriveRightBumper();

    RobotContainer.mDrivetrain.MaanitDrive(forward, backward, curve, fastTurn);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}