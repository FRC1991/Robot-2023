// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnDegree extends SequentialCommandGroup {
  /** Creates a new TurnDegree. */
  public TurnDegree(double targetDeg) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new InstantCommand(() -> RobotContainer.mDrivetrain.resetGyro()),
      new InstantCommand(()-> RobotContainer.mDrivetrain.getLeftDrive1().setOpenLoopRampRate(0)),
      new InstantCommand(()-> RobotContainer.mDrivetrain.getLeftDrive2().setOpenLoopRampRate(0)),
      new InstantCommand(()-> RobotContainer.mDrivetrain.getLeftDrive3().setOpenLoopRampRate(0)),
      new InstantCommand(()-> RobotContainer.mDrivetrain.getRightDrive1().setOpenLoopRampRate(0)),
      new InstantCommand(()-> RobotContainer.mDrivetrain.getRightDrive2().setOpenLoopRampRate(0)),
      new InstantCommand(()-> RobotContainer.mDrivetrain.getRightDrive3().setOpenLoopRampRate(0)),
      new TurnDegreesPID(targetDeg, RobotContainer.mDrivetrain));
  }
}
