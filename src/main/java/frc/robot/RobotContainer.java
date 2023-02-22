// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DrivetrainCommands.GameDrive;
import frc.robot.commands.MiscCommands.BrakeMode;
import frc.robot.commands.VisionCommands.RunForTarget;
import frc.robot.commands.ArmCommands.CenterArm;
import frc.robot.commands.ClawCommands.ResetClaw;
import frc.robot.subsystems.Arm;
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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
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
  public final static AtomicReference<Double> aprilTagID = new AtomicReference<Double>(0.0);
  public final static AtomicReference<Double> yDistanceAim = new AtomicReference<Double>(0.0);
  public final static AtomicReference <Double> xDistanceAim = new AtomicReference<Double>(0.0);
  public final static AtomicReference<Double> retroTape = new AtomicReference<Double>(0.0);
  public final static AtomicReference<Double> gamePieceSeen = new AtomicReference<Double>(0.0);
  public final static AtomicReference<Double> yDistanceGamePiece = new AtomicReference<Double>(0.0);
  public final static AtomicReference<Double> xDistanceGamePiece = new AtomicReference<Double>(0.0);


  DoubleTopic tagIDTopic, 
  yDistanceAimTopic, 
  xDistanceAimTopic, 
  retroTapeTopic, 
  gamePieceSeenTopic, 
  yDistanceGamePieceTopic, 
  xDistanceGamePieceTopic;
  
  double aprilTagIDListenerHandle, 
  yDistanceAimListenerHandle, 
  xDistanceAimListenerHandle, 
  retroTapeListenerHandle, 
  gamePieceSeenListenerHandle, 
  yDistanceGamePieceListenerHandle,
  xDistanceGamePieceListenerHandle;

  public static boolean isChasingTag = false;

  public static GenericEntry isChasingTagEntry;

  SendableChooser<Command> autoChoose;
//==========================  Subsystems +++++++++++++++++++++++
  public static Drivetrain mDrivetrain = new Drivetrain();
  public static Arm mArm = new Arm();
  public static Claw mClaw = new Claw();
  public static Turret mTurret = new Turret();
  public static ButtonBind mButtonBind = new ButtonBind();

//=============================Commands +++++++++++++++++++++++++++++++++ 

BrakeMode brakeMode = new BrakeMode();
GameDrive standardGameDriveCommand = new GameDrive();
ResetClaw resetClaw = new ResetClaw();
CenterArm centerArm = new CenterArm();
RunForTarget runForTagDriver = new RunForTarget(ButtonBind.driverController.getRightTriggerAxis(), xDistanceGamePiece);
RunForTarget runForTagAuto = new RunForTarget(xDistanceAim);
   

  public RobotContainer() {
    NTListenInit();
    configureBindings();
    dashboardInit();
  }

  private void dashboardInit(){

//Auto Chooser
  autoChoose = new SendableChooser<Command>();
  autoChoose.setDefaultOption("Auto From Right", brakeMode);
  autoChoose.addOption("Auto From Middle", brakeMode);
  autoChoose.addOption("Auto From Left", brakeMode);
  Shuffleboard.getTab("Main").add(autoChoose);
//Vison Entries 
    isChasingTagEntry = Shuffleboard.getTab("Main").add("Is Chasing Tag", isChasingTag).getEntry();


  }
 


//++++++++++++++++++++++++++++++Networktable listener+++++++++++++++++++++
  private void NTListenInit(){
//Network tables setup
NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
NetworkTable aimmingNT = ntInst.getTable("limelight");
NetworkTable gamePieceNT = ntInst.getTable("limelight-gamePiece");

//If tracking during holding A cube otherwise cone
if(mButtonBind.getAuxA() == true){
  gamePieceNT.getEntry("pipeline").setNumber(0);
}else{
  gamePieceNT.getEntry("pipeline").setNumber(1);
}

//If tracking During holding B Tag otherwise tape
if(mButtonBind.getAuxB() == true){
  aimmingNT.getEntry("pipeline").setNumber(0);
}else{
  aimmingNT.getEntry("pipeline").setNumber(1);
}

//Topics From Aimming NT
 tagIDTopic = aimmingNT.getDoubleTopic("tid");
 yDistanceAimTopic = aimmingNT.getDoubleTopic("ty");
 xDistanceAimTopic = aimmingNT.getDoubleTopic("tx");
 retroTapeTopic = aimmingNT.getDoubleTopic("tv");

//Topics from Game piece NT
  gamePieceSeenTopic = gamePieceNT.getDoubleTopic("tv");
  yDistanceGamePieceTopic = gamePieceNT.getDoubleTopic("ty");
  xDistanceGamePieceTopic = gamePieceNT.getDoubleTopic("tx");


 //Listeners for Aimming NT
 aprilTagIDListenerHandle = ntInst.addListener(
  tagIDTopic,
  EnumSet.of(NetworkTableEvent.Kind.kValueAll),
  event -> {
      aprilTagID.set(event.valueData.value.getDouble());
  });

  yDistanceAimListenerHandle = ntInst.addListener(
    yDistanceAimTopic, 
    EnumSet.of(NetworkTableEvent.Kind.kValueAll),
    event -> {
      yDistanceAim.set(event.valueData.value.getDouble());
    });

  xDistanceAimListenerHandle = ntInst.addListener(
    xDistanceAimTopic, 
    EnumSet.of(NetworkTableEvent.Kind.kValueAll),
    event -> {
      xDistanceAim.set(event.valueData.value.getDouble());
    });

  retroTapeListenerHandle = ntInst.addListener(
    retroTapeTopic,
    EnumSet.of(NetworkTableEvent.Kind.kValueAll),
    event -> {
      retroTape.set(event.valueData.value.getDouble());
    });

//Listener from Game Piece NT
  gamePieceSeenListenerHandle = ntInst.addListener(
    gamePieceSeenTopic,
    EnumSet.of(NetworkTableEvent.Kind.kValueAll), 
    event -> {
      gamePieceSeen.set(event.valueData.value.getDouble());
    });

  yDistanceGamePieceListenerHandle = ntInst.addListener(
    yDistanceGamePieceTopic,
    EnumSet.of(NetworkTableEvent.Kind.kValueAll), 
    event -> {
      yDistanceGamePiece.set(event.valueData.value.getDouble());
    });
  
  xDistanceGamePieceListenerHandle = ntInst.addListener(
    xDistanceGamePieceTopic,
    EnumSet.of(NetworkTableEvent.Kind.kValueAll), 
    event -> {
      xDistanceGamePiece.set(event.valueData.value.getDouble());
    });
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
    mDrivetrain.setDefaultCommand(standardGameDriveCommand);


   

    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoChoose.getSelected();
  }
}