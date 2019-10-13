package log.com.dao.impl;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import log.com.dao.LogOutPutDAO;
import log.com.utilities.Constant;

public class ConsoleLogOutPutDAOImpl implements LogOutPutDAO {

	private static Logger logger = Logger.getLogger(Constant.MY_LOG);
	
	@Override
	public int writeMessage(String message) throws Exception{
		int exito =0;
		try {
			ConsoleHandler ch = new ConsoleHandler();
			
			logger.addHandler(ch);
			logger.log(Level.INFO, message);
			exito = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

}
