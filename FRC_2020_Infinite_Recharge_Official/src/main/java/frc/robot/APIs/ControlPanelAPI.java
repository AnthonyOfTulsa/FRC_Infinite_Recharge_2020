package frc.robot.APIs;

public class ControlPanelAPI {

    // Declare some constants
    public static final int DIAMETER = 32; // The diameter of the control panel in inches
    public static final double CIRCUMFERENCE = 100.541; // The circumference of the control panel in inches

    // Sets names of colors for easy programming. DO NOT CHANGE
    public static final int COLOR_BLUE = 1;
    public static final int COLOR_GREEN = 2;
    public static final int COLOR_RED = 3;
    public static final int COLOR_YELLOW = 4;

    // RGB values for the red color
    public static final double RED_THRESHOLD_RED = 255;
    public static final double GREEN_THRESHOLD_RED = 0;
    public static final double BLUE_THRESHOLD_RED = 0;

    // RGB values for the yellow color
    public static final double RED_THRESHOLD_YELLOW = 255;
    public static final double GREEN_THRESHOLD_YELLOW = 255;
    public static final double BLUE_THRESHOLD_YELLOW = 255;

    // RGB values for the blue color
    public static final double RED_THRESHOLD_BLUE = 255;
    public static final double GREEN_THRESHOLD_BLUE = 255;
    public static final double BLUE_THRESHOLD_BLUE = 255;

    // RGB values for the green color
    public static final double RED_THRESHOLD_GREEN = 255;
    public static final double GREEN_THRESHOLD_GREEN = 255;
    public static final double BLUE_THRESHOLD_GREEN = 255;

    public static final double THRESHOLD_RANGE = 5;

    double distanceFromCenterInInches = 0; // This takes the distance of the center of the contact from the edge of the control panel wheel
    double motorMaxSpeed = 0; // This is the speed that the motor will spin at to align the wheel
    int gearboxRatio = 1; // The ratio of the gearbox the motor is using as x:1
    double wheelDiameterInInches = 2; // The diameter of the wheel touching the control panel given in inches
    int countsPerRevolution = 1000; // The amount of encoder counts per revolution of the motor

    public ControlPanelAPI(double inputdistanceFromCenterInInches, double inputMotorMaxSpeed, int inputGearboxRatio, double inputwheelDiameterInInches, int inputCountsPerRevolution) {
        distanceFromCenterInInches = inputdistanceFromCenterInInches; // The distance from the center of the control panel to the wheel rotating the control panel given in inches
        motorMaxSpeed = inputMotorMaxSpeed;
        gearboxRatio = inputGearboxRatio;
        wheelDiameterInInches = inputwheelDiameterInInches;
        countsPerRevolution = inputCountsPerRevolution;
    }

    // Change the color from a char to an int
    public static int convertColorToInt(char colorAsChar) {
        int colorAsInt = 0;
        if(colorAsChar == 'b') {
            colorAsInt = COLOR_BLUE;
        } else if(colorAsChar == 'g') {
            colorAsInt = COLOR_GREEN;
        } else if(colorAsChar == 'r') {
            colorAsInt = COLOR_RED;
        } else if(colorAsChar == 'y'){
            colorAsInt = COLOR_YELLOW;
        }
        return colorAsInt;
    }

