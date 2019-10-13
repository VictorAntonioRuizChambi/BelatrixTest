package log.com.main;

import java.util.Scanner;

import log.com.service.LogMessageService;
import log.com.service.impl.LogErrorMessageServiceImpl;
import log.com.service.impl.LogMessageMessageServiceImpl;
import log.com.service.impl.LogWarningMessageServiceImpl;
import log.com.utilities.Constant;

public class MainJobLogger {

	public static void main(String[] args) throws Exception{
		
		final LogMessageService logMessageMessageService;
		final LogMessageService logErrorMessageService;
		final LogMessageService logWarningMessageService;
		String entradaTeclado1 = "";
		String entradaTeclado2 = "";
		
        System.out.println ("Sistema de escritura de mensajes[MESSAGE,ERROR,WARNING] en salidas [CONSOLE,FILE,DASEDATOS]");
        System.out.println ("===========================================================================================");
        System.out.println ("Para cada uno de los tipos de mensaje ingresar bajo el");
        System.out.println ("siguiente formato: ZZZ, donde Z puede ser los valores N o Y");
//        System.out.println ("para cada uno de los 2 grupos mencionados, bajo el siguiente formato: ZZZ, donde Z puede ser los valores N o Y");
        System.out.println ("valores para [MESSAGE,ERROR,WARNING]:");
        Scanner entradaEscaner = new Scanner (System.in);
        entradaTeclado1 = entradaEscaner.nextLine ();
        
        if(entradaTeclado1==null || entradaTeclado1.trim().equals(Constant.EMPTY_TEXT)) {
        	System.out.println ("No se han ingresado los valores solicitados.");
        }else {
        	if(entradaTeclado1.trim().length()!=3){
        		System.out.println ("No se han ingresado la cantidad de valores solicitados.");
        	}else if((!String.valueOf(entradaTeclado1.charAt(0)).toUpperCase().equals("N") && !String.valueOf(entradaTeclado1.charAt(0)).toUpperCase().equals("Y"))
        			||(!String.valueOf(entradaTeclado1.charAt(1)).toUpperCase().equals("N") && !String.valueOf(entradaTeclado1.charAt(1)).toUpperCase().equals("Y"))
        			||(!String.valueOf(entradaTeclado1.charAt(2)).toUpperCase().equals("N") && !String.valueOf(entradaTeclado1.charAt(2)).toUpperCase().equals("Y"))) {
        		System.out.println ("El valor ingresado no cumple con el formato solicitado.");
        	}else {
        		System.out.println ("valores para [CONSOLE,FILE,DASEDATOS]:");
                entradaTeclado2 = entradaEscaner.nextLine ();
                
                if(entradaTeclado2==null || entradaTeclado2.trim().equals(Constant.EMPTY_TEXT)) {
                	System.out.println ("No se han ingresado los valores solicitados.");
                }else {
                	if(entradaTeclado2.trim().length()!=3){
                		System.out.println ("No se han ingresado la cantidad de valores solicitados.");
                	}else if((!String.valueOf(entradaTeclado2.charAt(0)).toUpperCase().equals("N") && !String.valueOf(entradaTeclado2.charAt(0)).toUpperCase().equals("Y"))
                			||(!String.valueOf(entradaTeclado2.charAt(1)).toUpperCase().equals("N") && !String.valueOf(entradaTeclado2.charAt(1)).toUpperCase().equals("Y"))
                			||(!String.valueOf(entradaTeclado2.charAt(2)).toUpperCase().equals("N") && !String.valueOf(entradaTeclado2.charAt(2)).toUpperCase().equals("Y"))) {
                		System.out.println ("El valor ingresado no cumple con el formato solicitado.");
                	}else{
                		if(String.valueOf(entradaTeclado1.charAt(0)).toUpperCase().equals("Y")){
                			logMessageMessageService = new LogMessageMessageServiceImpl();
                			if(String.valueOf(entradaTeclado2.charAt(0)).toUpperCase().equals("Y"))logMessageMessageService.logMessage("Message Test Message to Console", Constant.LOG_TO_CONSOLE);
                			if(String.valueOf(entradaTeclado2.charAt(1)).toUpperCase().equals("Y"))logMessageMessageService.logMessage("Message Test Message to File", Constant.LOG_TO_FILE);
                			if(String.valueOf(entradaTeclado2.charAt(2)).toUpperCase().equals("Y"))logMessageMessageService.logMessage("Message Test Message to DB", Constant.LOG_TO_DATABASE);
                		}
                		
                		if(String.valueOf(entradaTeclado1.charAt(1)).toUpperCase().equals("Y")){
                			logErrorMessageService = new LogErrorMessageServiceImpl();
                			if(String.valueOf(entradaTeclado2.charAt(0)).toUpperCase().equals("Y"))logErrorMessageService.logMessage("Error Test Message to Console", Constant.LOG_TO_CONSOLE);
                			if(String.valueOf(entradaTeclado2.charAt(1)).toUpperCase().equals("Y"))logErrorMessageService.logMessage("Error Test Message to File", Constant.LOG_TO_FILE);
                			if(String.valueOf(entradaTeclado2.charAt(2)).toUpperCase().equals("Y"))logErrorMessageService.logMessage("Error Test Message to DB", Constant.LOG_TO_DATABASE);
                		}
                		
                		if(String.valueOf(entradaTeclado1.charAt(2)).toUpperCase().equals("Y")){
                			logWarningMessageService = new LogWarningMessageServiceImpl();
                			if(String.valueOf(entradaTeclado2.charAt(0)).toUpperCase().equals("Y"))logWarningMessageService.logMessage("Warning Test Message to Console", Constant.LOG_TO_CONSOLE);
                			if(String.valueOf(entradaTeclado2.charAt(1)).toUpperCase().equals("Y"))logWarningMessageService.logMessage("Warning Test Message to File", Constant.LOG_TO_FILE);
                			if(String.valueOf(entradaTeclado2.charAt(2)).toUpperCase().equals("Y"))logWarningMessageService.logMessage("Warning Test Message to DB", Constant.LOG_TO_DATABASE);
                		}
                	}
                }
        	}
        }
        
       

	}

}
