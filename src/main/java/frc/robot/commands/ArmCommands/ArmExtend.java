// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;


public class ArmExtend extends CommandBase {
  /** Creates a new ArmExtend. */



  public ArmExtend() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mArmExtension);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mArmExtension.resetArmEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double armExtensionSpeed = RobotContainer.mButtonBind.driveLeftX;

    RobotContainer.mArmExtension.setArmExtend(armExtensionSpeed * 0.5);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mArmExtension.stopArmExtension();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
