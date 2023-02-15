// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DrivetrainCommands.GameDrive;
import frc.robot.commands.MiscCommands.BrakeMode;
import frc.robot.commands.ArmCommands.ArmExtend;
import frc.robot.commands.DrivetrainCommands.ChargeStationClimb;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Turret;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEvent;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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
  final AtomicReference<Double> aprilTagID = new AtomicReference<Double>();
  //final AtomicReference<Boolean> isTagVisible = new AtomicReference<Boolean>(false);
  GenericEntry isTagVisible;
  DoubleTopic tagIDTopic;
  int aprilTagIDListenerHandle;
//==========================  Subsystems +++++++++++++++++++++++
  public static Drivetrain mDrivetrain = new Drivetrain();
  public static ArmExtension mArmExtension = new ArmExtension();
  public static Claw mClaw = new Claw();
  public static Turret mTurret = new Turret();
  public static ButtonBind mButtonBind = new ButtonBind();

//=============================Commands +++++++++++++++++++++++++++++++++ 

BrakeMode brakeMode = new BrakeMode();
ChargeStationClimb chargeStation = new ChargeStationClimb(); 
GameDrive standardGameDriveCommand = new GameDrive();
ArmExtend armExtend = new ArmExtend();
   

  public RobotContainer() {
    dashboardInit();
    NTListenInit();
    configureBindings();
  }

  
 

//++++++++++++++++++++++++++++++Networktable listener+++++++++++++++++++++
  private void NTListenInit(){
//Network tables setup
NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
NetworkTable aimmingNT = ntInst.getTable("limelight");
NetworkTable gamePieceNT = ntInst.getTable("limelight-gamePiece");

 tagIDTopic = aimmingNT.getDoubleTopic("tid");

 aprilTagIDListenerHandle = ntInst.addListener(
  tagIDTopic,
  EnumSet.of(NetworkTableEvent.Kind.kValueAll),
  event -> {
      aprilTagID.set(event.valueData.value.getDouble());
  });


  
}

//===============================Dashboard setup+++++++++++++++++++++++ 
private void dashboardInit(){

  //isTagVisible = Shuffleboard.getTab("Main").add("Is tag in view", aprilTagIDListenerHandle).getEntry();

   }



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
    mDrivetrain.setDefaultCommand(armExtend);

    mButtonBind.driveAButton.whileTrue(brakeMode);
    mButtonBind.driveBButton.toggleOnTrue(chargeStation);

   
    mButtonBind.driveXButton.onTrue(new InstantCommand(
      ()->{
        System.out.println(aprilTagID.get());
      }));
   // mButtonBind.clawLimit.toggleOnTrue(mClaw::resetClawEncoder);
    
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