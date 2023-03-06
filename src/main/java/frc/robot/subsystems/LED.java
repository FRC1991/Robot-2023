// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
  /** Creates a new LED. */

  private AddressableLED lights;
  private AddressableLEDBuffer lightsBuffer;

  public LED() {

    lights = new AddressableLED(0);
    lightsBuffer = new AddressableLEDBuffer(20);

    lights.setLength(lightsBuffer.getLength());

    lights.setData(lightsBuffer);
    lights.start();
    
  }

  public void setToOrange(){
  for (var i = 0; i < lightsBuffer.getLength(); i++) {
    lightsBuffer.setRGB(i, 255, 149, 27);
 }
 
 lights.setData(lightsBuffer);
}
  
}
