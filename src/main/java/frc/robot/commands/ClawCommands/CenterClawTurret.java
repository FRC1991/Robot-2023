// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClawCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class CenterClawTurret extends CommandBase {
  /** Creates a new ResetClawTurret. */
  public CenterClawTurret() {
    addRequirements(RobotContainer.mClaw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(RobotContainer.mClaw.getClawTurretPos() < 0.0){
      RobotContainer.mClaw.setClawTurret(0.5);
    }else if(RobotContainer.mClaw.getClawTurretPos() > 0.0){
      RobotContainer.mClaw.setClawTurret(-0.5);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mClaw.stopClawTurret();
    RobotContainer.mClaw.resetClawTurretEncoder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.mButtonBind.clawTurretBeam.get() == true;
  }
}
