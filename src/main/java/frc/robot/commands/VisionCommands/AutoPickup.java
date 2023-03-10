// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.VisionCommands;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.ClawCommands.ResetClaw;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoPickup extends SequentialCommandGroup {
  /** Creates a new AutoArmExtension. */
  public AutoPickup(AtomicReference<Double> areaOfTar, AtomicReference<Double> xSteers) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new CenterAndRunForTarget(xSteers, areaOfTar),
    new ParallelCommandGroup( new ArmExtendBangBang(50),
    new ResetClaw()));
  }
}
