package gs.commands;

import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;

public class Sleep implements Command {
	
	final int time;
	
	public Sleep(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		logger.log("Sleep", "Sleep for " + time + "ms");
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// do nothing
		}
	}

}
