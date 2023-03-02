// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.MiscCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class BrakeMode extends CommandBase {
  /** Creates a new brakeMode. */
  public BrakeMode() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mDrivetrain);
    addRequirements(RobotContainer.mArm);
    addRequirements(RobotContainer.mClaw);
    addRequirements(RobotContainer.mTurret);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    RobotContainer.mDrivetrain.arcadeDrive(0, 0);

    RobotContainer.mTurret.setTurret(0);

    RobotContainer.mArm.setArmExtend(0);
    RobotContainer.mArm.setArmLift(0);

    RobotContainer.mClaw.setClaw(0);
    RobotContainer.mClaw.setClawTurret(0);

    RobotContainer.mDrivetrain.getLeftDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getLeftDrive3().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive2().setIdleMode(IdleMode.kBrake);
    RobotContainer.mDrivetrain.getRightDrive3().setIdleMode(IdleMode.kBrake);
    
    RobotContainer.mTurret.getTurret1().setIdleMode(IdleMode.kBrake);
    RobotContainer.mTurret.getTurret2().setIdleMode(IdleMode.kBrake);

    RobotContainer.mArm.getArmExtender().setIdleMode(IdleMode.kBrake);
    RobotContainer.mArm.getArmLifterOne().setIdleMode(IdleMode.kBrake);
    RobotContainer.mArm.getArmLifterTwo().setIdleMode(IdleMode.kBrake);

    RobotContainer.mClaw.getClawMotor().setIdleMode(IdleMode.kBrake);
    RobotContainer.mClaw.getClawTurretMotor().setIdleMode(IdleMode.kBrake);


    Timer.delay(0.3);
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
  
    RobotContainer.mTurret.getTurret1().setIdleMode(IdleMode.kCoast);
    RobotContainer.mTurret.getTurret2().setIdleMode(IdleMode.kCoast);

    RobotContainer.mArm.getArmExtender().setIdleMode(IdleMode.kCoast);
    RobotContainer.mArm.getArmLifterOne().setIdleMode(IdleMode.kCoast);
    RobotContainer.mArm.getArmLifterTwo().setIdleMode(IdleMode.kCoast);

    RobotContainer.mClaw.getClawMotor().setIdleMode(IdleMode.kCoast);
    RobotContainer.mClaw.getClawTurretMotor().setIdleMode(IdleMode.kCoast);


  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
