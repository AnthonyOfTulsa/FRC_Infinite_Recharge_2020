/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ConstantsPorts;

public class LifterSubsystem extends SubsystemBase {

  Solenoid intakeSolenoid;

  public LifterSubsystem() {
    intakeSolenoid = new Solenoid(ConstantsPorts.intakeLifterPort);
  }

  /** Sets the lifter solenoid to an inputted value 
   * 
   * @param set  True will set the solenoid to on
   *             False will set the solenoid to off
  */
  public void setLifter(boolean set) {
    intakeSolenoid.set(set);
  }

  /** Sets the lifter to on/deployed (true) */
  public void deployLifter() {
    intakeSolenoid.set(true);
  }

  /**Sets the lifter to off/retracted (false) */
  public void retractLifter() {
    intakeSolenoid.set(false);
  }

  /**Sets the lifter to the opposite value of the current value 
   * Ex. if solenoid is on, this will set it to off
  */
  public void toggleLifter() {
    intakeSolenoid.set(!intakeSolenoid.get());
  }

  /**Returns the current state of the lifter 
   * 
   * @return returns either true or false
   *         true = on
   *         false = off
  */
  public boolean getCurrentState() { 
    return intakeSolenoid.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
