// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDStrips extends SubsystemBase {

  private final AddressableLED rightLED, leftLED;
  private final AddressableLEDBuffer rightLEDBuffer, leftLEDBuffer;

  public LEDStrips() {

    rightLED = new AddressableLED(0);
    leftLED = new AddressableLED(1);

    rightLEDBuffer = new AddressableLEDBuffer(10);
    leftLEDBuffer = new AddressableLEDBuffer(10);

    rightLED.setLength(rightLEDBuffer.getLength());
    leftLED.setLength(leftLEDBuffer.getLength());

    rightLED.setData(leftLEDBuffer);
    leftLED.setData(rightLEDBuffer);

    rightLED.start();
    leftLED.start();
//set lights to orange 
    for (var i = 0; i < rightLEDBuffer.getLength(); i++) {
      rightLEDBuffer.setRGB(i, 245, 119, 22);
   }
   for (var i = 0; i < leftLEDBuffer.getLength(); i++) {
    leftLEDBuffer.setRGB(i, 245, 119, 22);
  }
   rightLED.setData(rightLEDBuffer);
   leftLED.setData(leftLEDBuffer);

  }

  public void setLightsToYellow(){
    for (var i = 0; i < rightLEDBuffer.getLength(); i++) {
      rightLEDBuffer.setRGB(i, 250, 206, 45);
   }
   for (var i = 0; i < leftLEDBuffer.getLength(); i++) {
    leftLEDBuffer.setRGB(i, 250, 206, 45);
  }
   rightLED.setData(rightLEDBuffer);
   leftLED.setData(leftLEDBuffer);

  }


  public void setLightsToYellowBlink(){
    for (var i = 0; i < rightLEDBuffer.getLength(); i++) {
      rightLEDBuffer.setRGB(i, 250, 206, 45);
   }
   for (var i = 0; i < leftLEDBuffer.getLength(); i++) {
    leftLEDBuffer.setRGB(i, 250, 206, 45);
  }
  
  Timer.delay(0.3);
  
  rightLED.setData(rightLEDBuffer);
  
  Timer.delay(0.3);
  
  leftLED.setData(leftLEDBuffer);

  } 


  @Override
  public void periodic() {



  }
}
