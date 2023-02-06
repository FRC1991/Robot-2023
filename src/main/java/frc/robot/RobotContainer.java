// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.MaanitDrive;
import frc.robot.commands.brakeMode;
import frc.robot.commands.chargeStation;
import frc.robot.commands.MiscCommands.BrakeMode;
import frc.robot.commands.DrivetrainCommands.ChargeStationClimb;
import frc.robot.commands.DrivetrainCommands.MaanitDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Turret;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

//The robot's subsystems, commands, and global varibales are all here


//+++++++++++++++++++++++++++++++ Global Vars=================
public static SimpleWidget GyroYaw;
public static GenericEntry maxSpeedEntry;

//==========================  Subsystems +++++++++++++++++++++++
  public static Drivetrain mDrivetrain = new Drivetrain();
  public static Arm mArm = new Arm();
  public static Claw mClaw = new Claw();
  public static Turret mTurret = new Turret();
  public static ButtonBind mButtonBind = new ButtonBind();

//=============================Commands +++++++++++++++++++++++++++++++++ 

brakeMode mBrakeMode = new brakeMode();
chargeStation mClimbStation = new chargeStation(0, mDrivetrain);
BrakeMode brakeMode = new BrakeMode();
ChargeStationClimb chargeStation = new ChargeStationClimb(); 
MaanitDrive standardMaanitDriveCommand = new MaanitDrive();

   

  public RobotContainer() {
    dashboardInit();
    NTListenerInit();
    configureBindings();
  }

  
  private void dashboardInit(){
    GyroYaw =
        Shuffleboard.getTab("Main")
            .add("Gyro Yaw", mDrivetrain.pigeon.getYaw())
            .withWidget(BuiltInWidgets.kGyro);
     }


  private void NTListenerInit(){
    NetworkTableInstance ntInstance = NetworkTableInstance.getDefault();
      
  }
    configureBindings();
  }

 //===============================Dashboard setup+++++++++++++++++++++++ 
  private void dashboardInit(){

    maxSpeedEntry = 
      Shuffleboard.getTab("Main")
      .add("Max Speed", Constants.GTADriveMultiplier)
      .withWidget(BuiltInWidgets.kNumberSlider)
      .withProperties(Map.of("Min", 0, "Max", 1))
      .getEntry();




     }

//++++++++++++++++++++++++++++++Networktable listener+++++++++++++++++++++

public static double maxSpeed = maxSpeedEntry.getDouble(1.0);




  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    mDrivetrain.setDefaultCommand(standardMaanitDriveCommand);

    mButtonBind.driveAButton.whileTrue(mBrakeMode);
    mButtonBind.driveYButton.toggleOnTrue(mClimbStation);
    mButtonBind.driveAButton.whileTrue(brakeMode);
    mButtonBind.driveBButton.toggleOnTrue(chargeStation);
    
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
  //}
}