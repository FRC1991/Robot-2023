// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class ButtonBind {
//Controllers
    public final static CommandXboxController driverController = new CommandXboxController(0);
    public final static CommandXboxController auxController = new CommandXboxController(1);
    public final static CommandGenericHID buttonBox = new CommandGenericHID(3);
//Controllers just for rumble
    private final static XboxController driveRumble = new XboxController(0);
    private final static XboxController auxRumble = new XboxController(1);
//DRIVER BINDINGS
//Driver Binding For Buttons
    public final Trigger driveAButton = driverController.a();
    public final Trigger driveBButton = driverController.b();
    public final Trigger driveXButton = driverController.x();
    public final Trigger driveYButton = driverController.y();
    public final Trigger driveLeftBumper = driverController.leftBumper();
    public final Trigger driveRightBumper = driverController.rightBumper();
    public final Trigger driveStartButton = driverController.start();
    public final Trigger driveBackButton = driverController.back();

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
    public final Trigger auxBackButton = auxController.back();

    
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
//Aux Binding for Joysticks==========================================================
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
    public final Trigger auxRightTriggerButton = auxController.leftTrigger();
    public final Trigger auxLeftTriggerButton = auxController.rightTrigger();


    public double getAuxRightTrigger(){
        return auxRightTrigger;
    }

    public double getAuxLeftTrigger(){
        return auxLeftTrigger;
    }

    public Trigger getAuxRightTriggerButton(){
        return auxRightTriggerButton;
    }

    public Trigger getAuxLeftTriggerButton(){
        return auxLeftTriggerButton;
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

//Button Box Bindings

  public final Trigger buttonOne =  buttonBox.button(0);
  public final Trigger buttonTwo =  buttonBox.button(1);
  public final Trigger buttonThree =  buttonBox.button(2);
  public final Trigger buttonFour =  buttonBox.button(3);
  public final Trigger buttonFive =  buttonBox.button(4);
  public final Trigger buttonSix =  buttonBox.button(5);
  public final Trigger buttonSeven =  buttonBox.button(7);
  public final Trigger buttonEight =  buttonBox.button(7);

  public final double buttonBoxX = buttonBox.getRawAxis(0);
  public final double buttonBoxY = buttonBox.getRawAxis(1);


//Limit switches
    public final DigitalInput intakeDIO = new DigitalInput(1);




    public final Trigger intakeStop = new Trigger(intakeDIO::get);
   


//Rumble Controllers

       public void doubleDriveVibrate(){
        for(int i = 0; i < 2; i++){
            driveRumble.setRumble(RumbleType.kBothRumble, 1);
            Timer.delay(0.2);
            driveRumble.setRumble(RumbleType.kBothRumble, 0);
            Timer.delay(0.2);
        }
       }
       public void singleDriveVibrate(){
            driveRumble.setRumble(RumbleType.kBothRumble, 1);
            Timer.delay(0.25);
            driveRumble.setRumble(RumbleType.kBothRumble, 0);
       }
       public void alternatingDriveVibrate(){
            driveRumble.setRumble(RumbleType.kLeftRumble, 1);
            driveRumble.setRumble(RumbleType.kRightRumble, 1);
       }

       public void doubleAuxVibrate(){
        for(int i = 0; i < 2; i++){
            auxRumble.setRumble(RumbleType.kBothRumble, 1);
            Timer.delay(0.2);
            auxRumble.setRumble(RumbleType.kBothRumble, 0);
            Timer.delay(0.2);
        }
       }
       public void singleAuxVibrate(){
        auxRumble.setRumble(RumbleType.kBothRumble, 1);
            Timer.delay(0.25);
        auxRumble.setRumble(RumbleType.kBothRumble, 0);
       }
       public void alternatingAuxVibrate(){
        auxRumble.setRumble(RumbleType.kLeftRumble, 1);
        auxRumble.setRumble(RumbleType.kRightRumble, 1);
       }
        

    

}
