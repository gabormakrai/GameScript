package gs.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import gs.commands.Sleep;
import gs.exception.CompileException;
import gs.main.Command;

public class CommandFactoryTest {

	@Test
	public void testSleep1() {
		
		Command command = CommandFactory.parseLine("sleep(1021)");
		
		assertTrue(command instanceof Sleep);
		
		assertEquals(1021, ((Sleep)command).getTime());
		
	}
	
	@Test
	public void testSleep2() {
		
		boolean exception = false;

		try {
			Command command = CommandFactory.parseLine("sleep(asd)");
		} catch (CompileException e) {
			exception = true;
		}
		
		assertTrue(exception);
		
	}
	
	@Test
	public void testSleep3() {
		
		Command command = CommandFactory.parseLine("Sleep(1021)");
		
		assertTrue(command == null);
		
	}
	
	
	
}
