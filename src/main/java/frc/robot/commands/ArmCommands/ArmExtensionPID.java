// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmExtensionPID extends PIDCommand {
  /** Creates a new ArmExtensionPID. */
  public ArmExtensionPID(double armExtensionInFeet, Arm arm) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kArmExtendP,
         Constants.kArmExtendI, 
         Constants.kArmExtendD),
               // Close loop on heading
               arm::getArmExtendDist,
               // Set reference to target
               armExtensionInFeet,
               // Pipe output to turn robot
               output -> arm.setArmExtend(output),
               // Require the drive
               arm);
       
           // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
           // setpoint before it is considered as having reached the reference
           getController()
               .setTolerance(Constants.kArmExtendToleranceDeg, Constants.kArmExtendRateToleranceDegPerS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
