// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BangPID;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ArmLiftBangBang extends CommandBase {
  /** Creates a new TurretToSetpoint. */
  double targetPos, speed, initPos, currentPos;

  public ArmLiftBangBang(double encoderValue) {
    addRequirements(RobotContainer.mArmLift);
    targetPos = encoderValue;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initPos = RobotContainer.mArmLift.getArmLiftTwoPos();
    currentPos = initPos;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPos = RobotContainer.mArmLift.getArmLiftTwoPos();
    if(RobotContainer.mArmLift.getArmLiftTwoPos() > targetPos){
      RobotContainer.mArmLift.setArmLift(-0.6);
    }else if(RobotContainer.mArmLift.getArmLiftTwoPos() < targetPos){
      RobotContainer.mArmLift.setArmLift(0.6);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mArmLift.stopArmLift();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.round(targetPos) == Math.round(RobotContainer.mArmLift.getArmLiftTwoPos());
  }
}
