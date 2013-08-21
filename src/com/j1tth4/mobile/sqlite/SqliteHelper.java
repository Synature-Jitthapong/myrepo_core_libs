package com.j1tth4.mobile.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

public interface SqliteHelper {
	public void open();
	public void close();
	public boolean insert(String table, ContentValues cv);
	public boolean execSQL(String sqlExec);
	public Cursor rawQuery(String sqlQuery);
}
