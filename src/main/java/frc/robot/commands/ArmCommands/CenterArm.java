// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class CenterArm extends CommandBase {


  public CenterArm() {
    addRequirements(RobotContainer.mTurret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(RobotContainer.mTurret.getTurretOnePos() < 0.0){
      RobotContainer.mTurret.setTurret(0.5);
    }else if(RobotContainer.mTurret.getTurretOnePos() > 0.0){
      RobotContainer.mTurret.setTurret(-0.5);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mTurret.stopTurret();
    RobotContainer.mTurret.resetTurretEncoder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.mButtonBind.turretBeam.getAsBoolean() == true;
  }
}
