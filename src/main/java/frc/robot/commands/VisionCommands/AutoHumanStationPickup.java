// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.VisionCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.ArmCommands.ArmHomePos;
import frc.robot.commands.BangPID.ArmExtendBangBang;
import frc.robot.commands.BangPID.ArmLiftBangBang;
import frc.robot.commands.BangPID.DriveDistance;
import frc.robot.commands.BangPID.TurnDegreesBang;
import frc.robot.commands.ClawCommands.IntakeIn;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoHumanStationPickup extends SequentialCommandGroup {
  /** Creates a new AutoHumanStationPickup. */
  public AutoHumanStationPickup() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ArmHomePos(),
      new PipelineSwitch(2,2),
      new TurnTillTarget(0.8),
      new RunForTarget(RobotContainer.xDistanceAim, RobotContainer.tagArea).withTimeout(3),
      new RunForTarget(RobotContainer.xDistanceAim, RobotContainer.tagArea).withTimeout(3),
      new ArmLiftBangBang(44),
      new ArmExtendBangBang(50),
      //new TurretAimTarget(RobotContainer.xDistanceGamePiece),
      new IntakeIn().withTimeout(5),
      new DriveDistance(-0.5, 2),
      new TurnDegreesBang(0.8, 180),
      new ArmHomePos()
    );
  }
}
