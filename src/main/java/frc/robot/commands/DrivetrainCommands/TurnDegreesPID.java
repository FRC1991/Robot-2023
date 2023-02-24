// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnDegreesPID extends PIDCommand {
  /** Creates a new TurnDegreesPID. */
  static Drivetrain drivetrain;

  public TurnDegreesPID(double targetAngleDegrees) {
    super(
      new PIDController(Constants.kTurnP,
       Constants.kTurnI, 
       Constants.kTurnD),
      // Close loop on heading
      drivetrain::getYaw,
      // Set reference to target
      targetAngleDegrees,
      // Pipe output to turn robot
      output -> drivetrain.arcadeDrive(0, output),
      // Require the drive
      drivetrain);

  // Set the controller to be continuous (because it is an angle controller)
  getController().enableContinuousInput(-180, 180);
  // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
  // setpoint before it is considered as having reached the reference
  getController()
      .setTolerance(Constants.kTurnToleranceDeg, Constants.kTurnRateToleranceDegPerS);
}


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
