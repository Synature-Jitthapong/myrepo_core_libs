package com.j1tth4.mobile.connection.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocket implements ISocketConnection {
	private Socket	connect;
    private PrintWriter	writer;
    private BufferedReader	reader;
    
    public ClientSocket(String ip, int port){
    	try {
    		InetAddress iNetAddr = InetAddress.getByName(ip);
            connect = new Socket(iNetAddr, port);
            writer = new PrintWriter(new OutputStreamWriter(connect.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	@Override
	public void send(String msg) {
		writer.println(msg);
        writer.flush();
	}

	@Override
	public String receive() {
		 try{     
	        return reader.readLine();
		 }catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            return null;
		 }
	}

}
