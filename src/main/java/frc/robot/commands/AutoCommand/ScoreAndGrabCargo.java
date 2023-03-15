// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommand;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.BangPID.ArmLiftBangBang;
import frc.robot.commands.ClawCommands.ResetClaw;
import frc.robot.commands.VisionCommands.AutoPickup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreAndGrabCargo extends SequentialCommandGroup {
  /** Creates a new ScoreAndGrabCube. */
  public ScoreAndGrabCargo(AtomicReference<Double> cubeOrCone) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ParallelCommandGroup(new TurretToSetpoint(34),
    new ArmLiftBangBang(44)),
    new ArmExtendBangBang(80),
    new ResetClaw(),
    new WaitCommand(0.5),
    new ParallelCommandGroup(new ArmExtendBangBang(0),
    new TurretToSetpoint(0),
    new AutoPickup(cubeOrCone, cubeOrCone)));
  }
}
