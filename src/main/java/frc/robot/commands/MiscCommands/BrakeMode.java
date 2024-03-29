// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.MiscCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class BrakeMode extends CommandBase {
  /** Creates a new brakeMode. */
  public BrakeMode() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mDrivetrain);
    addRequirements(RobotContainer.mArmExtension);
    addRequirements(RobotContainer.mArmLift);
    addRequirements(RobotContainer.mClaw);
    addRequirements(RobotContainer.mTurret);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Brake Mode Active?")
    .setBoolean(true);


    RobotContainer.mDrivetrain.getLeftDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive3().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive3().setIdleMode(IdleMode.kBrake);
    
    RobotContainer.mClaw.getClawMotor().setIdleMode(IdleMode.kBrake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    RobotContainer.mDrivetrain.arcadeDrive(0, 0);

    RobotContainer.mTurret.setTurret(0);

    RobotContainer.mArmExtension.setArmExtend(0);
    RobotContainer.mArmLift.setArmLift(0);

    RobotContainer.mClaw.setClaw(0);

    RobotContainer.mDrivetrain.getLeftDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive3().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive3().setIdleMode(IdleMode.kBrake);
    
    RobotContainer.mClaw.getClawMotor().setIdleMode(IdleMode.kBrake);

    



  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.getLeftDrive1().setIdleMode(IdleMode.kCoast);
    RobotContainer.mDrivetrain.getLeftDrive2().setIdleMode(IdleMode.kCoast);
    RobotContainer.mDrivetrain.getLeftDrive3().setIdleMode(IdleMode.kCoast);
    RobotContainer.mDrivetrain.getRightDrive1().setIdleMode(IdleMode.kCoast);
    RobotContainer.mDrivetrain.getRightDrive2().setIdleMode(IdleMode.kCoast);
    RobotContainer.mDrivetrain.getRightDrive3().setIdleMode(IdleMode.kCoast);
  
    RobotContainer.mClaw.getClawMotor().setIdleMode(IdleMode.kCoast);

    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Brake Mode Active?")
    .setBoolean(false);


  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
