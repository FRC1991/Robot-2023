// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.networktables.DoubleArrayTopic;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEvent;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArmCommands.ArmHomePos;
import frc.robot.commands.ArmCommands.ManualArmExtension;
import frc.robot.commands.ArmCommands.ManualArmLifter;
import frc.robot.commands.ArmCommands.ManualTurret;
import frc.robot.commands.ClawCommands.BackPreassure;
import frc.robot.commands.DrivetrainCommands.GameDrive;
import frc.robot.commands.MiscCommands.BrakeMode;
import frc.robot.commands.VisionCommands.AutoPickup;
import frc.robot.commands.VisionCommands.CenterAndRunForTarget;
import frc.robot.commands.VisionCommands.PipelineSwitch;
import frc.robot.commands.VisionCommands.TurretAimTarget;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmLift;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Turret;

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
  public final static AtomicReference<Double> cargoArea = new AtomicReference<Double>(0.0);
  public final static AtomicReference<Double> tagArea = new AtomicReference<Double>(0.0);
  public final static AtomicReference<Pose3d> botPose = new AtomicReference<Pose3d>(new Pose3d());


  DoubleTopic tagIDTopic, 
  yDistanceAimTopic, 
  xDistanceAimTopic, 
  retroTapeTopic, 
  gamePieceSeenTopic, 
  yDistanceGamePieceTopic, 
  xDistanceGamePieceTopic,
  cargoAreaTopic,
  tagAreaTopic;

  DoubleArrayTopic botPoseTopic;

  double aprilTagIDListenerHandle, 
  yDistanceAimListenerHandle, 
  xDistanceAimListenerHandle, 
  retroTapeListenerHandle, 
  gamePieceSeenListenerHandle, 
  yDistanceGamePieceListenerHandle,
  xDistanceGamePieceListenerHandle,
  cargoAreaListenerHandle,
  tagAreaListenerHandle;

  int botPoseListenerHandle;

  SendableChooser<Command> autoChoose;
  GenericEntry aimLLPipeline, gameLLPipeline;
  int posInField = DriverStation.getLocation();
  double maxSpeedSup = 0.8;
  //==========================  Subsystems +++++++++++++++++++++++
  public static Drivetrain mDrivetrain = new Drivetrain();
  public static ArmExtension mArmExtension = new ArmExtension();
  public static ArmLift mArmLift = new ArmLift();
  public static Claw mClaw = new Claw();
  public static Turret mTurret = new Turret();
  public static ButtonBind mButtonBind = new ButtonBind();

//=============================Commands +++++++++++++++++++++++++++++++++ 

GameDrive standardGameDriveCommand = new GameDrive(()-> maxSpeedSup);


  public RobotContainer() {
    NTListenInit();
    configureBindings();
    dashboardInit();
  }

  private void dashboardInit(){

//Auto Chooser
  autoChoose = new SendableChooser<Command>();
  //if(posInField == 1){
  //autoChoose.setDefaultOption("Score", new TurnArmScore());
 // }else if(posInField == 2){
 // autoChoose.addOption("Score and Grab Cone", new ParallelDeadlineGroup( new CornerAuto(xDistanceAim), new PipelineSwitch()));
 // }else{
  //autoChoose.addOption("Score and Grab Cube", new CornerAuto(xDistanceAim));
 // }
  Shuffleboard.getTab("Main").add(autoChoose);

  }




 


//++++++++++++++++++++++++++++++Networktable listener+++++++++++++++++++++
  private void NTListenInit(){
//Network tables setup
NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
NetworkTable aimmingNT = ntInst.getTable("limelight-aimming");
NetworkTable gamePieceNT = ntInst.getTable("limelight-cargo");


//Topics From Aimming NT
 tagIDTopic = aimmingNT.getDoubleTopic("tid");
 yDistanceAimTopic = aimmingNT.getDoubleTopic("ty");
 xDistanceAimTopic = aimmingNT.getDoubleTopic("tx");
 retroTapeTopic = aimmingNT.getDoubleTopic("tv");
 tagAreaTopic = aimmingNT.getDoubleTopic("ta");
 botPoseTopic = aimmingNT.getDoubleArrayTopic("botpose");

//Topics from Game piece NT
  gamePieceSeenTopic = gamePieceNT.getDoubleTopic("tv");
  yDistanceGamePieceTopic = gamePieceNT.getDoubleTopic("ty");
  xDistanceGamePieceTopic = gamePieceNT.getDoubleTopic("tx");
  cargoAreaTopic = gamePieceNT.getDoubleTopic("ta");

 //Listeners for Aimming NT
 aprilTagIDListenerHandle = ntInst.addListener(
  tagIDTopic,
  EnumSet.of(NetworkTableEvent.Kind.kValueAll),
  event -> {
      aprilTagID.set(event.valueData.value.getDouble());
  });

  tagAreaListenerHandle = ntInst.addListener(
    tagAreaTopic,
    EnumSet.of(NetworkTableEvent.Kind.kValueAll),
    event -> {
      tagArea.set(event.valueData.value.getDouble());
    });

  botPoseListenerHandle = ntInst.addListener(
    botPoseTopic,
    EnumSet.of(NetworkTableEvent.Kind.kValueAll),
    event -> {
    double[] coordsArray = event.valueData.value.getDoubleArray();
    botPose.set(new Pose3d(coordsArray[0], coordsArray[1], coordsArray[2],
      new Rotation3d(coordsArray[3], coordsArray[4], coordsArray[5])));
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

  cargoAreaListenerHandle = ntInst.addListener(
    cargoAreaTopic,
     EnumSet.of(NetworkTableEvent.Kind.kValueAll),
     event -> {
      cargoArea.set(event.valueData.value.getDouble());
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


    mDrivetrain.setDefaultCommand(standardGameDriveCommand);
    mArmLift.setDefaultCommand(new ManualArmLifter());
    mArmExtension.setDefaultCommand(new ManualArmExtension());
    mClaw.setDefaultCommand(new BackPreassure());
//==========================Driver binding========================
    
    //Brake mode Command
    mButtonBind.driveStartButton.toggleOnTrue(new BrakeMode());
    //Tracking Command
    mButtonBind.driveDPadUp.toggleOnTrue(new PipelineSwitch());

    mButtonBind.driveXButton.whileTrue(new CenterAndRunForTarget(xDistanceAim, cargoArea));
    mButtonBind.driveYButton.whileTrue(new CenterAndRunForTarget(xDistanceGamePiece, cargoArea));


    mButtonBind.driveDPadRight.onTrue(new InstantCommand(()-> System.out.println(cargoArea.get())));

  
    
//=======================Aux bindings=============================
  // Manual Movement
  mButtonBind.auxLeftBumper.whileTrue(new ManualTurret(-0.4));
  mButtonBind.auxRightBumper.whileTrue(new ManualTurret(0.4));
  // Claw Commands
 //mButtonBind.auxAButton.onTrue(new ResetClaw());
 //mButtonBind.auxBButton.whileTrue(new ManualClaw(0.7));
  //Turret command
  mButtonBind.auxBackButton.onTrue(new ArmHomePos());

  mButtonBind.auxYButton.onTrue(new TurretAimTarget(xDistanceGamePiece));
  mButtonBind.auxXButton.onTrue(new TurretAimTarget(xDistanceAim));

  mButtonBind.auxStartButton.toggleOnTrue(new PipelineSwitch());

  mButtonBind.auxDPadUp.onTrue(new AutoPickup(cargoArea, xDistanceGamePiece));
  
  

//=========================LED Binds============================
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