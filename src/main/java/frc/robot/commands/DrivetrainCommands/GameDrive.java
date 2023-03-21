

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ButtonBind;
import frc.robot.RobotContainer;

public class GameDrive extends CommandBase {

  DoubleSupplier maxSpeed;
  
  public GameDrive(DoubleSupplier maxSpeedSet){  
  addRequirements(RobotContainer.mDrivetrain);
  maxSpeed = maxSpeedSet;
}


  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
     // set motor profile to avoid tipping
  RobotContainer.mDrivetrain.getRightDrive1().setOpenLoopRampRate(1);
  RobotContainer.mDrivetrain.getRightDrive2().setOpenLoopRampRate(1);
  RobotContainer.mDrivetrain.getRightDrive3().setOpenLoopRampRate(1);
  RobotContainer.mDrivetrain.getLeftDrive1().setOpenLoopRampRate(1);
  RobotContainer.mDrivetrain.getLeftDrive2().setOpenLoopRampRate(1);
  RobotContainer.mDrivetrain.getLeftDrive3().setOpenLoopRampRate(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
 

    double forward = ButtonBind.driverController.getRightTriggerAxis();
    double backward = ButtonBind.driverController.getLeftTriggerAxis();
    double curve = ButtonBind.driverController.getLeftX();
    boolean fastTurn = ButtonBind.driverController.rightBumper().getAsBoolean();
    

    RobotContainer.mDrivetrain.GameDrive(forward * maxSpeed.getAsDouble(), backward * maxSpeed.getAsDouble(), -curve * maxSpeed.getAsDouble(), fastTurn);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}