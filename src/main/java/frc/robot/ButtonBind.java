// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class ButtonBind {
//Controllers
    public final static CommandXboxController driverController = new CommandXboxController(0);
    public final static CommandXboxController auxController = new CommandXboxController(1);
//DRIVER BINDINGS
//Driver Binding For Buttons
    public final Trigger driveAButton = driverController.a();
    public final Trigger driveBButton = driverController.b();
    public final Trigger driveXButton = driverController.x();
    public final Trigger driveYButton = driverController.y();
    public final Trigger driveLeftBumper = driverController.leftBumper();
    public final Trigger driveRightBumper = driverController.rightBumper();
    public final Trigger driveStartButton = driverController.start();

    public boolean getDriveA(){
        return driveAButton.getAsBoolean();
    }

    public boolean getDriveB(){
        return driveBButton.getAsBoolean();
    }

    public boolean getDriveX(){
        return driveXButton.getAsBoolean();
    }

    public boolean getDriveY(){
        return driveYButton.getAsBoolean();
    }

    public boolean getDriveRightBumper(){
        return driveRightBumper.getAsBoolean();
    }

    public boolean getDriveLeftBumper(){
        return driveLeftBumper.getAsBoolean();
    }

    public boolean getDriveStart(){
        return driveStartButton.getAsBoolean();
    }
//Driving Bindings for Joysticks
    public final Trigger driveLeftStick = driverController.leftStick();
    public final double driveLeftX = driverController.getLeftX();
    public final double driveLeftY = driverController.getLeftY();
    public final Trigger driveRightStick = driverController.rightStick();
    public final double driveRightX = driverController.getRightX();
    public final double driveRightY = driverController.getRightY();
    

    public double getDriveLeftY(){
        return driveLeftY;
    }

    public double getDriveLeftX(){
        return driveLeftX;
    }

    public double getDriveRightX(){
        return driveRightX;
    }

    public double getDriveRightY(){
        return driveRightY;
    }
//Drive Bindings for triggers
    public final double driveLeftTrigger = driverController.getLeftTriggerAxis();
    public final double driveRightTrigger = driverController.getRightTriggerAxis();

    public double getDriveRightTrigger(){
        return driveRightTrigger;
    }

    public double getDriveLeftTrigger(){
        return driveLeftTrigger;
    }

//Drive Bindings for DPad
    public final Trigger driveDPadDown = driverController.povDown();
    public final Trigger driveDPadUp = driverController.povUp();
    public final Trigger driveDPadLeft = driverController.povLeft();
    public final Trigger driveDPadRight = driverController.povRight();

   
   public Trigger getDriveDPadDown(){
    return driveDPadDown;
   }

   public Trigger getDriveDPadUp(){
    return driveDPadUp;
   }

   public Trigger getDriveDPadRight(){
    return driveDPadRight;
   }

   public Trigger getDriveDPadLeft(){
    return driveDPadLeft;
   }

    

//AUX BINDINGS
//Aux bindings for buttons
    public final Trigger auxAButton = auxController.a();
    public final Trigger auxBButton = auxController.b();
    public final Trigger auxXButton = auxController.x();
    public final Trigger auxYButton = auxController.y();
    public final Trigger auxLeftBumper = auxController.leftBumper();
    public final Trigger auxRightBumper = auxController.rightBumper();
    public final Trigger auxStartButton = auxController.start();
    
    public boolean getAuxA(){
        return auxAButton.getAsBoolean();
    }

    public boolean getAuxB(){
        return auxBButton.getAsBoolean();
    }

    public boolean getAuxX(){
        return auxXButton.getAsBoolean();
    }

    public boolean getAuxY(){
        return auxYButton.getAsBoolean();
    }

    public boolean getAuxRightBumper(){
        return auxRightBumper.getAsBoolean();
    }

    public boolean getAuxLeftBumper(){
        return auxLeftBumper.getAsBoolean();
    }

    public boolean getAuxStart(){
        return auxStartButton.getAsBoolean();
    }
//Aux Binding for Joysticks
    public final Trigger auxLeftStick = auxController.leftStick();
    public final double auxLeftX = auxController.getLeftX();
    public final double auxLeftY = auxController.getLeftY();
    public final Trigger auxRightStick = auxController.rightStick();
    public final double auxRightX = auxController.getRightX();
    public final double auxRightY = auxController.getRightY();
    
    public double getAuxLeftY(){
        return auxLeftY;
    }

    public double getAuxLeftX(){
        return auxLeftX;
    }

    public double getAuxRightX(){
        return auxRightX;
    }

    public double getAuxRightY(){
        return auxRightY;
    }
//Aux Bindings for Triggers
    public final double auxLeftTrigger = auxController.getLeftTriggerAxis();
    public final double auxRightTrigger = auxController.getRightTriggerAxis();

    public double getAuxRightTrigger(){
        return auxRightTrigger;
    }

    public double getAuxLeftTrigger(){
        return auxLeftTrigger;
    }
//Aux Bindings for DPad
    public final Trigger auxDPadDown = auxController.povDown();
    public final Trigger auxDPadUp = auxController.povUp();
    public final Trigger auxDPadLeft = auxController.povLeft();
    public final Trigger auxDPadRight = auxController.povRight();

    public Trigger getAuxDPadDown(){
        return auxDPadDown;
       }
    
       public Trigger getAuxDPadUp(){
        return auxDPadUp;
       }
    
       public Trigger getAuxDPadRight(){
        return auxDPadRight;
       }
    
       public Trigger getAuxDPadLeft(){
        return auxDPadLeft;
       }


//Limit switches
       DigitalInput clawLimitSwitch = new DigitalInput(0);

       Trigger clawLimit = new Trigger(clawLimitSwitch::get);
    

}
