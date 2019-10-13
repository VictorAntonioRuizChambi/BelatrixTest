package log.com.main;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobLogger {
	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logToDatabase;
	private static boolean logMessage;
	private static boolean logWarning;
	private static boolean logError;
	
	private boolean initialized; // este atributo no se utiliza
	private static Map dbParams;
	private static Logger logger;

	public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
			boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap) {
		logger = Logger.getLogger("MyLog"); 
		logToFile = logToFileParam;	
		logToConsole = logToConsoleParam;	
		logToDatabase = logToDatabaseParam;
		logMessage = logMessageParam;
		logWarning = logWarningParam;
		logError = logErrorParam;
		dbParams = dbParamsMap;
	}

	public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {
		messageText.trim(); //Cabe la posibilidad que el sistema se detenga por error, ya que messageText puede que
		//					  esté referencien a un valor nulo, null.
		if (messageText == null || messageText.length() == 0) {
			return;
		}
		if (!logToConsole && !logToFile && !logToDatabase) {
			throw new Exception("Invalid configuration");
		}
		if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
			throw new Exception("Error or Warning or Message must be specified");
		}

		Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", dbParams.get("userName"));//en caso no se haya guardado en el map valor para dicha llave, se almacenará null
		connectionProps.put("password", dbParams.get("password"));//en caso no se haya guardado en el map valor para dicha llave, se almacenará null

		connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
				+ ":" + dbParams.get("portNumber") + "/", connectionProps);//el resultado de dicha conexión resultará null debido a connectionProps
																			//Para usar connectionProps, se debe especificar las propiedades que se requieran usar,
																			//de otro modo se concatenará el id de memoria, por decirlo asi, de la variable connectionProps

		int t = 0;
		if (message && logMessage) {
			t = 1;
		}
										//no son excluyentes, por lo que en caso message sea true y logMessage sea true y error = true y logError = true
		if (error && logError) {		//el valor de t igual a 1 sea reemplazado por el valor de t = 2
			t = 2;
		}

		if (warning && logWarning) {	//no son excluyentes, por lo que en caso message sea true y logMessage sea true y error = true y logError = true
			t = 3;						//y warning sea true y logWarning sea true, el valor de t igual a 2 será reemplazado por el valor de t = 3
		}

		Statement stmt = connection.createStatement();

		String l = null;
		File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		
		FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
		ConsoleHandler ch = new ConsoleHandler();
		
		if (error && logError) {
			l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;//como la variable 'l' no se le ha especificado un valor
																											//previo, su valor por consola será 'null' en formato cadena
																											//y el formato cadena será algo asi "nullerror ..."
		}

		if (warning && logWarning) {
			l = l + "warning " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;//como la variable 'l' no se le ha especificado un valor
																											 //previo, su valor por consola será 'null' en formato cadena
																											 //y adicionalmente las condicionales no son exluyentes, se podría dar el caso
																											 //que se tenga como resultado "nullerror ...warning ..."
																											 // o "nullwarning ..."
		}

		if (message && logMessage) {
			l = l + "message " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;//como la variable 'l' no se le ha especificado un valor
																											 //previo, su valor por consola será 'null' en formato cadena
																											 //y adicionalmente las condicionales no son exluyentes, se podría dar el caso
																											 //que se tenga como resultado "nullerror ...nullwarning ...nullmessage..."
																											 //o "nullmessage..."
		}																									 
					
		if(logToFile) {
			logger.addHandler(fh);
			logger.log(Level.INFO, messageText);
		}
		
		if(logToConsole) {
			logger.addHandler(ch);
			logger.log(Level.INFO, messageText);
		}
		
		if(logToDatabase) {
			stmt.executeUpdate("insert into Log_Values('" + message + "', " + String.valueOf(t) + ")");
		}
	}
}
