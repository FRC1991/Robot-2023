// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommand;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.ArmHomePos;
import frc.robot.commands.ArmCommands.TurretToSetpoint;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.BangPID.ArmLiftBangBang;
import frc.robot.commands.ClawCommands.ResetClaw;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnArmScore extends SequentialCommandGroup {
  /** Creates a new TurnArmScore. */
  public TurnArmScore() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new TurretToSetpoint(32),
    new ArmLiftBangBang(35),
    new ArmExtendBangBang(130),
    new ResetClaw(),
    new ArmHomePos()
    );
  }
}
