package com.syn.mpos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.j1tth4.mobile.sqlite.SQLiteHelper;
import com.syn.mpos.model.Setting;

public class Configuration {
	private SQLiteHelper dbHelper;
	
	public Configuration(Context context){
		dbHelper = new MPOSSQLiteHelper(context);
	}
	
	public Setting.Sync getSync(){
		Setting.Sync sync = new Setting.Sync();
		String strSql = "SELECT * FROM sync";
		
		dbHelper.open();
		
		Cursor cursor = dbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			sync.setSyncTime(cursor.getLong(cursor.getColumnIndex("sync_time")));
			sync.setSyncStatus(cursor.getInt(cursor.getColumnIndex("sync_status")));
			cursor.moveToNext();
		}
		cursor.close();
		
		dbHelper.close();
		
		return sync;
	}
	
	public boolean addSyncSetting(Setting.Sync sync){
		boolean isSuccess = false;
		ContentValues cv = new ContentValues();
		cv.put("sync_time", sync.getSyncTime());
		cv.put("sync_status", sync.getSyncStatus());
		
		dbHelper.open();
		
		dbHelper.execSQL("DELETE FROM sync");
		isSuccess = dbHelper.insert("sync", cv);
				
		dbHelper.close();
		
		return isSuccess;
	}
	
	public Setting.ConnectionSetting getConnectionSetting(){
		Setting.ConnectionSetting connSetting = new Setting.ConnectionSetting();
		String strSql = "SELECT * FROM conn_setting";
		
		dbHelper.open();
		
		Cursor cursor = dbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			connSetting.setIpAddress(cursor.getString(cursor.getColumnIndex("ip_address")));
			connSetting.setVirtualDir(cursor.getString(cursor.getColumnIndex("virtual_dir")));
			connSetting.setServiceName(cursor.getString(cursor.getColumnIndex("service_name")));
			connSetting.setFullUrl(cursor.getString(cursor.getColumnIndex("full_url")));
			cursor.moveToNext();
		}
		cursor.close();
		
		dbHelper.close();
		
		return connSetting;
	}
	
	public boolean addConnectionSetting(Setting.ConnectionSetting setting){
		boolean isSuccess = false;
		ContentValues cv = new ContentValues();
		cv.put("ip_address", setting.getIpAddress());
		cv.put("virtual_dir", setting.getVirtualDir());
		cv.put("service_name", setting.getServiceName());
		cv.put("full_url", "http://" + setting.getIpAddress() + "/" 
				+ setting.getVirtualDir() + "/" + setting.getServiceName());
		
		dbHelper.open();
		
		dbHelper.execSQL("DELETE FROM conn_setting");
		isSuccess = dbHelper.insert("conn_setting", cv);
		
		dbHelper.close();
		
		return isSuccess;
	}
}
