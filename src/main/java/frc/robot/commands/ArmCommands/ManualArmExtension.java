// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ButtonBind;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ManualArmExtension extends CommandBase {
  /** Creates a new ManualArmExtension. */
private double speedSet;
  public ManualArmExtension() {
    addRequirements(RobotContainer.mArmExtension); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(ButtonBind.auxController.getLeftX() < Constants.globalDeadband){
      speedSet = 0;
    } else{
      speedSet = ButtonBind.auxController.getLeftX();
    }
    RobotContainer.mArmExtension.setArmExtend(speedSet * 0.6);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mArmExtension.stopArmExtension();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return false;
    }
  }
