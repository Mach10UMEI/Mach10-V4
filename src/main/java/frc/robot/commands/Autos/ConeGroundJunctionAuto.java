// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ConeGroundJunctionAuto extends CommandBase {
  
  double x;
  boolean flag;
  boolean pivotOne, pivotTwo, extend;
  double onePos, twoPos, extendPos;

  public ConeGroundJunctionAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.pivotOne);
    addRequirements(RobotContainer.pivotTwo);
    addRequirements(RobotContainer.extend);
    addRequirements(RobotContainer.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    //initializes flag values
    flag=false;
    pivotOne = false;
    pivotTwo = false;
    extend = false;

    //set target values (buffer added later)
    onePos = 24; //31 round
    twoPos = -37; //-23 round
    extendPos = -1;//-3 round

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // ADJUSTING LENGTH VALUES MUST CHANGE OF EXTEND ENCODER
    //change x to actual values
    // System.out.println(flag);
    if(!flag){
      if(RobotContainer.extend.get_extend_encoder() <= -3.0){
        RobotContainer.extend.extend(0.65);
      }else{
        flag= true;
        RobotContainer.extend.extend(0.0);
      }
    }else{
        RobotContainer.extend.extend(0.0);
        // flag= true;

      if(RobotContainer.pivotTwo.get_second_pivot_encoder() >= (twoPos + 2)){ //-1
        RobotContainer.pivotTwo.move_pivot_two(-0.7);
      }else if(RobotContainer.pivotTwo.get_second_pivot_encoder() <= (twoPos - 2)){ //-3
        RobotContainer.pivotTwo.move_pivot_two(0.7);
      }else{
        RobotContainer.pivotTwo.move_pivot_two(0.0);

        if(RobotContainer.pivotOne.get_first_pivot_encoder() >= (onePos + 2)){ //5
          RobotContainer.pivotOne.move_pivot_one(-0.19);
        }else if(RobotContainer.pivotOne.get_first_pivot_encoder() <= (onePos - 2)){ //-2
          RobotContainer.pivotOne.move_pivot_one(0.19);
        }else{
          RobotContainer.pivotOne.move_pivot_one(0.0);
        }

        RobotContainer.intake.intake_out_of_way();
      }

      //check if pivots are in place
      if(RobotContainer.pivotTwo.get_second_pivot_encoder() <= (twoPos+2.0)&&RobotContainer.pivotTwo.get_second_pivot_encoder() >= (twoPos-2.0)){
        pivotTwo = true;
      }
      if(RobotContainer.pivotOne.get_first_pivot_encoder() >= (onePos-2.0)&&RobotContainer.pivotOne.get_first_pivot_encoder() <= (onePos+2.0)){
        pivotOne = true;
      }

      if(pivotOne && pivotTwo){
        if(RobotContainer.extend.get_extend_encoder() >= extendPos){
          RobotContainer.extend.extend(-0.8);
        }else{
          RobotContainer.extend.extend(0.0);
        }
      }

      //check if extended and pivots are in place
      if((RobotContainer.extend.get_extend_encoder() < (extendPos + 2)) && pivotOne && pivotTwo){
        extend = true;
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
    return (pivotOne && pivotTwo && extend); //checks if everything is in position
  }
}
