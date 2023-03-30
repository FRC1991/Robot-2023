// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommand;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.BangPID.ArmLiftBangBang;
import frc.robot.commands.BangPID.DriveDistance;
import frc.robot.commands.ClawCommands.IntakeOut;
import frc.robot.commands.Setpoints.PickUpPos;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreAndDrive extends SequentialCommandGroup {
  /** Creates a new ScoreAndDrive. */
  public ScoreAndDrive() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ParallelCommandGroup(
      new TurretToSetpoint(32),
      new ArmLiftBangBang(44)),
      new IntakeOut().withTimeout(0.5),
      new PickUpPos(),
      new DriveDistance(0.8, 8));
  }
}
