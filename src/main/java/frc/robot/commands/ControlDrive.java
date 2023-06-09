// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ControlDrive extends CommandBase {
  /** Creates a new ControlDrive. */
  public ControlDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  RobotContainer.drive.coast_mode();

  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = RobotContainer.getLeftY(RobotContainer.driveController);
    double turn = RobotContainer.getLeftX(RobotContainer.driveController);
    //add a decimal mutipler to change max speed
    RobotContainer.drive.arcadeDrive(speed*1.0, turn*0.9);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
