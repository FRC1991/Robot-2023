// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ButtonBind;
import frc.robot.RobotContainer;

public class ManualArmLifter extends CommandBase {
  /** Creates a new ArmLifter. */
  private double speedSet;
  public ManualArmLifter(){//double speed) {
    addRequirements(RobotContainer.mArmLift);
    //speedSet = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
      speedSet = ButtonBind.auxController.getLeftY();
    

    RobotContainer.mArmLift.setArmLift(MathUtil.applyDeadband(speedSet * 0.6, 0.2));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mArmLift.stopArmLift();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    }
  } 
 

