package gs.commands;

import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;

public class SetVariable implements Command{
	
	final String key;
	final String value;
	
	public SetVariable(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		logger.log("SetVariable", "" + key + "='" + value + "'");
		
		variables.put(key, value);
		
	}

}
