
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ButtonBind;
import frc.robot.RobotContainer;

public class MaanitDrive extends CommandBase {
  /** Creates a new MaanitDrive. */
  public MaanitDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftSpeed = ButtonBind.driverController.getLeftY();
     double rightSpeed = ButtonBind.driverController.getRightY();

    RobotContainer.mDrivetrain.tankDrive(leftSpeed, rightSpeed);

    System.out.println(RobotContainer.mDrivetrain.pigeon.getPitch());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.tankDrive(0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
