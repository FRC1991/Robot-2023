// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.OI;

import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;

/** Add your docs here. */
public class JoystickAnalogButton {
    
    CommandGenericHID m_joystick;
    int m_axisNumber;
    private double THRESHOLD = 0.5;


    public JoystickAnalogButton(CommandGenericHID joystick, int axisNumber){
        m_joystick = joystick;
        m_axisNumber = axisNumber;
    }

    public JoystickAnalogButton(CommandGenericHID joystick, int axisNumber, double threshold){
        
        m_joystick = joystick;
        m_axisNumber = axisNumber;
        THRESHOLD = threshold;
    }

    public void setThreshold(double threshold){
        THRESHOLD = threshold;
    }

    public double getThreshold(){
        return THRESHOLD;
    } 

    public boolean get(){
        if(THRESHOLD < 0){
            return m_joystick.getRawAxis(m_axisNumber) <  THRESHOLD;
        }else{
            return m_joystick.getRawAxis(m_axisNumber) > THRESHOLD;
        }
    }
}
