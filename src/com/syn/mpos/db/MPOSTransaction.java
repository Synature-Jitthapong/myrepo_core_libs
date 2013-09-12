package com.syn.mpos.db;

import java.util.Calendar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.j1tth4.mobile.sqlite.SQLiteHelper;
import com.syn.mpos.model.OrderTransaction;
import com.syn.pos.Transaction;

/**
 * 
 * @author j1tth4
 *
 */
public class MPOSTransaction extends Util implements Transaction{
	
	private SQLiteHelper mDbHelper;
	
	public MPOSTransaction(Context context) {
		mDbHelper = new MPOSSQLiteHelper(context);
	}

	@Override
	public int getMaxTransaction(int computerId) {
		int transactionId = 0;
		
		String strSql = "SELECT MAX(transaction_id) FROM order_transaction " +
				" WHERE computer_id=" + computerId;
		
		mDbHelper.open();
		Cursor cursor = mDbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			transactionId = cursor.getInt(0);
			cursor.moveToNext();
		}
		cursor.close();
		mDbHelper.close();
		
		return transactionId + 1;
	}

	@Override
	public int openTransaction(int computerId, int shopId,
			int sessionId, int staffId) {

		int transactionId = getMaxTransaction(computerId);
		Calendar dateTime = getDateTime();
		Calendar date = getDate();
		
		Log.d("dateTime", dateTime.getTime().toString());
		Log.i("date", date.getTime().toString());
		
		ContentValues cv = new ContentValues();
		cv.put("transaction_id", transactionId);
		cv.put("computer_id", computerId);
		cv.put("shop_id", shopId);
		cv.put("session_id", sessionId);
		cv.put("open_staff_id", staffId);
		cv.put("open_time", dateTime.getTimeInMillis());
		cv.put("open_staff_id", staffId);
		cv.put("sale_date", date.getTimeInMillis());
		cv.put("receipt_year", dateTime.get(Calendar.YEAR));
		cv.put("receipt_month", dateTime.get(Calendar.MONTH));
		
		mDbHelper.open();
		
		if(!mDbHelper.insert("order_transaction", cv))
			transactionId = 0;
		
		mDbHelper.close();
		
		return transactionId;
	}

	@Override
	public boolean successTransaction(int transactionId, int computerId, int staffId){
		boolean isSuccess = false;
		
		Calendar calendar = getDateTime();
		int receiptId = getMaxReceiptId(computerId, calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH));
		
		String strSql = "UPDATE order_transaction " +
				" SET transaction_status_id=2, " +
				" receipt_id=" + receiptId + ", " +
				" close_time=" + calendar.getTimeInMillis() + ", " +
				" paid_time=" + calendar.getTimeInMillis() + ", " + 
				" paid_staff_id=" + staffId +
				" WHERE transaction_id=" + transactionId + 
				" AND computer_id=" + computerId;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL(strSql);
		mDbHelper.close();
		return isSuccess;
	}
	
	@Override
	public boolean deleteTransaction(int transactionId, int computerId){
		boolean isSuccess = false;
		mDbHelper.open();
		mDbHelper.execSQL("DELETE FROM order_transaction " +
				" WHERE transaction_id=" + transactionId + 
				" AND computer_id=" + computerId);
		mDbHelper.close();
		return isSuccess;
	}

	@Override
	public int getMaxReceiptId(int computerId, int year, int month) {
		int maxReceiptId = 0;
		
		String strSql = "SELECT MAX(receipt_id) FROM order_transaction " +
				" WHERE computer_id=" + computerId +
				" AND receipt_year=" + year + 
				" AND receipt_month=" + month + 
				" AND transaction_status_id = 2";
		
		mDbHelper.open();
		Cursor cursor = mDbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			maxReceiptId = cursor.getInt(0);
			cursor.moveToNext();
		}
		cursor.close();
		mDbHelper.close();
		
		return maxReceiptId + 1;
	}

	@Override
	public int getCurrTransaction(int computerId) {
		int transactionId = 0;
		
		String strSql = "SELECT transaction_id FROM order_transaction " +
				" WHERE computer_id = " + computerId + 
				" AND transaction_status_id = 1";
		
		mDbHelper.open();
		Cursor cursor = mDbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			if(cursor.getLong(0) != 0)
				transactionId = cursor.getInt(0);
			cursor.moveToNext();
		}
		cursor.close();
		mDbHelper.close();
		return transactionId;
	}
	
	@Override
	public boolean prepareTransaction(int transactionId, int computerId) {
		boolean isSuccess = false;
		
		String strSql = "UPDATE order_transaction SET " +
				" transaction_status_id=1, remark='' " +
				" WHERE transaction_id=" + transactionId +
				" AND computer_id=" + computerId;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL(strSql);
		mDbHelper.close();
		
		return isSuccess;
	}

	@Override
	public boolean holdTransaction(int transactionId, int computerId, String remark) {
		boolean isSuccess = false;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL("UPDATE order_transaction SET transaction_status_id = 9, " +
				" remark='" + remark + "' " +
				" WHERE transaction_id=" + transactionId + 
				" AND computer_id=" + computerId);
		mDbHelper.close();
		return isSuccess;
	}
	
	@Override
	public boolean updateTransactionVat(int transactionId, int computerId, 
			float totalSalePrice, float vatExclude) {
		boolean isSuccess = false;
		float vat = calculateVat(totalSalePrice, 7);
		
		String strSql = "UPDATE order_transaction SET " +
				" transaction_vat=" + vat + ", " +
				" transaction_vatable=" + totalSalePrice + ", " +
				" transaction_exclude_vat=" + vatExclude +
				" WHERE transaction_id=" + transactionId +
				" AND computer_id=" + computerId;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL(strSql);
		mDbHelper.close();
		return isSuccess;
	}
	
	@Override
	public OrderTransaction getTransaction(int transactionId, int computerId){
		OrderTransaction trans = new OrderTransaction();
		
		String strSql = "SELECT * FROM order_transaction " +
				" WHERE transaction_id=" + transactionId +
				" AND computer_id=" + computerId;
		
		mDbHelper.open();
		Cursor cursor = mDbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			trans.setTransactionVatable(cursor.getFloat(cursor.getColumnIndex("transaction_vatable")));
			trans.setTransactionVat(cursor.getFloat(cursor.getColumnIndex("transaction_vat")));
			trans.setTransactionVatExclude(cursor.getFloat(cursor.getColumnIndex("transaction_exclude_vat")));
			trans.setSaleDate(cursor.getLong(cursor.getColumnIndex("sale_date")));
			trans.setReceiptYear(cursor.getInt(cursor.getColumnIndex("receipt_year")));
			trans.setReceiptMonth(cursor.getInt(cursor.getColumnIndex("receipt_month")));
			trans.setReceiptId(cursor.getInt(cursor.getColumnIndex("receipt_id")));
		}
		cursor.close();
		mDbHelper.close();
		return trans;
	}
}
