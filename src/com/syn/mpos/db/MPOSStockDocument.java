package com.syn.mpos.db;

import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.j1tth4.mobile.sqlite.SQLiteHelper;
import com.syn.pos.Document;

public class MPOSStockDocument extends Util implements Document {
	
	public static final int SALE_DOC = 20;
	public static final int VOID_DOC = 21;
	public static final int DAILY_DOC = 24;
	public static final int DAILY_ADD_DOC = 18;
	public static final int DAILY_REDUCE_DOC = 19;
	public static final int DIRECT_RECEIVE_DOC = 39;
	public static final int DOC_STATUS_NEW = 0;
	public static final int DOC_STATUS_SAVE = 1;
	public static final int DOC_STATUS_APPROVE = 2;
	public static final int DOC_STATUS_CANCLE = 99;
	
	private SQLiteHelper mDbHelper;
	
	public MPOSStockDocument(Context context){
		mDbHelper = new MPOSSQLiteHelper(context);
	}
	
	@Override
	public int getMaxDocument(int shopId, int docTypeId) {
		int maxDocId = 0;
		String strSql = "SELECT MAX(documetn_id) " +
				" FROM document " +
				" WHERE shop_id=" + shopId +
				" AND document_type_id=" + docTypeId;
		
		mDbHelper.open();
		Cursor cursor = mDbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			maxDocId = cursor.getInt(0);
		}
		cursor.close();
		mDbHelper.close();
		return maxDocId + 1;
	}

	@Override
	public int getMaxDocumentNo(int documentId, int shopId, int documentMonth,
			int documentYear, int documentTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean newDocument(int shopId, int documentTypeId, int staffId) {
		boolean isSuccess = false;
		int documentId = getMaxDocument(shopId, documentTypeId);
		Calendar date = getDate();
		Calendar dateTime = getDateTime();
		
		ContentValues cv = new ContentValues();
		cv.put("document_id", documentId);
		cv.put("shop_id", shopId);
		cv.put("document_type_id", documentTypeId);
		cv.put("document_status", DOC_STATUS_NEW);
		cv.put("document_date", date.getTimeInMillis());
		cv.put("update_by", staffId);
		cv.put("update_date", dateTime.getTimeInMillis());
		
		mDbHelper.open();
		isSuccess = mDbHelper.insert("document", cv);
		mDbHelper.close();
		return isSuccess;
	}

	private boolean updateDocument(int documentId, int shopId, int documentStatus, 
			int staffId, String remark){
		boolean isSuccess = false;
		Calendar dateTime = getDateTime();
		
		String strSql = "UPDATE document " +
				" SET document_status=" + documentStatus + ", " + 
				" update_by=" + staffId + ", " +
				" update_date='" + dateTime.getTimeInMillis() + "', " +
				" remark='" + remark + "' " +
				" WHERE document_id=" + documentId + 
				" AND shop_id=" + shopId;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL(strSql);
		mDbHelper.close();
		return isSuccess;
	}
	
	@Override
	public boolean saveDocument(int documentId, int shopId, int staffId,
			String remark) {
		return updateDocument(documentId, shopId, DOC_STATUS_SAVE, staffId, remark);
	}

	@Override
	public boolean approveDocument(int documentId, int shopId, int staffId,
			String remark) {
		return updateDocument(documentId, shopId, DOC_STATUS_APPROVE, staffId, remark);
	}

	@Override
	public boolean cancelDocument(int documentId, int shopId, int staffId,
			String remark) {
		return updateDocument(documentId, shopId, DOC_STATUS_CANCLE, staffId, remark);
	}

}
