/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import frc.robot.commands.ShooterCommands.*;
import frc.robot.commands.DriveTank;
import frc.robot.commands.ClimbCommands.*;
import frc.robot.commands.ClimbCommands.SetRopePosition.SetOrAngle;
import frc.robot.commands.IntakeCommands.RunIntake;
import frc.robot.commands.IntakeCommands.SweepIntake;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.*;
import frc.robot.commands.SolenoidSetsAndToggles.CompressIntake;
import frc.robot.commands.SolenoidSetsAndToggles.ReleaseIntake;
import frc.robot.commands.SolenoidSetsAndToggles.ToggleLifter;
import frc.robot.commands.SolenoidSetsAndToggles.ToggleLifterAndPanel;
import frc.robot.commands.SolenoidSetsAndToggles.TogglePanel;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //Limelights and Network Tables
  NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

  //Subsystems
  private final AutoAimSubsystem autoAimSubsystem = new AutoAimSubsystem(limelight);
  private final ClimbSubsystem climbSubsystem = new ClimbSubsystem();
  private final ColorWheelSubsystem colorWheelSubsystem = new ColorWheelSubsystem();
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IndexSubsystem indexSubsystem = new IndexSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final LifterSubsystem lifterSubsystem = new LifterSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem(limelight);
  

  // Commands
  // private final TankDriveCommand tankDriveCommand;

  // Controllers
  private final XboxController driverController = new XboxController(ConstantsOI.driverPort);
  private final XboxController operatorController = new XboxController(ConstantsOI.operatorPort);
  
  // Driver buttons and triggers
  TriggerAxis driverLeftTrigger = new TriggerAxis(driverController, Hand.kLeft, .1);
  TriggerAxis driverRightTrigger = new TriggerAxis(driverController, Hand.kRight, .1);
  JoystickButton driverLeftBumper = new JoystickButton(driverController, Button.kBumperLeft.value);
  JoystickButton driverRightBumper = new JoystickButton(driverController, Button.kBumperRight.value);
  JoystickButton driverAButton = new JoystickButton(driverController, Button.kA.value);
  JoystickButton driverBButton = new JoystickButton(driverController, Button.kB.value);
  JoystickButton driverXButton = new JoystickButton(driverController, Button.kX.value);
  JoystickButton driverYButton = new JoystickButton(driverController, Button.kY.value);
  JoystickButton driverBackButton = new JoystickButton(driverController, Button.kBack.value);
  JoystickButton driverStartButton = new JoystickButton(driverController, Button.kStart.value);
  POVButton driverDpadUp = new POVButton(driverController, 0);
  POVButton driverDpadRight = new POVButton(driverController, 90);
  POVButton driverDpadDown = new POVButton(driverController, 180);
  POVButton driverDpadLeft = new POVButton(driverController, 270);

  // Operator buttons and triggers
  TriggerThumbstick operatorLeftStickX = new TriggerThumbstick(operatorController, XboxController.Axis.kLeftX, 0.05);
  TriggerThumbstick operatorRightStickY = new TriggerThumbstick(operatorController, XboxController.Axis.kRightY, 0.05);
  TriggerAxis operatorLeftTrigger = new TriggerAxis(operatorController, Hand.kLeft, .1);
  TriggerAxis operatorRightTrigger = new TriggerAxis(operatorController, Hand.kRight, .1);
  JoystickButton operatorLeftBumper = new JoystickButton(operatorController, Button.kBumperLeft.value);
  JoystickButton operatorRightBumper = new JoystickButton(operatorController, Button.kBumperRight.value);
  JoystickButton operatorAButton = new JoystickButton(operatorController, Button.kA.value);
  JoystickButton operatorBButton = new JoystickButton(operatorController, Button.kB.value);
  JoystickButton operatorXButton = new JoystickButton(operatorController, Button.kX.value);
  JoystickButton operatorYButton = new JoystickButton(operatorController, Button.kY.value);
  JoystickButton operatorBackButton = new JoystickButton(operatorController, Button.kBack.value);
  JoystickButton operatorStartButton = new JoystickButton(operatorController, Button.kStart.value);
  POVButton operatorDpadUp = new POVButton(operatorController, 0);
  POVButton operatorDpadRight = new POVButton(operatorController, 90);
  POVButton operatorDpadDown = new POVButton(operatorController, 180);
  POVButton operatorDpadLeft = new POVButton(operatorController, 270);

 
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    driveSubsystem.setDefaultCommand(new DriveTank(
      () -> driverController.getRawAxis(ConstantsOI.driverRightDriveAxis),
      () -> driverController.getRawAxis(ConstantsOI.driverLeftDriveAxis),
      driveSubsystem));

    shooterSubsystem.setShooterTarget(0);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //DRIVER BINDS

    //TODO Drive From left and right Y joysticks

    //TODO Left Trigger: Sweep

    //TODO Right Trigger: IntakeIn (Just intake and possibly funnel?)

    // Toggle lifter and panel (driver left bumper)
    operatorLeftBumper.whenPressed(new ToggleLifterAndPanel(lifterSubsystem));

    // Toggle lifter (driver x button)
    driverXButton.whenPressed(new ToggleLifter(lifterSubsystem));
     
    // Toggle panel (driver b button)
    driverBButton.whenPressed(new TogglePanel(lifterSubsystem));

    //TODO POV Down: Reset Climb Release (ALL)

    //TODO POV Left: Tgl Rope Servo

    //TODO POV Right: Tgl Climb Spring Release



    //OPERATOR BINDS

    //TODO Left Stick X: Manual Pan for target
    operatorLeftStickX.whileActiveContinuous(new AdjustPan(() -> operatorController.getRawAxis(XboxController.Axis.kLeftX), autoAimSubsystem));

    //TODO Right Stick Y: Manual Tilt for target
    operatorRightStickY.whileActiveContinuous(new AdjustTilt(() -> -operatorController.getRawAxis(XboxController.Axis.kRightY), autoAimSubsystem));

    //TODO Left Trigger: Run Index In (not for shooting but rather for when intaking balls)

    //TODO Right Trigger: Set the Target RPM based on the cycleSpeedSelector or if Auto aim active
    //Based on auto aim

    //TODO Left Bumper: Run the funnel only at a lowerish speed

    //TODO Right Bumper: Cycle between three different target RPMs

    //TODO A: Run the AutoAim Home command to manually automatically recenter aim

    // Release climb (operator y button)
    operatorYButton.whenPressed(new ReleaseClimb(climbSubsystem));

    //TODO X: Lock Climb Pistion (CURRENTLY UNUSED AS THERE IS NO LOCK PISTON)

    //TODO Menu: Tgl Auto Velocity

    //TODO Back: Tgl Auto Aim

    //TODO POV up: Tgl the ctrl panel piston (CURRENTLY UNUSED)

    //TODO POV Left: Run Rotate (Ctrl Panel)

    //TODO POV Right: Run Position (Ctrl Panel)

    //TODO POV Down: Our alt trigger. Dont acutally run a command with this
    //Rather use .and() on a button/trigger with this to activate a command

    //ALT COMMANDS

    //TODO Y ALT: Reset Climb (both piston and servo)

    //TODO X ALT: Unlock Climb (CURRENTLY UNUSED)

  }

  //Interfacing command for to 
  public void scheduleClimbLock() {
    new InstantCommand(climbSubsystem::lockClimb, climbSubsystem).schedule();
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
