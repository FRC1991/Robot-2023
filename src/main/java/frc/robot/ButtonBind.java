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
    public final Trigger driveAButton = driverController.a();
    public final Trigger driveBButton = driverController.b();
    public final Trigger driveXButton = driverController.x();
    public final Trigger driveYButton = driverController.y();
    public final Trigger driveLeftBumper = driverController.leftBumper();
    public final Trigger driveRightBumper = driverController.rightBumper();
    public final Trigger driveStartButton = driverController.start();

    
    public final Trigger driveLeftStick = driverController.leftStick();
    public final double driveLeftX = driverController.getLeftX();
    public final Trigger driveRightStick = driverController.rightStick();
    
    public final double driveLeftTrigger = driverController.getLeftTriggerAxis();
    public final double driveRightTrigger = driverController.getRightTriggerAxis();

    public final Trigger driveDPadDown = driverController.povDown();
    public final Trigger driveDPadUp = driverController.povUp();
    public final Trigger driveDPadLeft = driverController.povLeft();
    public final Trigger driveDPadRight = driverController.povRight();

//Aux Binding
    public final Trigger auxAButton = auxController.a();
    public final Trigger auxBButton = auxController.b();
    public final Trigger auxXButton = auxController.x();
    public final Trigger auxYButton = auxController.y();
    public final Trigger auxLeftBumper = auxController.leftBumper();
    public final Trigger auxRightBumper = auxController.rightBumper();
    public final Trigger auxStartButton = auxController.start();

    
    public final Trigger auxLeftStick = auxController.leftStick();
    public final Trigger auxRightStick = auxController.rightStick();
    
    public final Trigger auxLeftTrigger = auxController.leftTrigger();
    public final Trigger auxRightTrigger = auxController.rightTrigger();

    public final Trigger auxDPadDown = auxController.povDown();
    public final Trigger auxDPadUp = auxController.povUp();
    public final Trigger auxDPadLeft = auxController.povLeft();
    public final Trigger auxDPadRight = auxController.povRight();


    public double getDriveRightTrigger(){
        return driveRightTrigger;
    }

    public double getDriveLeftTrigger(){
        return driveLeftTrigger;
    }

    public double getDriveLeftX(){
        return driveLeftX;
    }

    public boolean getDriveX(){
        return driveXButton.getAsBoolean();
    }

    public boolean getDriveRightBumper(){
        return driveRightBumper.getAsBoolean();
    }




}
