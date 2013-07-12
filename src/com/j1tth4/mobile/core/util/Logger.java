package com.j1tth4.mobile.core.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.util.Log;

public abstract class Logger {
	private Context context;
	private String logDir;
	private String fileName;
	private SimpleDateFormat timeFormat;
	private SimpleDateFormat dateFormat;
	private Date date;
	
	public Logger(Context c, String logDir, String fileName){
		this.context = c;
		this.logDir = logDir;
		this.fileName = fileName;

		date = new Date();
		timeFormat = 
				new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		dateFormat = 
				new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		this.fileName += "_" + dateFormat.format(date);
	}
	
	public void appendLog(String mesg) {
		
		FileManager fileManager = new FileManager(context, logDir);
		String logFile = fileManager.getFile(fileName).getPath();
		
		try {
			// BufferedWriter for performance, true to set append to file flag
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile,
					true));
			buf.newLine();
			buf.append(timeFormat.format(date) + " " + mesg);
			buf.close();
		} catch (IOException e) {
			Log.d("LOGGER", e.getMessage());
		}
	}
}
