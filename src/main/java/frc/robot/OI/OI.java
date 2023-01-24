// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.OI;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class OI {

    public CommandXboxController driverController, auxController;
    private JoystickButton driveAButton,
    driveBButton,
    driveXButton,
    driveYButton,
    driveStartButton,
    driveSelectButton,
    driveRightBumper,
    driveLeftBumper,
    auxAButton,
    auxBButton,
    auxXButton,
    auxYButton,
    auxStartButton,
    auxSelectButton,
    auxRightBumper,
    auxLeftBumper;

    private DPadButton driveDPadUpButton,
    driveDPadDownButton,
    driveDPadLeftButton,
    driveDPadRightButton,
    auxDPadUpButton,
    auxDPadDownButton,
    auxDPadLeftButton,
    auxDPadRightButton;

    private JoystickAnalogButton driveRightStickUpButton,
    driveRightStickDownButton,
    driveRightStickLeftButton,
    driveRightStickRightButton,
    driveLeftStickUpButton,
    driveLeftStickDownButton,
    driveLeftStickLeftButton,
    driveLeftStickRightButton,
    driveLeftTriggerButton,
    driveRightTriggerButton,
    auxRightStickUpButton,
    auxRightStickDownButton,
    auxRightStickLeftButton,
    auxRightStickRightButton,
    auxLeftStickUpButton,
    auxLeftStickDownButton,
    auxLeftStickLeftButton,
    auxLeftStickRightButton,
    auxLeftTriggerButton,
    auxRightTriggerButton;

    public OI(){
        driverController = new CommandXboxController(0);
        auxController = new CommandXboxController(1);

        driveAButton = new JoystickButton(driverController.getHID(), 1);
        driveBButton = new JoystickButton(driverController.getHID(), 2);
        driveXButton = new JoystickButton(driverController.getHID(), 3);
        driveYButton = new JoystickButton(driverController.getHID(), 4);

        driveLeftBumper = new JoystickButton(driverController.getHID(), 5);
        driveRightBumper = new JoystickButton(driverController.getHID(), 6);
        driveSelectButton = new JoystickButton(driverController.getHID(), 7);
        driveStartButton = new JoystickButton(driverController.getHID(), 8);

        driveDPadUpButton = new DPadButton(driverController, DPadButton.Direction.UP);
        driveDPadRightButton = new DPadButton(driverController, DPadButton.Direction.RIGHT);
        driveDPadLeftButton = new DPadButton(driverController, DPadButton.Direction.LEFT);
        driveDPadDownButton = new DPadButton(driverController, DPadButton.Direction.DOWN);

        driveRightStickUpButton = new JoystickAnalogButton(driverController, 5, -0.5);
        driveRightStickDownButton = new JoystickAnalogButton(driverController, 5);
        driveRightStickLeftButton = new JoystickAnalogButton(driverController, 4, -0.5);
        driveRightStickRightButton = new JoystickAnalogButton(driverController, 4);
        driveLeftStickUpButton = new JoystickAnalogButton(driverController, 1, -0.5);
        driveLeftStickDownButton = new JoystickAnalogButton(driverController, 1);
        driveLeftStickLeftButton = new JoystickAnalogButton(driverController, 0, -0.5);
        driveLeftStickRightButton = new JoystickAnalogButton(driverController, 0);
        driveLeftTriggerButton = new JoystickAnalogButton(driverController, 2);
        driveRightTriggerButton = new JoystickAnalogButton(driverController,3);


        auxAButton = new JoystickButton(auxController.getHID(), 1);
        auxBButton = new JoystickButton(auxController.getHID(), 2);
        auxXButton = new JoystickButton(auxController.getHID(), 3);
        auxYButton = new JoystickButton(auxController.getHID(), 4);

        auxLeftBumper = new JoystickButton(auxController.getHID(), 5);
        auxRightBumper = new JoystickButton(auxController.getHID(), 6);
        auxSelectButton = new JoystickButton(auxController.getHID(), 7);
        auxStartButton = new JoystickButton(auxController.getHID(), 8);

        auxDPadUpButton = new DPadButton(auxController, DPadButton.Direction.UP);
        auxDPadRightButton = new DPadButton(auxController, DPadButton.Direction.RIGHT);
        auxDPadLeftButton = new DPadButton(auxController, DPadButton.Direction.LEFT);
        auxDPadDownButton = new DPadButton(auxController, DPadButton.Direction.DOWN);

        auxRightStickUpButton = new JoystickAnalogButton(auxController, 5, -0.5);
        auxRightStickDownButton = new JoystickAnalogButton(auxController, 5, 0.5);
        auxRightStickLeftButton = new JoystickAnalogButton(auxController, 4, -0.5);
        auxRightStickRightButton = new JoystickAnalogButton(auxController, 4, 0.5);
        auxLeftStickUpButton = new JoystickAnalogButton(auxController, 1, -0.5);
        auxLeftStickDownButton = new JoystickAnalogButton(auxController, 1, 0.5);
        auxLeftStickLeftButton = new JoystickAnalogButton(auxController, 0, -0.5);
        auxLeftStickRightButton = new JoystickAnalogButton(auxController, 0, 0.5);
        auxLeftTriggerButton = new JoystickAnalogButton(auxController, 2, 0.5);
        auxRightTriggerButton = new JoystickAnalogButton(auxController,3, 0.5);

    }

    public CommandXboxController getDriveJoystick(){
        return driverController;
    }

    public double getDriveLeftYAxis(){
        return driverController.getRawAxis(1);
    }

    public double getDriveLeftXAxis() {
        return driverController.getRawAxis(0);
      }
    
      public double getDriveRightYAxis() {
        return driverController.getRawAxis(5);
      }
    
      public double getDriveRightXAxis() {
        return driverController.getRawAxis(4);
      }
    
      public double getDriveRightTriggerAxis() {
        return driverController.getRawAxis(3);
      }
    
      public double getDriveLeftTriggerAxis() {
        return driverController.getRawAxis(2);
      }

      public JoystickButton getDriveAButton() {
        return driveAButton;
      }
    
      public JoystickButton getDriveBButton() {
        return driveBButton;
      }
    
      public JoystickButton getDriveXButton() {
        return driveXButton;
      }
    
      public JoystickButton getDriveYButton() {
        return driveYButton;
      }
    
      public JoystickButton getDriveLeftBumper() {
        return driveLeftBumper;
      }
    
      public JoystickButton getDriveRightBumper() {
        return driveRightBumper;
      }
    
      public JoystickButton getDriveSelectButton() {
        return driveSelectButton;
      }
    
      public JoystickButton getDriveStartButton() {
        return driveStartButton;
      }

      public DPadButton getDriveDPadUp() {
        return driveDPadUpButton;
      }
    
      public DPadButton getDriveDPadDown() {
        return driveDPadDownButton;
      }
    
      public DPadButton getDriveDPadLeft() {
        return driveDPadLeftButton;
      }
    
      public DPadButton getDriveDPadRight() {
        return driveDPadRightButton;
      }

      public JoystickAnalogButton getDriveRightStickUpButton() {
        return driveRightStickUpButton;
      }
    
      public JoystickAnalogButton getDriveRightStickDownButton() {
        return driveRightStickDownButton;
      }
    
      public JoystickAnalogButton getDriveRightStickLeftButton() {
        return driveRightStickLeftButton;
      }
    
      public JoystickAnalogButton getDriveRightStickRightButton() {
        return driveRightStickRightButton;
      }
    
      public JoystickAnalogButton getDriveLeftStickUpButton() {
        return driveLeftStickUpButton;
      }
    
      public JoystickAnalogButton getDriveLeftStickDownButton() {
        return driveLeftStickDownButton;
      }
    
      public JoystickAnalogButton getDriveLeftStickLeftButton() {
        return driveLeftStickLeftButton;
      }
    
      public JoystickAnalogButton getDriveLeftStickRightButton() {
        return driveLeftStickRightButton;
      }
    
      public JoystickAnalogButton getDriveLeftTriggerButton() {
        return driveLeftTriggerButton;
      }
    
      public JoystickAnalogButton getDriveRightTriggerButton() {
        return driveRightTriggerButton;
      }


      public CommandXboxController getAuxJoystick(){
        return auxController;
    }

    public double getAuxLeftYAxis(){
        return auxController.getRawAxis(1);
    }

    public double getAuxLeftXAxis() {
        return auxController.getRawAxis(0);
      }
    
      public double getAuxRightYAxis() {
        return auxController.getRawAxis(5);
      }
    
      public double getAuxRightXAxis() {
        return auxController.getRawAxis(4);
      }
    
      public double getAuxRightTriggerAxis() {
        return auxController.getRawAxis(3);
      }
    
      public double getAuxLeftTriggerAxis() {
        return auxController.getRawAxis(2);
      }

      public JoystickButton getAuxAButton() {
        return auxAButton;
      }
    
      public JoystickButton getAuxBButton() {
        return auxBButton;
      }
    
      public JoystickButton getAuxXButton() {
        return auxXButton;
      }
    
      public JoystickButton getAuxYButton() {
        return auxYButton;
      }
    
      public JoystickButton getAuxLeftBumper() {
        return auxLeftBumper;
      }
    
      public JoystickButton getAuxRightBumper() {
        return auxRightBumper;
      }
    
      public JoystickButton getAuxSelectButton() {
        return auxSelectButton;
      }
    
      public JoystickButton getAuxStartButton() {
        return auxStartButton;
      }

      public DPadButton getAuxDPadUp() {
        return auxDPadUpButton;
      }
    
      public DPadButton getAuxDPadDown() {
        return auxDPadDownButton;
      }
    
      public DPadButton getAuxDPadLeft() {
        return auxDPadLeftButton;
      }
    
      public DPadButton getAuxDPadRight() {
        return auxDPadRightButton;
      }

      public JoystickAnalogButton getAuxRightStickUpButton() {
        return auxRightStickUpButton;
      }
    
      public JoystickAnalogButton getAuxRightStickDownButton() {
        return auxRightStickDownButton;
      }
    
      public JoystickAnalogButton getAuxRightStickLeftButton() {
        return auxRightStickLeftButton;
      }
    
      public JoystickAnalogButton getAuxRightStickRightButton() {
        return auxRightStickRightButton;
      }
    
      public JoystickAnalogButton getAuxLeftStickUpButton() {
        return auxLeftStickUpButton;
      }
    
      public JoystickAnalogButton getAuxLeftStickDownButton() {
        return auxLeftStickDownButton;
      }
    
      public JoystickAnalogButton getAuxLeftStickLeftButton() {
        return auxLeftStickLeftButton;
      }
    
      public JoystickAnalogButton getAuxLeftStickRightButton() {
        return auxLeftStickRightButton;
      }
    
      public JoystickAnalogButton getAuxLeftTriggerButton() {
        return auxLeftTriggerButton;
      }
    
      public JoystickAnalogButton getAuxRightTriggerButton() {
        return auxRightTriggerButton;
      }
}
