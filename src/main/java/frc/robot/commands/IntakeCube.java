// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj.Timer;

public class IntakeCube extends CommandBase {
  /** Creates a new GrabPosition. */
  double x;
  boolean flag;
  double onePos;

  public IntakeCube() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  

    //set target values (buffer added later)
    onePos = 2;
       
  }

  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      

        if(RobotContainer.intake.get_intake_encoder() >= (onePos + 2)){
          RobotContainer.intake.moveIntake(-0.19);
        }else if(RobotContainer.intake.get_intake_encoder() <= (onePos - 2)){
          RobotContainer.intake.moveIntake(0.19);
        }else{

          RobotContainer.intake.moveIntake(0.0);
          end(true);
        }
    }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.intake.moveIntake(0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
