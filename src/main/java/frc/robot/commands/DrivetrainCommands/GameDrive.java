
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ButtonBind;
import frc.robot.RobotContainer;

public class GameDrive extends CommandBase {


  public GameDrive(){  
  addRequirements(RobotContainer.mDrivetrain);
}


  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  

    double forward = ButtonBind.driverController.getRightTriggerAxis();
    double backward = ButtonBind.driverController.getLeftTriggerAxis();
    double curve = ButtonBind.driverController.getLeftX();
    boolean fastTurn = ButtonBind.driverController.rightBumper().getAsBoolean();

    RobotContainer.mDrivetrain.GameDrive(forward, backward, curve, fastTurn);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
