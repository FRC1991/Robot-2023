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
public class ArmLiftPID extends PIDCommand {
  /** Creates a new ArmLiftPID. */
  static Arm arm;

  public ArmLiftPID(double armHeightInFeet) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kArmLiftP,
         Constants.kArmLiftI, 
         Constants.kArmLiftD),
               // Close loop on heading
               arm::getArmExtendDist,
               // Set reference to target
               armHeightInFeet,
               // Pipe output to turn robot
               output -> arm.setArmLift(output),
               // Require the arm
               arm);
       
           getController()
               .setTolerance(Constants.kArmLiftToleranceDeg, Constants.kArmLiftRateToleranceDegPerS);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
