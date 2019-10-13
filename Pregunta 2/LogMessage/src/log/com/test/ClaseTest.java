package log.com.test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import log.com.dao.impl.ConsoleLogOutPutDAOImpl;
import log.com.dao.impl.DBLogOutPutDAOImpl;
import log.com.dao.impl.FileLogOutPutDAOImpl;

public class ClaseTest {
	
	@Test
	public void testConsoleLogOutPutDAOImpl(){
		ConsoleLogOutPutDAOImpl consoleLogOutPutDAOImpl= new ConsoleLogOutPutDAOImpl();
		try {
			
			assertFalse(consoleLogOutPutDAOImpl.writeMessage("Test Message")==0);
			assertFalse(consoleLogOutPutDAOImpl.writeMessage("Warning Message")==0);
			assertFalse(consoleLogOutPutDAOImpl.writeMessage("5")==0);
			
			assertTrue(consoleLogOutPutDAOImpl.writeMessage("Test Message")==1);
			assertTrue(consoleLogOutPutDAOImpl.writeMessage("Warning Message")==1);
			assertTrue(consoleLogOutPutDAOImpl.writeMessage(null)==1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFileLogOutPutDAOImpl(){
		FileLogOutPutDAOImpl fileLogOutPutDAOImpl= new FileLogOutPutDAOImpl();
		try {
			assertTrue(fileLogOutPutDAOImpl.writeMessage("Message Message")==1);
			assertTrue(fileLogOutPutDAOImpl.writeMessage("Warning Message")==1);
			assertTrue(fileLogOutPutDAOImpl.writeMessage("Error Message")==1);
			assertTrue(fileLogOutPutDAOImpl.writeMessage(null)==1);
			
			assertFalse(fileLogOutPutDAOImpl.writeMessage("Message Message")!=1);
			assertFalse(fileLogOutPutDAOImpl.writeMessage("Warning Message")!=1);
			assertFalse(fileLogOutPutDAOImpl.writeMessage("Error Message")!=1);
			assertFalse(fileLogOutPutDAOImpl.writeMessage(null)!=1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDBLogOutPutDAOImpl(){
		DBLogOutPutDAOImpl dBLogOutPutDAOImpl= new DBLogOutPutDAOImpl();
		try {
			
			assertFalse(dBLogOutPutDAOImpl.writeMessage("Error Message")==1);
			assertFalse(dBLogOutPutDAOImpl.writeMessage("Warning Message")==1);
			assertFalse(dBLogOutPutDAOImpl.writeMessage(null)==1);
			
//			assertTrue(dBLogOutPutDAOImpl.writeMessage("Test Message")==1);
//			assertTrue(dBLogOutPutDAOImpl.writeMessage("Warning Message")==1);
//			assertTrue(dBLogOutPutDAOImpl.writeMessage(null)==1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
