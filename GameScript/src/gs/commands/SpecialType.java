package gs.commands;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;
import gs.robot.RobotService;

public class SpecialType implements Command {
	
	public static enum SpecialTypeOption {
		ENTER, TAB, DELETE
	}
	
	final SpecialTypeOption key;
	final int strikes;
	
	public SpecialType(SpecialTypeOption key, int strikes) {
		this.key = key;
		this.strikes = strikes;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		int keyCode = -1;
		
		if (key == SpecialTypeOption.TAB) {
			keyCode = KeyEvent.VK_TAB;
		} else if (key == SpecialTypeOption.ENTER) {
			keyCode = KeyEvent.VK_ENTER;
		} else if (key == SpecialTypeOption.DELETE) {
			keyCode = KeyEvent.VK_DELETE;
		}
		for (int i = 0; i < strikes; ++i) {
			RobotService.getRobot().keyPress(keyCode);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// do nothing
			}
			RobotService.getRobot().keyRelease(keyCode);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

}
