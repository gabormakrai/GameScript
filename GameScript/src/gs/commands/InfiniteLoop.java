package gs.commands;

import java.util.HashMap;

import gs.log.Logger;
import gs.main.Command;

public class InfiniteLoop implements Command {
	
	final CodeBlock codeBlock;
	
	public InfiniteLoop(CodeBlock codeBlock) {
		this.codeBlock = codeBlock;
	}

	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		while(true) {
			
			logger.log("InfiniteBlock", "Call function " + codeBlock.name + "");
			
			codeBlock.run(variables, logger);
			
		}
	}
}
