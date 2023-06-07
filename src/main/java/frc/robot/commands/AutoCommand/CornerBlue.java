// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommand;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.BangPID.ArmLiftBangBang;
import frc.robot.commands.BangPID.DriveDistance;
import frc.robot.commands.ClawCommands.IntakeIn;
import frc.robot.commands.ClawCommands.IntakeOut;
import frc.robot.commands.Setpoints.PickUpPos;
import frc.robot.commands.VisionCommands.PipelineSwitch;
import frc.robot.commands.VisionCommands.RunForTarT;
import frc.robot.commands.VisionCommands.RunForTarget;
import frc.robot.commands.VisionCommands.TurretAimTarget;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CornerBlue extends SequentialCommandGroup {
  /** Creates a new ScoreAndGrabCube. 
   * @param tapeOrTagy */
  public CornerBlue(AtomicReference<Double> cubeOrConea, AtomicReference<Double> tapeOrTagy) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ParallelCommandGroup(
    new TurretToSetpoint(-31),
    new ArmLiftBangBang(44)),
    new IntakeOut().withTimeout(0.5),
    new ParallelCommandGroup(new TurretToSetpoint(0),
    new ArmExtendBangBang(80),
    new ArmLiftBangBang(15)),
    new ArmLiftBangBang(0),
    new DriveDistance(0.75, 14),
   // new DriveDistance(0.5, 7),
    //new TurretAimTarget(tapeOrTagy).withTimeout(1),
   new ParallelDeadlineGroup( new RunForTarget(tapeOrTagy, tapeOrTagy),
//    new PipelineSwitch(2, 2).withTimeout(1),
    new IntakeIn()),
    new ParallelCommandGroup(new ArmLiftBangBang(33),
    new TurretToSetpoint(-33),
    new ArmExtendBangBang(0),
    new DriveDistance(-0.5, 3)),
    //new DriveDistance(-0.7, 20.5),
    //new TurretAimTarget(cubeOrConea).withTimeout(1.5),//),
   new RunForTarT(cubeOrConea, tapeOrTagy).withTimeout(3.1),
    new IntakeOut().withTimeout(0.6),
    new TurretToSetpoint(-28),
    new PickUpPos()); //,
    //new DriveDistance(0.7, 5)));
   // new RunForTarget(cubeOrCone, cubeOrCone)));
  }
}
