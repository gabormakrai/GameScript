package gs.robot;

import java.awt.AWTException;
import java.awt.Robot;

public class RobotService {
	
	public static Robot mainRobot = null;
	
	public static Robot getRobot() {
		if (mainRobot == null) {
			try {
				mainRobot = new Robot();
			} catch (AWTException e) {
				throw new RuntimeException("Problem getting the robot...", e);
			}
		}
		return mainRobot;
	}

}
