// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class ButtonBind {
//Controllers
    public final static CommandXboxController driverController = new CommandXboxController(0);
    public final static CommandXboxController auxController = new CommandXboxController(1);
//Driver Binding
    public final static Trigger driveAButton = driverController.a();
    public final static Trigger driveBButton = driverController.b();
    public final static Trigger driveXButton = driverController.x();
    public final static Trigger driveYButton = driverController.y();
    public final static Trigger driveLeftBumper = driverController.leftBumper();
    public final static Trigger driveRightBumper = driverController.rightBumper();
    public final static Trigger driveStartButton = driverController.start();

    
    public final static Trigger driveLeftStick = driverController.leftStick();
    public final static Trigger driveRightStick = driverController.rightStick();
    
    public final static Trigger driveLeftTrigger = driverController.leftTrigger();
    public final static Trigger driveRightTrigger = driverController.rightTrigger();

    public final static Trigger driveDPadDown = driverController.povDown();
    public final static Trigger driveDPadUp = driverController.povUp();
    public final static Trigger driveDPadLeft = driverController.povLeft();
    public final static Trigger driveDPadRight = driverController.povRight();

//Aux Binding
    public final static Trigger auxAButton = auxController.a();
    public final static Trigger auxBButton = auxController.b();
    public final static Trigger auxXButton = auxController.x();
    public final static Trigger auxYButton = auxController.y();
    public final static Trigger auxLeftBumper = auxController.leftBumper();
    public final static Trigger auxRightBumper = auxController.rightBumper();
    public final static Trigger auxStartButton = auxController.start();

    
    public final static Trigger auxLeftStick = auxController.leftStick();
    public final static Trigger auxRightStick = auxController.rightStick();
    
    public final static Trigger auxLeftTrigger = auxController.leftTrigger();
    public final static Trigger auxRightTrigger = auxController.rightTrigger();

    public final static Trigger auxDPadDown = auxController.povDown();
    public final static Trigger auxDPadUp = auxController.povUp();
    public final static Trigger auxDPadLeft = auxController.povLeft();
    public final static Trigger auxDPadRight = auxController.povRight();






}
