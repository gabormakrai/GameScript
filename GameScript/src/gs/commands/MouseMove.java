package gs.commands;

import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;
import gs.robot.RobotService;

public class MouseMove implements Command {
	
	final int x,y;
	
	public MouseMove(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		logger.log("MouseMove", "Moving mouse cursor to (" + x + "," + y + ")");
		
		RobotService.getRobot().mouseMove(x, y);
				
	}

}
