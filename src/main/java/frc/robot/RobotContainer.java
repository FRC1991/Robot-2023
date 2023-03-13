// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicReference;
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
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArmCommands.ArmHomePos;
import frc.robot.commands.ArmCommands.ManualArmExtension;
import frc.robot.commands.ArmCommands.ManualArmLifter;
import frc.robot.commands.ArmCommands.ManualTurret;
import frc.robot.commands.AutoCommand.ScoreAndGrabCargo;
import frc.robot.commands.AutoCommand.TurnArmScore;
import frc.robot.commands.ClawCommands.ManualClaw;
import frc.robot.commands.ClawCommands.ResetClaw;
import frc.robot.commands.ClawCommands.RotateClawTurret;
import frc.robot.commands.DrivetrainCommands.GameDrive;
import frc.robot.commands.MiscCommands.BrakeMode;
import frc.robot.commands.VisionCommands.PipelineSwitch;
import frc.robot.commands.VisionCommands.RunForTarget;
import frc.robot.commands.VisionCommands.TurretAimTarget;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LED;
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
  public final static AtomicReference<Double> botPose = new AtomicReference<Double>(0.0);

  DoubleTopic tagIDTopic, 
  yDistanceAimTopic, 
  xDistanceAimTopic, 
  retroTapeTopic, 
  gamePieceSeenTopic, 
  yDistanceGamePieceTopic, 
  xDistanceGamePieceTopic,
  botPoseTopic;

  double aprilTagIDListenerHandle, 
  yDistanceAimListenerHandle, 
  xDistanceAimListenerHandle, 
  retroTapeListenerHandle, 
  gamePieceSeenListenerHandle, 
  yDistanceGamePieceListenerHandle,
  xDistanceGamePieceListenerHandle,
  botPoseListenerHandle;

  SendableChooser<Command> autoChoose;
  GenericEntry aimLLPipeline, gameLLPipeline;
  int posInField = DriverStation.getLocation();
  boolean topLL = false;
//==========================  Subsystems +++++++++++++++++++++++
  public static Drivetrain mDrivetrain = new Drivetrain();
  public static Arm mArm = new Arm();
  public static Claw mClaw = new Claw();
  public static Turret mTurret = new Turret();
  public static ButtonBind mButtonBind = new ButtonBind();
  public static LED mLED = new LED();

//=============================Commands +++++++++++++++++++++++++++++++++ 

GameDrive standardGameDriveCommand = new GameDrive();


  public RobotContainer() {
    NTListenInit();
    configureBindings();
    dashboardInit();
  }

  private void dashboardInit(){

//Auto Chooser
  autoChoose = new SendableChooser<Command>();
  //if(posInField == 1){
  autoChoose.setDefaultOption("Score", new TurnArmScore());
 // }else if(posInField == 2){
  autoChoose.addOption("Score and Grab Cone", new ParallelDeadlineGroup( new ScoreAndGrabCargo(xDistanceAim), new PipelineSwitch()));
 // }else{
  autoChoose.addOption("Score and Grab Cube", new ScoreAndGrabCargo(xDistanceAim));
 // }
  Shuffleboard.getTab("Main").add(autoChoose);

  //Vision Pipline Selector


  //Drivetrain data
  if(mDrivetrain.getPitch() > 2 || mDrivetrain.getPitch() < -2){
      
    boolean range = false;

    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Is charging station in range?")
    .setBoolean(range);    
    
  }else{
  
    boolean range = true;
  
    NetworkTableInstance.getDefault()
    .getTable("Shuffleboard")
    .getSubTable("Main")
    .getEntry("Is charging station in range?")
    .setBoolean(range);
    
   }

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
 botPoseTopic = aimmingNT.getDoubleTopic("botpose");

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

  botPoseListenerHandle = ntInst.addListener(
    botPoseTopic,
    EnumSet.of(NetworkTableEvent.Kind.kValueAll),
    event -> {
      botPose.set(event.valueData.value.getDouble());
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


    mDrivetrain.setDefaultCommand(standardGameDriveCommand);
   
//==========================Driver binding========================
    
    //Brake mode Command
    mButtonBind.driveStartButton.toggleOnTrue(new BrakeMode());
    //Tracking Command
    mButtonBind.driveDPadUp.toggleOnTrue(new PipelineSwitch());

    mButtonBind.driveXButton.whileTrue(new RunForTarget(xDistanceAim));
    mButtonBind.driveYButton.whileTrue(new RunForTarget(xDistanceGamePiece));

    mButtonBind.driveDPadDown.toggleOnTrue(new ParallelCommandGroup(
    new InstantCommand(()-> mDrivetrain.getLeftDrive1().setSmartCurrentLimit(10)),
    new InstantCommand(()-> mDrivetrain.getLeftDrive2().setSmartCurrentLimit(10)),
    new InstantCommand(()-> mDrivetrain.getLeftDrive3().setSmartCurrentLimit(10)),
    new InstantCommand(()-> mDrivetrain.getRightDrive1().setSmartCurrentLimit(10)),
    new InstantCommand(()-> mDrivetrain.getRightDrive2().setSmartCurrentLimit(10)),
    new InstantCommand(()-> mDrivetrain.getRightDrive3().setSmartCurrentLimit(10))));

    mButtonBind.driveDPadRight.onTrue(new InstantCommand(()-> System.out.println(botPose.get())));

  
    
//=======================Aux bindings=============================
  // Manual Movement
  mButtonBind.auxLeftBumper.whileTrue(new ManualTurret(-0.3));
  mButtonBind.auxRightBumper.whileTrue(new ManualTurret(0.3));
  // Claw Commands
 mButtonBind.auxAButton.onTrue(new ResetClaw());
 mButtonBind.auxBButton.whileTrue(new ManualClaw(0.5));
  //Turret command
  mButtonBind.auxBackButton.onTrue(new ArmHomePos());

  mButtonBind.auxYButton.onTrue(new TurretAimTarget(xDistanceGamePiece));
  mButtonBind.auxXButton.onTrue(new TurretAimTarget(xDistanceAim));

  mButtonBind.auxStartButton.toggleOnTrue(new PipelineSwitch());

  

  mButtonBind.auxDPadRight.whileTrue(new ManualArmLifter(0.6));
  mButtonBind.auxDPadLeft.whileTrue(new ManualArmExtension(-0.6));

  mButtonBind.auxDPadUp.whileTrue(new ManualArmLifter(0.6));
  mButtonBind.auxDPadDown.whileTrue(new ManualArmLifter(-0.6));

  mButtonBind.auxRightStick.whileTrue(new RotateClawTurret(0.15));
  mButtonBind.auxLeftStick.whileTrue(new RotateClawTurret(-0.15));

//=========================LED Binds============================
    new InstantCommand(() -> mLED.setToOrange());
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