// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommand;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/** Add your docs here. */
public class TracjectoryFollowing {

    public Command TracjectoryFollowingTest(){

        var autoVoltageConstraint = 
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.ksVolt,
             Constants.kvVolt,
             Constants.kaVolt),
             Constants.kDriveKinematics,
              10);
    
        TrajectoryConfig config = 
            new TrajectoryConfig(
                Constants.kMaxAcceleration, 
                Constants.kMaxSpeed)
                .setKinematics(Constants.kDriveKinematics)
                .addConstraint(autoVoltageConstraint);

        Trajectory exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                new Pose2d(0, 0, new Rotation2d(0)),
                List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                new Pose2d(3, 0, new Rotation2d(0)),
                config);


        RamseteCommand ramseteCommand = 
        new RamseteCommand(exampleTrajectory, 
        RobotContainer.mDrivetrain::getPose2d,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolt, Constants.kvVolt, Constants.kaVolt),
         Constants.kDriveKinematics,
        RobotContainer.mDrivetrain::getWheelSpeeds, 
        new PIDController(Constants.kpVolt, 0, 0), 
        new PIDController(Constants.kpVolt, 0, 0),
         RobotContainer.mDrivetrain::tankDriveVolts, 
         RobotContainer.mDrivetrain);
                
        
        RobotContainer.mDrivetrain.resetOdometry(exampleTrajectory.getInitialPose());

        return ramseteCommand.andThen(() -> RobotContainer.mDrivetrain.tankDriveVolts(0, 0));
        
        
        
    }
}
