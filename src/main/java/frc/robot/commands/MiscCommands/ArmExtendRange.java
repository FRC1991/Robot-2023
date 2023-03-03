// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.MiscCommands;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.DrivetrainCommands.DriveDistancePID;
import frc.robot.commands.VisionCommands.RunForTarget;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmExtendRange extends SequentialCommandGroup {
  /** Creates a new ArmExtendRange. */
  double speedSet, turretSpeedSet;
  public ArmExtendRange(double speed, double turretSpeedSet, AtomicReference<Double> pickTagPipeline) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(new DriveDistancePID(-2 * speed, null),

    new TurretToSetpoint(turretSpeedSet, -180),

    new RunForTarget(pickTagPipeline));
}
}
