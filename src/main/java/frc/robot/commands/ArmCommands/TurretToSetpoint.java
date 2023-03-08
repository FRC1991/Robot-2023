// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TurretToSetpoint extends CommandBase {
  /** Creates a new TurretToSetpoint. */
  double targetPos, speed, initPos, currentPos;

  public TurretToSetpoint(double encoderValue) {
    addRequirements(RobotContainer.mTurret);
    targetPos = encoderValue;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initPos = RobotContainer.mTurret.getTurretOnePos();
    currentPos = initPos;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPos = RobotContainer.mTurret.getTurretOnePos();
    if(RobotContainer.mTurret.getTurretOnePos() > targetPos){
      RobotContainer.mTurret.setTurret(-0.4);
    }else if(RobotContainer.mTurret.getTurretOnePos() < targetPos){
      RobotContainer.mTurret.setTurret(0.4);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mTurret.stopTurret();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.round(targetPos) == Math.round(RobotContainer.mTurret.getTurretOnePos());
  }
}