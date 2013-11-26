package com.j1tth4.mobile.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

public interface SQLiteHelper {
	public static final String COMM_SEP = ",";
	
	void open();
	void close();
	boolean insert(String table, ContentValues cv);
	Cursor rawQuery(String strQuery);
	boolean execSQL(String sqlExec);
}
