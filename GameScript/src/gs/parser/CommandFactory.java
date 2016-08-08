package gs.parser;

import gs.commands.FindAndMoveMouse;
import gs.commands.Log;
import gs.commands.MouseClick;
import gs.commands.SetVariable;
import gs.commands.Sleep;
import gs.commands.SpecialType;
import gs.commands.Type;
import gs.commands.MouseClick.MouseClickOption;
import gs.commands.SpecialType.SpecialTypeOption;
import gs.commands.MouseMove;
import gs.exception.CompileException;
import gs.main.Command;

public class CommandFactory {
	
	static public Command parseLine(String line) throws CompileException {
		
		Command command = null;
		
		if (line.startsWith("sleep")) {
			String timeString = line.substring(line.indexOf("(") + 1);
			timeString = timeString.substring(0, timeString.indexOf(")"));
			int time = 0;
			try {
				time = Integer.parseInt(timeString);
			} catch (NumberFormatException e) {
				throw new CompileException("Sleep command has non-integer parameter", e);
			}
			command = new Sleep(time);
		} else if (line.startsWith("log")) {
			String[] keyVariable = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split("\\,");
			String key = keyVariable[0];
			boolean variable = Boolean.valueOf(keyVariable[1]);
			command = new Log(key, variable);
		} else if (line.startsWith("set")) {
			String key = line.substring(line.indexOf(" ") + 1, line.indexOf("="));
			String value = line.substring(line.indexOf("=") + 1);
			command = new SetVariable(key, value);
		} else if (line.startsWith("findandmovemouse")) {
			String[] parameters = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split("\\,");
			
			if (parameters.length != 10) {
				throw new CompileException("Not enough parameter (should be 10)", new Exception());
			}
			
			String screenFile = parameters[0];
			String targetFile = parameters[1];
			
			int moveX = -1;
			try {
				moveX = Integer.parseInt(parameters[2]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter moveX is not an integer: " + parameters[2], e);
			}
			
			int moveY = -1;
			try {
				moveY = Integer.parseInt(parameters[3]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter moveY is not an integer: " + parameters[3], e);
			}
			
			int x0 = -1;
			try {
				x0 = Integer.parseInt(parameters[4]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter x0 is not an integer: " + parameters[4], e);
			}
			
			int y0 = -1;
			try {
				y0 = Integer.parseInt(parameters[5]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter y0 is not an integer: " + parameters[5], e);
			}
			
			int x1 = -1;
			try {
				x1 = Integer.parseInt(parameters[6]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter x1 is not an integer: " + parameters[6], e);
			}
			
			int y1 = -1;
			try {
				y1 = Integer.parseInt(parameters[7]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter y1 is not an integer: " + parameters[7], e);
			}
			
			int retries = -1;
			try {
				retries = Integer.parseInt(parameters[8]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter retries is not an integer: " + parameters[8], e);
			}
			
			int retrySleep = -1;
			try {
				retrySleep = Integer.parseInt(parameters[9]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter retrySleep is not an integer: " + parameters[9], e);
			}
			
			command = new FindAndMoveMouse(screenFile, targetFile, moveX, moveY, x0, y0, x1, y1, retries, retrySleep);
		} else if (line.startsWith("mouseclick")) {
			String[] parameters = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split("\\,");
			MouseClickOption button = MouseClickOption.valueOf(parameters[0]);
			command = new MouseClick(button);
		} else if (line.startsWith("mousemove")) {
			String[] parameters = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split("\\,");
			
			int x = -1;
			try {
				x = Integer.parseInt(parameters[0]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter x is not an integer: " + parameters[0], e);
			}
			
			int y = -1;
			try {
				y = Integer.parseInt(parameters[1]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter y is not an integer: " + parameters[1], e);
			}
			
			command = new MouseMove(x, y);
		} else if (line.startsWith("specialtype")) {
			String[] parameters = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split("\\,");
			
			SpecialTypeOption key = SpecialTypeOption.valueOf(parameters[0]);
			
			int strikes = -1;
			try {
				strikes = Integer.parseInt(parameters[1]);
			} catch (NumberFormatException e) {
				throw new CompileException("Parameter strikes is not an integer: " + parameters[1], e);
			}
			
			command = new SpecialType(key, strikes);
		} else if (line.startsWith("type")) {
			String[] parameters = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split("\\,");
			String key = parameters[0];
			boolean variables = Boolean.valueOf(parameters[1]);
			command = new Type(key, variables);
		}
		
		if (command == null) {
			throw new CompileException("Unknown command '" + line + "'", new Exception());
		} else {
			return command;
		}
	}
	
}
