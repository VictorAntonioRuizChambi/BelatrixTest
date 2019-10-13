package log.com.dao.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import log.com.dao.LogOutPutDAO;
import log.com.utilities.Constant;

public class FileLogOutPutDAOImpl implements LogOutPutDAO{
	
	private static Logger logger = Logger.getLogger(Constant.MY_LOG);
	private static Map<String,Object> dbParams= new HashMap<String,Object>();
	
	@Override
	public int writeMessage(String message) throws Exception {
		
		int exito = 0;
		try{
			dbParams.put(Constant.LOG_FILE_FOLDER, Constant.EMPTY_TEXT);
			String logPath = dbParams.get(Constant.LOG_FILE_FOLDER) + Constant.LOG_FILE;
			File logFile = new File(logPath);
			
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			
			FileHandler fh = new FileHandler(logPath);
			
			logger.addHandler(fh);
			logger.log(Level.INFO, message);
			exito = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

}
