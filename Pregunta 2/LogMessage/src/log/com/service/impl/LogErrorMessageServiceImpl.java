package log.com.service.impl;

import java.text.DateFormat;
import java.util.Date;

import log.com.dao.LogOutPutDAO;
import log.com.dao.impl.ConsoleLogOutPutDAOImpl;
import log.com.dao.impl.DBLogOutPutDAOImpl;
import log.com.dao.impl.FileLogOutPutDAOImpl;
import log.com.service.LogMessageService;
import log.com.utilities.Constant;
import log.com.utilities.MessageException;

public class LogErrorMessageServiceImpl implements LogMessageService {
	
	private LogOutPutDAO logOutPutDAO;

	@Override
	public void logMessage(String messageText, String outPutType) throws Exception {
		
		if (messageText == null || messageText.trim().equals(Constant.EMPTY_TEXT)) {
			throw new Exception(MessageException.TEXT_MESSAGE_MUST_BE_SPECIFIED);
		}else if(outPutType == null || outPutType.trim().equals(Constant.EMPTY_TEXT)) {
			throw new Exception(MessageException.OUTPUT_TYPE_MUST_BE_SPECIFIED);
		}

		messageText = Constant.ERROR_TYPE + Constant.SIMPLE_SPACE_TEXT +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		
		if(outPutType.equals(Constant.LOG_TO_CONSOLE)){
			logOutPutDAO = new ConsoleLogOutPutDAOImpl();
		}else if(outPutType.equals(Constant.LOG_TO_FILE)) {
			logOutPutDAO = new FileLogOutPutDAOImpl();
		}else if(outPutType.equals(Constant.LOG_TO_DATABASE)) {
			logOutPutDAO = new DBLogOutPutDAOImpl();
			messageText = messageText+","+Constant.ERROR_COD_TYPE;
		}
		
		logOutPutDAO.writeMessage(messageText);
		
	}

}
