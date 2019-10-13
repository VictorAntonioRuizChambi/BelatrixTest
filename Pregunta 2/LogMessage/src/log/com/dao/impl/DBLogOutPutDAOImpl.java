package log.com.dao.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import log.com.dao.LogOutPutDAO;

public class DBLogOutPutDAOImpl implements LogOutPutDAO {

	private static Map<String,Object> dbParams= new HashMap<String,Object>();
	
	@Override
	public int writeMessage(String message) throws Exception{
		Connection connection = null;
		Properties connectionProps = new Properties();
		int exito =0;
		
		dbParams.put("userName", "testUser");
		dbParams.put("password", "testPassword");
		dbParams.put("dbms", "mysql");
		dbParams.put("serverName", "localhost");
		dbParams.put("portNumber", "3601");
		
		connectionProps.put("user", dbParams.get("userName"));//en caso no se haya guardado en el map valor para dicha llave, se almacenará null
		connectionProps.put("password", dbParams.get("password"));//en caso no se haya guardado en el map valor para dicha llave, se almacenará null

		try {
			connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" 
															 + dbParams.get("serverName")
															 + ":" + dbParams.get("portNumber") 
															 + "/", connectionProps);
			
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate("insert into Log_Values("+message +")");
			exito=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
																			
		return exito;																	


	}

}
