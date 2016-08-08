package gs.commands;

import java.awt.event.InputEvent;
import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;
import gs.robot.RobotService;

public class MouseClick implements Command {
	
	public static enum MouseClickOption {
		LEFT, RIGHT
	}
		
	final MouseClickOption button;
	
	public MouseClick(MouseClickOption button) {
		this.button = button;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		logger.log("MouseClick", "Click (" + button.toString() + ")...");

		int key = button == MouseClickOption.LEFT ? InputEvent.BUTTON1_MASK : InputEvent.BUTTON2_MASK;
		
		RobotService.getRobot().mousePress(key);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// do nothing
		}
		
		RobotService.getRobot().mouseRelease(key);
	}
}

