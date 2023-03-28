// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommand;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.ArmHomePos;
import frc.robot.commands.ArmCommands.PickUpPos;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.BangPID.ArmLiftBangBang;
import frc.robot.commands.BangPID.DriveDistance;
import frc.robot.commands.ClawCommands.IntakeIn;
import frc.robot.commands.ClawCommands.IntakeOut;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CornerAuto extends SequentialCommandGroup {
  /** Creates a new ScoreAndGrabCube. */
  public CornerAuto(AtomicReference<Double> cubeOrCone, AtomicReference<Double> tarArea) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ParallelCommandGroup(
    new TurretToSetpoint(32),
    new ArmLiftBangBang(44)),
    new IntakeOut().withTimeout(0.5),
    new PickUpPos(),
    new DriveDistance(0.8, 15),
    new IntakeIn().withTimeout(1.3),
    new ParallelCommandGroup(new ArmLiftBangBang(35),
    new TurretToSetpoint(36),
    new ArmExtendBangBang(0),
    new DriveDistance(-0.75, 15.6)),
    new IntakeOut().withTimeout(0.6),
    new ParallelCommandGroup(
    new ArmHomePos(),
    new DriveDistance(0.8, 5)));
   // new RunForTarget(cubeOrCone, cubeOrCone)));
  }
}
