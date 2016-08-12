package gs.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;

public class WaitFor implements Command {
	
	final String waitFor;
	final SimpleDateFormat sdf;
	
	public WaitFor(String waitFor) {
		this.waitFor = waitFor;
		sdf = new SimpleDateFormat("HH:mm:ss");
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		logger.log("WaitFor", "Wait for " + waitFor + "...");
		
		while (true) {
			String now = sdf.format(new Date());
			if (waitFor.compareTo(now) <= 0) {
				break;
			}
			try {
				Thread.sleep(999);
			} catch (InterruptedException e) {
				// do nothing...
			}
		}
		
		logger.log("WaitFor", "It is time :)");
		
	}

}
