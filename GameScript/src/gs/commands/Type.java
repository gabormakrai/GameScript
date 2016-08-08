package gs.commands;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;
import gs.robot.RobotService;

public class Type implements Command {
	
	final String key;
	final boolean variable;
	
	public Type(String key, boolean variable) {
		this.key = key;
		this.variable = variable;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		if (variable) {
		
			String message = variables.get(key);
			
			logger.log("Type", "Type variable " + key + ": '" + message + "'");
			
			for (int i = 0; i < message.length(); ++i) {
				keyPress(message.charAt(i));
			}
						
		} else {
			
			logger.log("Type", "Type '" + key + "'");
			
			for (int i = 0; i < key.length(); ++i) {
				keyPress(key.charAt(i));
			}
			
		}
	}
	
	private void keyPress(char c) {
		
		int key = -1;
		boolean pushShift = false;
		
		if (c == 'q') {
			key = KeyEvent.VK_Q;
		} else if (c == 'w') {
			key = KeyEvent.VK_W;
		} else if (c == 'e') {
			key = KeyEvent.VK_E;
		} else if (c == 'r') {
			key = KeyEvent.VK_R;
		} else if (c == 't') {
			key = KeyEvent.VK_T;
		} else if (c == 'y') {
			key = KeyEvent.VK_Y;
		} else if (c == 'u') {
			key = KeyEvent.VK_U;
		} else if (c == 'i') {
			key = KeyEvent.VK_I;
		} else if (c == 'o') {
			key = KeyEvent.VK_O;
		} else if (c == 'p') {
			key = KeyEvent.VK_P;
		} else if (c == 'a') {
			key = KeyEvent.VK_A;
		} else if (c == 's') {
			key = KeyEvent.VK_S;
		} else if (c == 'd') {
			key = KeyEvent.VK_D;
		} else if (c == 'f') {
			key = KeyEvent.VK_F;
		} else if (c == 'g') {
			key = KeyEvent.VK_G;
		} else if (c == 'h') {
			key = KeyEvent.VK_H;
		} else if (c == 'j') {
			key = KeyEvent.VK_J;
		} else if (c == 'k') {
			key = KeyEvent.VK_K;
		} else if (c == 'l') {
			key = KeyEvent.VK_L;
		} else if (c == 'z') {
			key = KeyEvent.VK_Z;
		} else if (c == 'x') {
			key = KeyEvent.VK_X;
		} else if (c == 'c') {
			key = KeyEvent.VK_C;
		} else if (c == 'v') {
			key = KeyEvent.VK_V;
		} else if (c == 'b') {
			key = KeyEvent.VK_B;
		} else if (c == 'n') {
			key = KeyEvent.VK_N;
		} else if (c == 'm') {
			key = KeyEvent.VK_M;
		} else if (c == '0') {
			key = KeyEvent.VK_0;
		} else if (c == '1') {
			key = KeyEvent.VK_1;
		} else if (c == '2') {
			key = KeyEvent.VK_2;
		} else if (c == '3') {
			key = KeyEvent.VK_3;
		} else if (c == '4') {
			key = KeyEvent.VK_4;
		} else if (c == '5') {
			key = KeyEvent.VK_5;
		} else if (c == '6') {
			key = KeyEvent.VK_6;
		} else if (c == '7') {
			key = KeyEvent.VK_7;
		} else if (c == '8') {
			key = KeyEvent.VK_8;
		} else if (c == '9') {
			key = KeyEvent.VK_9;
		} else if (c == '@') {
			pushShift = true;
			key = KeyEvent.VK_AT;
		} else if (c == '.') {
			key = KeyEvent.VK_PERIOD;
		} else if (c == ':') {
			pushShift = true;
			key = KeyEvent.VK_COLON;
		} else if (c == '/') {
			key = KeyEvent.VK_SLASH;
		}
		
		if (pushShift) {
			RobotService.getRobot().keyPress(KeyEvent.VK_SHIFT);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
		
		RobotService.getRobot().keyPress(key);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// do nothing
		}
		RobotService.getRobot().keyRelease(key);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// do nothing
		}
		
		if (pushShift) {
			RobotService.getRobot().keyRelease(KeyEvent.VK_SHIFT);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}
	
}
