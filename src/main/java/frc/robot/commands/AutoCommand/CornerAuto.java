// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommand;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.PickUpSetpoint;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.BangPID.ArmLiftBangBang;
import frc.robot.commands.BangPID.DriveDistance;
import frc.robot.commands.ClawCommands.ResetClaw;
import frc.robot.commands.VisionCommands.RunForTarget;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CornerAuto extends SequentialCommandGroup {
  /** Creates a new ScoreAndGrabCube. */
  public CornerAuto(AtomicReference<Double> cubeOrCone, AtomicReference<Double> tarArea) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ParallelCommandGroup(new TurretToSetpoint(34),
    new ArmLiftBangBang(44)),
    new ArmExtendBangBang(100),
    new ResetClaw(),
    new ParallelCommandGroup(new ArmExtendBangBang(0),
    new TurretToSetpoint(0),
    new ParallelCommandGroup( new DriveDistance(0.7, 6),
    new PickUpSetpoint()),
    new RunForTarget(cubeOrCone, cubeOrCone)));
  }
}
