# FRC Team 1991 The Dragons Robot for 2023
--------------------------------------------------------------
This is a summary of the our robot's software and hardware. Feel free to find us and ask for more information!!! Also go find out even more about us on our new [website](https://team1991-grommet.vercel.app/). 

## I want to learn about...

- [Quick information for scouting](#Quick-scouting-information)
- [The electronics](#Electronics)
- [The build/CAD](#Build-CAD)
- [The software](#Software)
- [The team](#Team)

### Electronics

For this year we transitioned over to the [REV PDH](https://www.revrobotics.com/rev-11-1850/). We used a total of 13 [neos](https://www.revrobotics.com/rev-21-1650/) and 1 [neo 550](https://www.revrobotics.com/rev-21-1651/) all controlled by [Spark max motor controllers](https://www.revrobotics.com/rev-11-2158/). 6 of these motors are on the drivetrain. 2 are used to lift the arm. 1 is used to extend out our arm. 2 are on the turret. 1 is to rotate our claw and the final motor a neo 550 is used to open and close the claw. We also a total of 5 [physical limit switches](https://www.grainger.com/product/6X292?gucid=N:N:PS:Paid:GGL:CSM-2295:4P7A1P:20501231&gclid=CjwKCAiAr4GgBhBFEiwAgwORrT3ftxPCb0evrn4rLCS0Aeh5ITvAahquKXvPsfDpjy86tjAc203kpBoC9pIQAvD_BwE&gclsrc=aw.ds) and 2 [IR beam sensors](https://learn.adafruit.com/ir-breakbeam-sensors). The limit switches are used to make sure we do not over extend any part of our robot while the IR sensors are used to center our 2 turrets. We also used two [limelights 2+](https://limelightvision.io/collections/products/products/limelight-2-plus) to wire these up we used a [TP link ethernet switch](https://www.amazon.com/TP-Link-Ethernet-Splitter-Unmanaged-TL-SF1005D/dp/B000FNFSPY/ref=asc_df_B000FNFSPY/?tag=hyprod-20&linkCode=df0&hvadid=309707619534&hvpos=&hvnetw=g&hvrand=5951270540427691936&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9003242&hvtargid=pla-352077286959&psc=1) to overcome the one port limitation on the [radio](https://www.andymark.com/products/open-mesh-om5p-ac-dual-band-1-17-gbps-access-point-radio). The radio and limelights were wired using [POE injectors](https://www.revrobotics.com/rev-11-1210/). For our gyro we used the [Pigeon 2.0](https://store.ctr-electronics.com/pigeon-2/). We also used [wago connectors](amazon.com/221-2401-WAGO-Lever-Nuts®-2-Conductor-Transparent/dp/B0BKR2SR7H/ref=sr_1_8?gclid=CjwKCAiAr4GgBhBFEiwAgwORrZ19wbIXQFmEIOo78psZ8gifpKCuclsqIOggxwHXLDQYCLN_y2NBBxoCZJYQAvD_BwE&hvadid=236856702852&hvdev=c&hvlocphy=9003242&hvnetw=g&hvqmt=e&hvrand=18177757179304087033&hvtargid=kwd-390596553309&hydadcr=25968_9904055&keywords=wago+electrical+wire+connectors&qid=1677777452&sr=8-8) everywhere we could we highly reccomend these connectors they save an incredible amount of time. Finally we ran everything though a cable chase. This is it for electonics come to our pit for more infomation. We would be happy to help.

### Software

Our code base has more than 4600 lines of code!!! For the software our entire robot is programmed in Java using the WPILib library. We used the command based paradigm for its cleanliness. All our code for this robot is avaible in [this github repository](https://github.com/FRC1991/Robot-2023). The code will be explained by elaborating on each important file separately. First our robot container holds our button bindings, networktables and shuffleboard code. As a feature we have an automatic autonomous chooser dependent on our position which we pull in from the FMS. Our Robot file just checks which alliance we are on so we know which tags to focus on(useage explained more later) and puts the motor in coast mode once the match ends. Our constants have all the PID constants and motor controller constants. The ButtonBind file holds all the bindings for the controller which we can supply to the commands and has a feature that talks to the driver using controller rumbles. Our subsystems are very standard feel free to check them out as you please! We also have some special vision commands which enable us to follow not only the targets but also the game pieces(Reason for two limelights). Using commands that turn till we see a tag/game piece and run to that tag/game piece till we center on it. Since we have multiple targets to follow we have an auto pipeline switch method making it easier for the drivers to control the robot. We also communicate with human player and drivers using our LED strips. Our drive method is called gameDrive which makes it so the triggers control forward and backwards movement while a joystick controls turning and holding the bumper with joystick enables in place turning(just like a game). There will a control mapping picture available soon. There is still a lot more to our code but this should show some features that differentiate us from other teams. Feel free to explore our github repository we made it public for a reason!

### Build-CAD

For the build starting from the drivetrain. We used two neo evo slims gearboxes the wheels are 2 slick tires in the front and 2 grip wheels in the back with smaller free floating wheels in the middle. Several part of our robot use laser cut parts from our sponsor [edro](https://www.edrocorp.com/). Many of our more complex parts are made by 3D printing on the [Onyx pro](https://www.matterhackers.com/store/l/markforged-onyx-pro-3d-printer/sk/M7G3XMM6?rcode=PMAX_PMax1&gclid=CjwKCAiAr4GgBhBFEiwAgwORrX6WDvfifAeTQ98QWj7aabm0clXXFauPA13wlM_1gyVPIr6BTqrYzxoCDzwQAvD_BwE) with fiberglass lines and carbon fiber filament. A lot of the robot was resdesigned from our first iteration due to weight distribution concerns. We used our CAD sponsor [solidworks](https://www.solidworks.com/). We also used their cutting-edge software to simulate the movements of our robot. Some of our early designed looked like this. ![bot pic 4 Background Removed](https://user-images.githubusercontent.com/77414408/222869248-86d48e6e-ddd1-42aa-9896-6272fef4801c.png)



### Team

These are quick profiles on our mentors and team leaders.

 #### ***Mentors***
- Jay Kean
    - Fun Fact: 
    - Hobbies: 
- Eileen Kean
    - Fun Fact: Former team captain.
    - Hobbies: Legos, Watching baseball

 #### ***Student Leaders***
 - Build Captain: Pacey Mahar
    - Fun Fact: Knows how to play bugle.
    - Hobbies: Re-enacting, Costume design.
 - Electric/Programming Captain: Maanit Malhan
    - Fun Fact: Changed schools eight times(My parents hobby is moving).
    - Hobbies: Cars, Soccer.
 - Design Co-Leader: Abhijith Tippireddy
    - Fun Fact: Knows how to horse ride.
    - Hobbies: Running, field hockey.
 - Design Co-Leader: Justin Surace
    - Fun Fact: Black Belt.
    - Hobbies: Sim Racing, Auto-Cross.
 - Safety Captain: Catherine Hockenhull
    - Fun Fact: Knows how to crochet.
    - Hobbies: Running, Reading.
 - Corprate Captain: Nasere Lewin
    - Fun Fact: Like to build things.
    - Hobbies: Lego, Drawing.



