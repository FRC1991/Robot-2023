// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.VisionCommands;

import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TurretAimCargo extends CommandBase {
  /** Creates a new TurretAim. */

  private double steerScale = Constants.visionConstant;
  private double adjustSteer = 0;
  private double xSteer;
  private AtomicReference <Double> xSteering = RobotContainer.xDistanceGamePiece;
 
  public TurretAimCargo() {
    addRequirements(RobotContainer.mTurret);
    xSteer = xSteering.get();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Is Turret Aimming")
    .setBoolean(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(xSteer > 3){
      adjustSteer = xSteer * 0.015;
      adjustSteer = adjustSteer * steerScale;
    }else if(xSteer < -3){
      adjustSteer = xSteer * 0.015;
      adjustSteer = adjustSteer * steerScale;
    }else{
      adjustSteer = 0;
    }



    RobotContainer.mTurret.setTurret(adjustSteer);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mButtonBind.alternatingDriveVibrate();
    RobotContainer.mButtonBind.alternatingAuxVibrate();
    RobotContainer.mTurret.stopTurret();
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Is Turret Aimming")
    .setBoolean(false);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(xSteer) < 3;
  }
}
