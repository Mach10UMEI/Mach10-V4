// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj.Timer;


public class GrabPositionConeTeleop extends CommandBase {
  /** Creates a new GrabPosition. */
  double x;
  boolean flag;

  double onePos, twoPos, extendPos;
  public GrabPositionConeTeleop() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.pivotOne);
    addRequirements(RobotContainer.pivotTwo);
    addRequirements(RobotContainer.extend);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    flag=false;

    //set target values (buffer added later)
    onePos = 1;
    twoPos = -2; //-3          //-0.5 round
    extendPos = -24; //-26     //-36.7 round
  }



  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // ADJUSTING LENGTH VALUES MUST CHANGE OF EXTEND ENCODER
    //change x to actual values
    // System.out.println(flag);
    if(!flag){
      if(RobotContainer.extend.get_extend_encoder() <= -3.0){
        RobotContainer.extend.extend(0.8);
      }else{
        flag= true;
        RobotContainer.extend.extend(0.0);
      }
    }else{
        RobotContainer.extend.extend(0.0);
        flag= true;
      if(RobotContainer.pivotOne.get_first_pivot_encoder() >= (onePos + 2)){
        RobotContainer.pivotOne.move_pivot_one(-0.25);
      }else if(RobotContainer.pivotOne.get_first_pivot_encoder() <= (onePos - 2)){
        RobotContainer.pivotOne.move_pivot_one(0.25);
      }else{
        RobotContainer.pivotOne.move_pivot_one(0.0);
      }

      if(RobotContainer.intake.get_intake_encoder() >= (2 + 2)){
        RobotContainer.intake.moveIntake(-0.19);
      }else if(RobotContainer.intake.get_intake_encoder() <= (2 - 2)){
        RobotContainer.intake.moveIntake(0.19);
      }else{
        RobotContainer.intake.moveIntake(0.0);
      }

      if(RobotContainer.pivotTwo.get_second_pivot_encoder() >= (twoPos + 2)){
        RobotContainer.pivotTwo.move_pivot_two(-0.85);
      }else if(RobotContainer.pivotTwo.get_second_pivot_encoder() <= (twoPos - 2)){
        RobotContainer.pivotTwo.move_pivot_two(0.85);
      }else{
        RobotContainer.pivotTwo.move_pivot_two(0.0);
      }
      // System.out.println(((RobotContainer.pivotOne.get_first_pivot_encoder() >= (2.0-5.0)&&RobotContainer.pivotOne.get_first_pivot_encoder() <= (2.0+5.0))));
      if((RobotContainer.pivotTwo.get_second_pivot_encoder() <= (twoPos+5.0)&&RobotContainer.pivotTwo.get_second_pivot_encoder() >= (twoPos-5.0)) && (RobotContainer.pivotOne.get_first_pivot_encoder() >= (onePos-5.0)&&RobotContainer.pivotOne.get_first_pivot_encoder() <= (onePos+5.0))){
        if(RobotContainer.extend.get_extend_encoder() >= extendPos){

          RobotContainer.extend.extend(-0.80);
        }else{
          RobotContainer.extend.extend(0.0);
          end(true);
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.extend.extend(0.0);
    RobotContainer.pivotTwo.move_pivot_two(0.0);
    RobotContainer.pivotOne.move_pivot_one(0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
