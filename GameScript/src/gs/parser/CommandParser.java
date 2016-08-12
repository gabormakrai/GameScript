package gs.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

import gs.commands.CodeBlock;
import gs.commands.InfiniteLoop;
import gs.exception.CompileException;
import gs.main.Command;

public class CommandParser {
	
	public Command parseFile(String fileName) {
		
		HashMap<String, CodeBlock> codeblocks = new HashMap<>();
		
		LinkedList<Command> mainCommands = new LinkedList<>();
		
		LinkedList<Command> codeBlockCommands = null;
		String codeBlockName = null;
		
		int lineCounter = 0;
		
		try {
			
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String line = "";
				
			while ((line = br.readLine()) != null) {
				
				++lineCounter;
				
				line = line.trim();
				
				if (line.startsWith("#")) {
					continue;
				} else if (line.length() == 0) {
					continue;
				}
				// function definition
				else if (line.startsWith("def")) {
					if (codeBlockName != null) {
						throw new CompileException("Line " + lineCounter + ": Cannot define function in a function...", new Exception());
					}
					
					codeBlockName = line.substring(line.indexOf(" ") + 1, line.indexOf(":"));
					codeBlockCommands = new LinkedList<>();
					
				}
				// function def end
				else if (line.startsWith("enddef")) {
					if (codeBlockName == null) {
						throw new CompileException("Line " + lineCounter + ": There is no function to close...", new Exception());
					}
					
					CodeBlock codeBlock = new CodeBlock(codeBlockName, codeBlockCommands.toArray(new Command[0]));
					codeblocks.put(codeBlockName, codeBlock);
					
					codeBlockName = null;
					codeBlockCommands = null;
				}
				else {
					
					Command command = null;
					
					if (line.startsWith("call")) {
						String blockNameToCall = line.substring(line.indexOf(" ") + 1);
						CodeBlock codeBlock = codeblocks.get(blockNameToCall);
						if (codeBlock == null) {
							throw new CompileException("Line " + lineCounter + ": No function called '" + blockNameToCall+ "' defined...", new Exception());
						}
						command = codeBlock;
					} else if (line.startsWith("infiniteloop")) {
						String blockNameToCall = line.substring(line.indexOf(" ") + 1);
						CodeBlock codeBlock = codeblocks.get(blockNameToCall);
						if (codeBlock == null) {
							throw new CompileException("Line " + lineCounter + ": No function called '" + blockNameToCall+ "' defined...", new Exception());
						}
						command = new InfiniteLoop(codeBlock);
					} else {
						try {
							command = CommandFactory.parseLine(line);
						} catch (Throwable e) {
							throw new CompileException("Line " + lineCounter + ": ", e);
						}
					}
						
					if (codeBlockCommands != null) {
						codeBlockCommands.add(command);
					} else {
						mainCommands.add(command);
					}
				}
			}
			
			br.close();
		} catch (IOException e) {
			throw new CompileException("IO Problem during reading the file", e);
		}
		
		return new CodeBlock("main", mainCommands.toArray(new Command[0]));
	}
	
}
