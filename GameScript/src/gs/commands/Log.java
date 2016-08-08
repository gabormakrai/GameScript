package gs.commands;

import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;

public class Log implements Command {
	
	final String key;
	final boolean variable;
	
	public Log(String message) {
		this(message, false);
	}
	
	public Log(String key, boolean variable) {
		this.key = key;
		this.variable = variable;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		if (variable) {
			logger.log("Log", "Variable " + key + " = '" + variables.get(key) + "'");
		} else {
			logger.log("Log", key);
		}
	}

}
