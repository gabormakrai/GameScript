package gs.main;

import java.util.HashMap;

import gs.log.Logger;

public interface Command {
	
	void run(HashMap<String, String> variables, Logger logger);
	
}
