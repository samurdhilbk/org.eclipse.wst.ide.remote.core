package org.eclipse.wst.ide.remote.core.internal.provisional;

import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.eclipse.swt.widgets.Event;

//import java.io.*;
//import java.net.Socket;
//import org.eclipse.wst.ide.remote.core.internal.Monitor;
public class Response {

	static boolean open=false;
	public static Socket socket;
	
	public static String getResponse(String command){
		return "Running "+command+" ....";
	}
	
	public static void respond(String command) throws IOException {
		IHandlerService handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
		PrintWriter socketWriter=new PrintWriter(socket.getOutputStream(),true);
		Event event=new Event();
		String[] commandArr=command.split("/");
		String mainCommand=commandArr[1];
		String subCommand="";
		if(commandArr.length>2) subCommand=commandArr[2];
		//for(int i=0;i<commandArr.length;i++) System.out.print(commandArr[i]+" ");
		//System.out.println(subCommand);
		if(mainCommand.equals("new")){ 
			//System.out.println("new");
			String commandId = IWorkbenchCommandConstants.FILE_NEW;
			socketWriter.println("The New wizard opened successfully.");
			socketWriter.close();
			try {
				handlerService.executeCommand(commandId, event);
			} catch (Exception ex) {
			    //throw new RuntimeException("add.command not found");
			    // Give message
			}
		}
		else if(mainCommand.equals("help")){
			//System.out.println("help");
			String commandId = IWorkbenchCommandConstants.HELP_ABOUT;
			socketWriter.println("The Help window opened successfully.");
			socketWriter.close();
			try {
				handlerService.executeCommand(commandId, event);
			} catch (Exception ex) {
			    //throw new RuntimeException("add.command not found");
			    // Give message
			}
		}
		socketWriter.close();
	
		return;
		
	}
}
