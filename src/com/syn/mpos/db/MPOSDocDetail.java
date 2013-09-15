package com.syn.mpos.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.j1tth4.mobile.sqlite.SQLiteHelper;
import com.syn.pos.DocumentDetail;
import com.syn.pos.inventory.Document.DocDetail;

public class MPOSDocDetail extends Util implements DocumentDetail {
	
	private SQLiteHelper mDbHelper;
	
	public MPOSDocDetail(Context context){
		mDbHelper = new MPOSSQLiteHelper(context);
	}
	
	@Override
	public int getMaxDocumentDetail(int documentId, int shopId) {
		int docDetailId = 0;
		String strSql = "SELECT MAX(docdetail_id " +
				" FROM docdetail " +
				" WHERE document_id=" + documentId + 
				" AND shop_id=" + shopId;
		
		mDbHelper.open();
		Cursor cursor = mDbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			docDetailId = cursor.getInt(0);
		}
		cursor.close();
		mDbHelper.close();
		return docDetailId + 1;
	}

	@Override
	public boolean addDocumentDetail(int documentId, int shopId, float materialId, 
			float materialQty, float materialPrice, String unitName) {
		boolean isSuccess = false;
		int docDetailId = getMaxDocumentDetail(documentId, shopId);
		
		ContentValues cv = new ContentValues();
		cv.put("docdetail_id", docDetailId);
		cv.put("document_id", documentId);
		cv.put("shop_id", shopId);
		cv.put("material_id", materialId);
		cv.put("material_qty", materialQty);
		cv.put("material_price_per_unit", materialPrice);
		cv.put("material_net_price", materialPrice);
		cv.put("material_tax_type", 1);
		cv.put("material_tax_price", 0);
		
		mDbHelper.open();
		isSuccess = mDbHelper.insert("docdetail", cv);
		mDbHelper.close();
		return isSuccess;
	}

	@Override
	public boolean addDocumentDetail(int documentId, int shopId,
			float materialId, float materialQty, float materialPrice,
			int taxType, String unitName) {
		boolean isSuccess = false;
		int docDetailId = getMaxDocumentDetail(documentId, shopId);
		float totalPrice = materialPrice * materialQty;
		float tax = taxType == 2 ? calculateVat(totalPrice, 7) : 0;
		float netPrice = totalPrice + tax;
		
		ContentValues cv = new ContentValues();
		cv.put("docdetail_id", docDetailId);
		cv.put("document_id", documentId);
		cv.put("shop_id", shopId);
		cv.put("material_id", materialId);
		cv.put("material_qty", materialQty);
		cv.put("material_price_per_unit", materialPrice);
		cv.put("material_net_price", netPrice);
		cv.put("material_tax_type", taxType);
		cv.put("material_tax_price", tax);
		
		mDbHelper.open();
		isSuccess = mDbHelper.insert("docdetail", cv);
		mDbHelper.close();
		return isSuccess;
	}

	@Override
	public boolean updateDocumentDetail(int docDetailId, int documentId, int shopId,
			int materialId, float materialQty, float materialPrice,
			String unitName) {
		boolean isSuccess = false;
		float totalPrice = materialPrice * materialQty;
		
		String strSql = "UPDATE docdetail " +
				" SET material_qty=" + materialQty + ", " +
				" material_price_per_unit=" + materialPrice + ", " +
				" material_net_price=" + totalPrice + 
				" WHERE docdetail_id=" + docDetailId + 
				" AND document_id=" + documentId +
				" AND shop_id=" + shopId;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL(strSql);
		mDbHelper.close();
		return isSuccess;
	}

	@Override
	public boolean updateDocumentDetail(int docDetailId, int documentId, int shopId,
			int materialId, float materialQty, float materialPrice,
			int taxType, String unitName) {
		boolean isSuccess = false;
		float totalPrice = materialPrice * materialQty;
		float tax = taxType == 2 ? calculateVat(totalPrice, 7) : 0;
		float netPrice = totalPrice + tax;
		
		String strSql = "UPDATE docdetail " +
				" SET material_qty=" + materialQty + ", " +
				" material_price_per_unit=" + materialPrice + ", " +
				" material_net_price=" + netPrice + ", " +
				" material_tax_type=" + taxType + 
				" WHERE docdetail_id=" + docDetailId + 
				" AND document_id=" + documentId +
				" AND shop_id=" + shopId;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL(strSql);
		mDbHelper.close();
		return isSuccess;
	}

	@Override
	public boolean deleteDocumentDetail(int docDetailId, int shopId, int documentId) {
		boolean isSuccess = false;
		String strSql = "DELETE FROM docdetail " +
				" WHERE docdetail_id=" + docDetailId + 
				" AND document_id=" + documentId + 
				" AND shop_id=" + shopId;
		
		mDbHelper.open();
		isSuccess = mDbHelper.execSQL(strSql);
		mDbHelper.close();
		return isSuccess;
	}

	@Override
	public DocDetail getDocDetail(int docDetailId, int documentId, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocDetail> listAllDocDetail(int documentId, int shopId) {
		List<DocDetail> docDetailLst = 
				new ArrayList<DocDetail>();
		String strSql = "SELECT * FROM docdetail " +
				" WHERE document_id=" + documentId + 
				" AND shop_id=" + shopId;
		
		mDbHelper.open();
		Cursor cursor = mDbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			do{
				DocDetail docDetail = new DocDetail();
				docDetail.setDocDetailId(cursor.getInt(cursor.getColumnIndex("docdetail_id")));
				docDetail.setMaterialId(cursor.getInt(cursor.getColumnIndex("material_id")));
				
			}while(cursor.moveToNext());
		}
		cursor.close();
		mDbHelper.close();
		return docDetailLst;
	}

}
