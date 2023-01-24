// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.OI;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/** Add your docs here. */
public class DPadButton {

    CommandXboxController joystick;
    Direction direction;

    public DPadButton(CommandXboxController joystick, Direction direction){
        this.joystick = joystick;
        this.direction = direction;
    }

    public static enum Direction {
        UP(0),
        RIGHT(90),
        DOWN(180),
        LEFT(270);

        int direction;

        private Direction(int direction){
            this.direction = direction;
        }
    }

    public boolean get(){
        int DPadValue = joystick.getHID().getPOV();
        return (DPadValue == direction.direction)
        ||  (DPadValue == (direction.direction + 45) % 360)
        ||  (DPadValue == (direction.direction + 315) % 360);
    }
    
}
