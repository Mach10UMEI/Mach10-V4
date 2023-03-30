// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AutoLevel extends CommandBase {
  /** Creates a new AutoLevel. */
  public AutoLevel() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.gyro);
    addRequirements(RobotContainer.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  RobotContainer.drive.brake_mode();
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.gyro.getRoll() <-10.5){
      RobotContainer.drive.arcadeDrive(-0.34, 0.0);
    }else if(RobotContainer.gyro.getRoll() > 10.5){
      RobotContainer.drive.arcadeDrive(0.34, 0.0);
    }

    if(RobotContainer.intake.get_intake_encoder() >= -2){
      RobotContainer.intake.moveIntake(-0.25);
    }else if(RobotContainer.intake.get_intake_encoder() <= -2){
      RobotContainer.intake.moveIntake(0.25);
    }else{
      RobotContainer.intake.moveIntake(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drive.arcadeDrive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
