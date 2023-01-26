// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import com.ctre.phoenix.sensors.Pigeon2;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class climbStation extends CommandBase {
  /** Creates a new climbStation. */
  private Pigeon2 pigeon;
  private boolean isPitchFlat;
  private boolean firstStep;
  private boolean secondStep;

  public climbStation() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mDrivetrain);
    pigeon = RobotContainer.mDrivetrain.pigeon;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mDrivetrain.tankDrive(0, 0);
    pigeon.configMountPosePitch(0);
    isPitchFlat = false;
    firstStep = false;
    secondStep = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    RobotContainer.mDrivetrain.tankDrive(0.6, 0.6);

    if(Math.round(pigeon.getPitch()) >= 16 || Math.round(pigeon.getPitch()) >= 15){
        firstStep = true;
    } 

    if(firstStep == true){
      RobotContainer.mDrivetrain.tankDrive(0.3, 0.3);
    }

    if(Math.round(pigeon.getPitch()) <= 9 || Math.round(pigeon.getPitch()) <= 8){
    secondStep = true;
    }

    if(firstStep == true && secondStep == true){
      isPitchFlat = true;
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.getLeftDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive3().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive3().setIdleMode(IdleMode.kBrake);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isPitchFlat;
  }
}
