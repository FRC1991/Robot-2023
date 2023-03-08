// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ArmLiftBangBang extends CommandBase {
  /** Creates a new ArmLiftBangBang. */
  private double targetPos, speedSet, initPos, currentPos;

  public ArmLiftBangBang(double altEncoderValue, double speed) {
addRequirements(RobotContainer.mArm);
  targetPos = altEncoderValue;
  speed = speedSet;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initPos = RobotContainer.mArm.getArmLiftOnePos();
    currentPos = initPos;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPos = RobotContainer.mArm.getArmLiftOnePos();
    if(currentPos > targetPos){
      RobotContainer.mArm.setArmLift(speedSet);
    }else if(currentPos < targetPos){
      RobotContainer.mArm.setArmLift(-speedSet);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mArm.stopArmLift();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.round(targetPos) == Math.round(currentPos);
  }
}
