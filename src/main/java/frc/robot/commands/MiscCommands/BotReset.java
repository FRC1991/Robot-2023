// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.MiscCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.ArmCommands.CenterArm;
import frc.robot.commands.ArmCommands.ManualArmExtension;
import frc.robot.commands.ArmCommands.ManualArmLifter;
import frc.robot.commands.ClawCommands.CenterClawTurret;
import frc.robot.commands.ClawCommands.ResetClaw;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BotReset extends ParallelCommandGroup {
  /** Creates a new BotReset. */
  public BotReset() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ManualArmExtension(-0.3), 
    new ManualArmLifter(-0.3), 
    new CenterClawTurret(), 
    new CenterArm(),
    new ResetClaw(),
    new InstantCommand(() -> RobotContainer.mDrivetrain.resetGyro()),
    new InstantCommand(() -> RobotContainer.mDrivetrain.resetEncoders()));
  }
}
