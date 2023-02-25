// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ManualTurret extends CommandBase {

  private boolean speedSet, speed;
  
  public ManualTurret() {
    addRequirements(RobotContainer.mTurret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    speedSet = RobotContainer.mButtonBind.driveAButton.getAsBoolean();
    speed = RobotContainer.mButtonBind.driveAButton.getAsBoolean();

    if(speedSet = true){
      RobotContainer.mTurret.setTurret(0.25);
    }else if(speed = true){
      RobotContainer.mTurret.setTurret(-0.25);
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
    return false;
  }
}
