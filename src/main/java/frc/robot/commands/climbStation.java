// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.sensors.Pigeon2;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class climbStation extends CommandBase {
  /** Creates a new climbStation. */
  private Drivetrain drivetrain;
  private Pigeon2 pigeon;
  private boolean isPitchFlat;
  private int topRange = 1;
  private int bottomRange = -1;
  private int waitFor = 1;

  public climbStation() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.mDrivetrain);
    pigeon = RobotContainer.mDrivetrain.pigeon;
    //int isPitchFlat = NetworkTableEntry.getNumber(drivetrain.pigeon.getPitch());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mDrivetrain.tankDrive(0, 0);
    pigeon.configMountPosePitch(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.mDrivetrain.tankDrive(0.1, 0.1);
    
    try {
      TimeUnit.SECONDS.sleep(waitFor);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if(pigeon.getPitch() >= topRange || pigeon.getPitch() <= bottomRange){
      isPitchFlat = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.tankDrive(0, 0);
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
