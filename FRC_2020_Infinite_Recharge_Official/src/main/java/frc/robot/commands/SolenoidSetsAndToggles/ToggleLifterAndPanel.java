/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.SolenoidSetsAndToggles;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LifterSubsystem;

public class ToggleLifterAndPanel extends CommandBase {

  LifterSubsystem lifterSubsystem;
  boolean isFinished = false;

  public ToggleLifterAndPanel(LifterSubsystem lifterSubsystem) {
    this.lifterSubsystem = lifterSubsystem;
    addRequirements(lifterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lifterSubsystem.toggleLifter();
    lifterSubsystem.togglePanel();
    isFinished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
