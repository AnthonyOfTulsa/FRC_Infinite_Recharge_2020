/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class ColorWheelRunToPosition extends CommandBase {
  
  ColorWheelSubsystem colorWheelSubsystem;
  double rotationsToRotate;
  boolean isFinished = false;

  public ColorWheelRunToPosition(double rotationsToRotate, ColorWheelSubsystem sub) {
    colorWheelSubsystem = sub;
    this.rotationsToRotate = rotationsToRotate;
    addRequirements(sub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    colorWheelSubsystem.resetPID(); // Restarts the PID calculations
    colorWheelSubsystem.resetEncoder(); // Reset the current encoder
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double motorSpeed = colorWheelSubsystem.getMotorSpeedPID(colorWheelSubsystem.getCurrentEncoderRotations(), rotationsToRotate);
    if(colorWheelSubsystem.atSetpoint()) {
      isFinished = true;
    } else {
      colorWheelSubsystem.setColorMotorSpeed(motorSpeed); 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    colorWheelSubsystem.stopColorMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}