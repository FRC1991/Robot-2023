// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmLiftPID extends ProfiledPIDCommand {
  /** Creates a new TurnDegrees. */
  public ArmLiftPID(double targetPosInFeet, Arm arm) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            Constants.kArmLiftP,
            Constants.kArmLiftI,
            Constants.kArmLiftD,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(Constants.kMaxArmLiftRatePerS, Constants.kMaxArmLiftAccelerationPerSSquared)),
        // This should return the measurement
        arm::getArmExtendDist,
        // This should return the goal (can also be a constant)
        targetPosInFeet,
        // This uses the output
        (output, setpoint) -> arm.setArmLift(output),
          // Use the output (and setpoint, if desired) here
         arm);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    //getController().enableContinuousInput(-180, 180);

    getController().setTolerance(Constants.kArmLiftToleranceDeg, Constants.kArmLiftRateToleranceDegPerS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(getController().atGoal() == true){
     return true;
    }else if(RobotContainer.mButtonBind.armLiftMaxLimit.getAsBoolean() == false){
      return true;
    }else if(RobotContainer.mButtonBind.armLiftMinLimit.getAsBoolean() == false){
      return true;
    }else{
      return false;
    }
  }
}
