package gs.commands;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;
import gs.robot.RobotService;

public class SpecialType implements Command {
	
	public static enum SpecialTypeOption {
		ENTER, TAB, DELETE, CTRLT, CTRLW
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
		} else if (key == SpecialTypeOption.CTRLW) {
			keyCode = KeyEvent.VK_W;
		} else if (key == SpecialTypeOption.CTRLT) {
			keyCode = KeyEvent.VK_T;
		}
		for (int i = 0; i < strikes; ++i) {
			
			if (key == SpecialTypeOption.CTRLT || key == SpecialTypeOption.CTRLW) {
				RobotService.getRobot().keyPress(KeyEvent.VK_CONTROL);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// do nothing
				}
			}
			
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
			
			if (key == SpecialTypeOption.CTRLT || key == SpecialTypeOption.CTRLW) {
				RobotService.getRobot().keyRelease(KeyEvent.VK_CONTROL);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// do nothing
				}
			}
			
		}
	}

}
