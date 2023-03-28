// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BangPID;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ArmExtendBangBang extends CommandBase {
  /** Creates a new TurretToSetpoint. */
  double targetPos, speed, initPos, currentPos;

  public ArmExtendBangBang(double encoderValue) {
    addRequirements(RobotContainer.mArmExtension);
    targetPos = encoderValue;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initPos = RobotContainer.mArmExtension.getArmExtendPos();
    currentPos = initPos;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPos = RobotContainer.mArmExtension.getArmExtendPos();
    if(RobotContainer.mArmExtension.getArmExtendPos() > targetPos){
      RobotContainer.mArmExtension.setArmExtend(-0.7);
    }else if(RobotContainer.mArmExtension.getArmExtendPos() < targetPos){
      RobotContainer.mArmExtension.setArmExtend(0.7);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mArmExtension.stopArmExtension();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.round(targetPos) == Math.round(RobotContainer.mArmExtension.getArmExtendPos());
  }
}
