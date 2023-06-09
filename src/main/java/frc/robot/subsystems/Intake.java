// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  // can05

  CANSparkMax intakeMotor;
  public Intake() {
    intakeMotor = new CANSparkMax(Constants.INTAKE_ID, MotorType.kBrushless);
    intakeMotor.setIdleMode(IdleMode.kBrake);
    intakeMotor.restoreFactoryDefaults();
  }

  public void moveIntake(double speed){
    intakeMotor.set(speed);
  }

  public double get_intake_encoder() {
    return intakeMotor.getEncoder().getPosition();
  }

  public void intake_out_of_way() {
    intakeMotor.getEncoder().setPosition(-1.0);
  }

  public void home_intake_encoder(){
    intakeMotor.getEncoder().setPosition(0.0);
  }
  public void coast_mode(){
    intakeMotor.setIdleMode(IdleMode.kCoast);
  }
  public void brake_mode(){
    intakeMotor.setIdleMode(IdleMode.kBrake);
  }
  



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
