package gs.main;

import java.util.Arrays;
import java.util.HashMap;

import gs.log.Logger;
import gs.parser.CommandParser;
import gs.robot.RobotService;

public class GameScriptMain {
	
	
	public static void main(String[] args) {
		
		System.out.println("args: " + Arrays.toString(args));

		// get the Robot
		RobotService.getRobot();
		
		// compile script file
		System.out.println("Compiling...");
		Command command = new CommandParser().parseFile(args[0]);
		System.out.println("Done...");
		
		// run script file
		HashMap<String, String> variables = new HashMap<>();
		Logger logger = new Logger(true);
		
		command.run(variables, logger);
		
	}
	
}
