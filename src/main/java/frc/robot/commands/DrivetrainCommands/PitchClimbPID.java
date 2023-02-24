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
public class PitchClimbPID extends PIDCommand {
  /** Creates a new PitchClimbPID. */
  static Drivetrain drivetrain;
  public PitchClimbPID(double targetAngleDegrees) {
    super(
      new PIDController(Constants.kChargeP,
       Constants.kChargeI, 
       Constants.kChargeD),
      // Close loop on heading
      drivetrain::getPitch,
      // Set reference to target
      targetAngleDegrees,
      // Pipe output to turn robot
      output -> drivetrain.arcadeDrive(output, 0),
      // Require the drive
      drivetrain);

  // Set the controller to be continuous (because it is an angle controller)
  //+++++++++++++++++++++getController().enableContinuousInput(-180, 180);
  // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
  // setpoint before it is considered as having reached the reference
  getController()
      .setTolerance(Constants.kChargeToleranceDeg, Constants.kChargeRateToleranceDegPerS);
}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