    // Calculate what color we are on based on RGB inputs
    public static int calculateCurrentColor(double red, double green, double blue){
        int color = 0;
        // See if the RGB values match those of the color RED
        if(red >= (RED_THRESHOLD_RED-THRESHOLD_RANGE) && red <= (RED_THRESHOLD_RED+THRESHOLD_RANGE)) {
            if(green >= (GREEN_THRESHOLD_RED-THRESHOLD_RANGE) && green <= (GREEN_THRESHOLD_RED+THRESHOLD_RANGE)) {
                if(blue >= (BLUE_THRESHOLD_RED-THRESHOLD_RANGE) && blue <= (BLUE_THRESHOLD_RED+THRESHOLD_RANGE)) {
                    color = COLOR_RED;
                    return color;
                }
            }
        }

        // Copy the above logic for the other colors
        //See if the RGB values match those of the color YELLOW
        if(red >= (RED_THRESHOLD_YELLOW-THRESHOLD_RANGE) && red <= (RED_THRESHOLD_YELLOW+THRESHOLD_RANGE)) {
            if(green >= (GREEN_THRESHOLD_YELLOW-THRESHOLD_RANGE) && green <= (GREEN_THRESHOLD_YELLOW+THRESHOLD_RANGE)) {
                if(blue >= (BLUE_THRESHOLD_YELLOW-THRESHOLD_RANGE) && blue <= (BLUE_THRESHOLD_YELLOW+THRESHOLD_RANGE)) {
                    color = COLOR_YELLOW;
                    return color;
                }
            }
        }

        //See if the RGB values match those of the color BLUE
        if(red >= (RED_THRESHOLD_BLUE-THRESHOLD_RANGE) && red <= (RED_THRESHOLD_BLUE+THRESHOLD_RANGE)) {
            if(green >= (GREEN_THRESHOLD_BLUE-THRESHOLD_RANGE) && green <= (GREEN_THRESHOLD_BLUE+THRESHOLD_RANGE)) {
                if(blue >= (BLUE_THRESHOLD_BLUE-THRESHOLD_RANGE) && blue <= (BLUE_THRESHOLD_BLUE+THRESHOLD_RANGE)) {
                    color = COLOR_BLUE;
                    return color;
                }
            }
        }

        //See if the RGB values match those of the color GREEN
        if(red >= (RED_THRESHOLD_GREEN-THRESHOLD_RANGE) && red <= (RED_THRESHOLD_GREEN+THRESHOLD_RANGE)) {
            if(green >= (GREEN_THRESHOLD_GREEN-THRESHOLD_RANGE) && green <= (GREEN_THRESHOLD_GREEN+THRESHOLD_RANGE)) {
                if(blue >= (BLUE_THRESHOLD_GREEN-THRESHOLD_RANGE) && blue <= (BLUE_THRESHOLD_GREEN+THRESHOLD_RANGE)) {
                    color = COLOR_GREEN;
                    return color;
                }
            }
        }
        return color;
    }

