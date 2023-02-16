// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TurnTillTag extends CommandBase {
  /** Creates a new TurnTillTag. */

  double turnSpeed;
  NetworkTableEntry isTagVisible;
  private boolean tagFound = false;
  double tagNum;

  public TurnTillTag(double speed) {

    addRequirements(RobotContainer.mDrivetrain);
    turnSpeed = speed;
    isTagVisible = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tid");
    tagNum = isTagVisible.getDouble(0);
//If red alliance then follow tag 1, 2, 3, 4 
    if(Robot.isRedAlliance == true){
      if(tagNum == 1){
      tagFound = true;
      }else if(tagNum == 2){
        tagFound = true;
      }else if(tagNum == 3){
        tagFound = true;
      }else if(tagNum == 4){
        tagFound = true;
      }
    }
//If blue alliance then follow tag 5, 6, 7, 8
    if(Robot.isRedAlliance == false){
      if(tagNum == 5){
        tagFound = true;
      }else if(tagNum == 6){
      tagFound = true;
      }else if(tagNum == 7){
        tagFound = true;
      }else if(tagNum == 8){
        tagFound = true;
      }
    }
    

  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mDrivetrain.arcadeDrive(0, turnSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.mDrivetrain.arcadeDrive(0, turnSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.mDrivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return tagFound;
  }
}
