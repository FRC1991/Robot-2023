// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.BangPID.DriveDistancePID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveDistance extends SequentialCommandGroup {
  /** Creates a new DriveDistance. */
  public DriveDistance(double targetDist) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new InstantCommand(() -> RobotContainer.mDrivetrain.resetEncoders()),
    new DriveDistancePID(targetDist, RobotContainer.mDrivetrain)
    //new BrakeMode()
    
    );
  }
}