    //Calculate method for if we are on our side of the color wheel
    public double calculateOurSide(char targetColorAsChar) {
        double outputMotorSpeed = 0;
        double compensatedSpinDistance = 0;
        double motorSpinDistanceInEncoders = 0; // The amount of ticks the motor should spin
        int targetColor = convertColorToInt(targetColorAsChar);        
        int currentRobotColor = calculateCurrentColor(255, 0, 0); // Add methods to get the real RGB values
        int currentWheelColor = 0;
        double redDistance = 0;
        double yellowDistance = 0;
        double blueDistance = 0;
        double greenDistance = 0;
        double motorSpinDistanceInRotations = 0;

        // Sets the distances of each color relative to the wheel's current location
        if(currentRobotColor == COLOR_RED) {
            currentWheelColor = COLOR_BLUE;
            redDistance = -0.25*CIRCUMFERENCE;
            yellowDistance = -0.125*CIRCUMFERENCE;
            blueDistance = 0*CIRCUMFERENCE;
            greenDistance = 0.125*CIRCUMFERENCE;
        } else if(currentRobotColor == COLOR_YELLOW) {
            currentWheelColor = COLOR_GREEN;
            redDistance = 0.125*CIRCUMFERENCE;
            yellowDistance = -0.25*CIRCUMFERENCE;
            blueDistance = -0.125*CIRCUMFERENCE;
            greenDistance = 0*CIRCUMFERENCE;
        } else if(currentRobotColor == COLOR_BLUE) {
            currentWheelColor = COLOR_RED;
            redDistance = 0*CIRCUMFERENCE;
            yellowDistance = 0.125*CIRCUMFERENCE;
            blueDistance = -0.25*CIRCUMFERENCE;
            greenDistance = -0.125*CIRCUMFERENCE;
        } else if(currentRobotColor == COLOR_GREEN) {
            currentWheelColor = COLOR_YELLOW;
            redDistance = -0.125*CIRCUMFERENCE;
            yellowDistance = 0*CIRCUMFERENCE;
            blueDistance = 0.125*CIRCUMFERENCE;
            greenDistance = -0.25*CIRCUMFERENCE;
        }

        //  Calculate distances based on the target color
        if(targetColor == COLOR_RED) {
            compensatedSpinDistance = (redDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        } else if(targetColor == COLOR_YELLOW) {
            compensatedSpinDistance = (yellowDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        } else if(targetColor == COLOR_BLUE) {
            compensatedSpinDistance = (blueDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        } else if(targetColor == COLOR_GREEN) {
            compensatedSpinDistance = (greenDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        }

        motorSpinDistanceInRotations = motorSpinDistanceInRotations/countsPerRevolution;

        if(motorSpinDistanceInRotations > 0){
            outputMotorSpeed = motorMaxSpeed;
        } else if(motorSpinDistanceInRotations < 0){
            outputMotorSpeed = -motorMaxSpeed;
        } else {
            outputMotorSpeed = 0;
        }

        return outputMotorSpeed;
    }

    // Calculate method for if we are on our enemies side of the color wheel
    public double calculateEnemySide(char targetColorAsChar) {
        double outputMotorSpeed = 0;
        double compensatedSpinDistance = 0;
        double motorSpinDistanceInEncoders = 0; // The amount of ticks the motor should spin
        int targetColor = convertColorToInt(targetColorAsChar);        
        int currentRobotColor = calculateCurrentColor(255, 0, 0); // Add methods to get the real RGB values
        int currentWheelColor = 0;
        double redDistance = 0;
        double yellowDistance = 0;
        double blueDistance = 0;
        double greenDistance = 0;
        double motorSpinDistanceInRotations =0;

        // Sets the distances of each color relative to the wheel's current location
        if(currentRobotColor == COLOR_RED) {
            currentWheelColor = COLOR_BLUE;
            redDistance = 0.25*CIRCUMFERENCE;
            yellowDistance = 0.125*CIRCUMFERENCE;
            blueDistance = 0*CIRCUMFERENCE;
            greenDistance = -0.125*CIRCUMFERENCE;
        } else if(currentRobotColor == COLOR_YELLOW) {
            currentWheelColor = COLOR_GREEN;
            redDistance = -0.125*CIRCUMFERENCE;
            yellowDistance = 0.25*CIRCUMFERENCE;
            blueDistance = 0.125*CIRCUMFERENCE;
            greenDistance = 0*CIRCUMFERENCE;
        } else if(currentRobotColor == COLOR_BLUE) {
            currentWheelColor = COLOR_RED;
            redDistance = 0*CIRCUMFERENCE;
            yellowDistance = -0.125*CIRCUMFERENCE;
            blueDistance = 0.25*CIRCUMFERENCE;
            greenDistance = 0.125*CIRCUMFERENCE;
        } else if(currentRobotColor == COLOR_GREEN) {
            currentWheelColor = COLOR_YELLOW;
            redDistance = 0.125*CIRCUMFERENCE;
            yellowDistance = 0*CIRCUMFERENCE;
            blueDistance = -0.125*CIRCUMFERENCE;
            greenDistance = 0.25*CIRCUMFERENCE;
        }

        //  Calculate distances based on the target color
        if(targetColor == COLOR_RED) {
            compensatedSpinDistance = (redDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        } else if(targetColor == COLOR_YELLOW) {
            compensatedSpinDistance = (yellowDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        } else if(targetColor == COLOR_BLUE) {
            compensatedSpinDistance = (blueDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        } else if(targetColor == COLOR_GREEN) {
            compensatedSpinDistance = (greenDistance*distanceFromCenterInInches)/16;
            motorSpinDistanceInEncoders = ((compensatedSpinDistance/wheelDiameterInInches)*gearboxRatio)*countsPerRevolution;
        }

        motorSpinDistanceInRotations = motorSpinDistanceInRotations/countsPerRevolution;

        if(motorSpinDistanceInRotations > 0){
            outputMotorSpeed = motorMaxSpeed;
        } else if(motorSpinDistanceInRotations < 0){
            outputMotorSpeed = -motorMaxSpeed;
        } else {
            outputMotorSpeed = 0;
        }

        return outputMotorSpeed;
    }

}