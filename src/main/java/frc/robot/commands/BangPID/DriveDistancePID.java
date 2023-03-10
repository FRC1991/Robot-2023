
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BangPID;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveDistancePID extends ProfiledPIDCommand {
  /** Creates a new TurnDegrees. */
  public DriveDistancePID(double targetDistance, Drivetrain drivetrain) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            Constants.kDriveP,
            Constants.kDriveI,
            Constants.kDriveD,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(Constants.kMaxDistPerS, Constants.kMaxAccelerationPerS)),
        // This should return the measurement
        drivetrain::getDistanceFeet,
        // This should return the goal (can also be a constant)
        targetDistance,
        // This uses the output
        (output, setpoint) -> drivetrain.arcadeDrive(output, 0),
          // Use the output (and setpoint, if desired) here
         drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

    getController().setTolerance(Constants.kDistTolerance, Constants.kDistRateTolerancePerS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
     return getController().atGoal();

  }
}
