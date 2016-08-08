package gs.commands;

import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;

public class CodeBlock implements Command {

	final String name;
	final Command[] commands;
	
	public CodeBlock(String name, Command[] commands) {
		this.name = name;
		this.commands = commands;
	}
	
	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		logger.log("CodeBlock(" + name + ")", "Start execution...");
		
		for (Command command : commands) {
			command.run(variables, logger);
		}
		
		logger.log("CodeBlock(" + name + ")", "Execution finished...");
		
	}
	
	
}
