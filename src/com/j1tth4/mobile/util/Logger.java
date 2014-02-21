package com.j1tth4.mobile.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.util.Log;

public abstract class Logger {
	public static final String FILE_EXTENSION = ".txt";
	private Context mContext;
	private String mLogDir;
	private String mFileName;
	private SimpleDateFormat mTimeFormat;
	private SimpleDateFormat mDateFormat;
	private Date date;
	
	public Logger(Context c, String logDir, String fileName){
		mContext = c;
		mLogDir = logDir;
		mFileName = fileName;

		date = new Date();
		mTimeFormat = 
				new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		mDateFormat = 
				new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		mFileName += "_" + mDateFormat.format(date) + FILE_EXTENSION;
	}
	
	public void appendLog(String mesg) {
		
		FileManager fileManager = new FileManager(mContext, mLogDir);
		String logFile = fileManager.getFile(mFileName).getPath();
		
		try {
			// BufferedWriter for performance, true to set append to file flag
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile,
					true));
			buf.newLine();
			buf.append(mTimeFormat.format(date) + " " + mesg);
			buf.close();
		} catch (IOException e) {
			Log.d("LOGGER", e.getMessage());
		}
	}
}
