// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Setpoints;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.BangPID.ArmLiftBangBang;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmHomePos extends ParallelCommandGroup {
  /** Creates a new ArmHomePos. */
  public ArmHomePos() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new TurretToSetpoint(0),
    new ArmExtendBangBang(0),
    new ArmLiftBangBang(0)
    );
  }
}
