package com.syn.mpos.db;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;

public class Report {
	protected MPOSSQLiteHelper dbHelper;
	protected long dateFrom, dateTo;
	
	public Report(Context context, long dFrom, long dTo){
		dbHelper = new MPOSSQLiteHelper(context);
		dateFrom = dFrom;
		dateTo = dTo;
	}
	
	public Report(Context context){
		dbHelper = new MPOSSQLiteHelper(context);
	}
	
	public ArrayList<HashMap<String, String>> getGroupOfMenu(){
		ArrayList<HashMap<String, String>> menuGroups = 
				new ArrayList<HashMap<String, String>>();
		
		String strSql = "SELECT a.menu_group_id, b.menu_dept_id, " +
				" a.menu_group_name_0, b.menu_dept_name_0 " +
				" FROM menu_group a " +
				" LEFT JOIN menu_dept b " +
				" ON a.menu_group_id=b.menu_group_id";
		
		dbHelper.open();
		
		Cursor cursor = dbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			do{
				HashMap<String, String> menuGroup = 
						new HashMap<String, String>();
				menuGroup.put("menuGroupId", cursor.getString(cursor.getColumnIndex("menu_group_id")));
				menuGroup.put("menuDeptId", cursor.getString(cursor.getColumnIndex("menu_dept_id")));
				menuGroup.put("menuGroupName", cursor.getString(cursor.getColumnIndex("menu_group_name_0")));
				menuGroup.put("menuDeptName", cursor.getString(cursor.getColumnIndex("menu_dept_name_0")));
				
				menuGroups.add(menuGroup);
			}while(cursor.moveToNext());
		}
		cursor.close();
		
		dbHelper.close();
		
		return menuGroups;
	}
	
	public ArrayList<HashMap<String, String>> getSaleReportByProduct(int menuDeptId){
		ArrayList<HashMap<String, String>> reports = 
				new ArrayList<HashMap<String, String>>();
		
		String strSql = " SELECT d.product_code, b.product_name, b.sale_price, b.total_sale_price, " +
				" sum(b.product_amount) AS total_amount, b.total_product_price, b.each_product_discount " +
				" FROM order_transaction a " +
				" LEFT JOIN order_detail b " +
				" ON a.transaction_id=b.transaction_id " +
				" AND a.computer_id=b.computer_id " +
				" LEFT JOIN menu_item c " +
				" ON b.product_id=c.product_id " +
				" LEFT JOIN products d " +
				" ON c.product_id=d.product_id " +
				" WHERE c.menu_dept_id=" + menuDeptId +
				" AND a.sale_date >= " + dateFrom + 
				" AND a.sale_date <= " + dateTo +
				" GROUP BY d.product_id " +
				" ORDER BY d.product_id ";
		
		dbHelper.open();
		
		Cursor cursor = dbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			do{
				HashMap<String, String> report =
						new HashMap<String, String>();
				report.put("productCode", cursor.getString(cursor.getColumnIndex("product_code")));
				report.put("productName", cursor.getString(cursor.getColumnIndex("product_name")));
				report.put("salePrice", cursor.getString(cursor.getColumnIndex("sale_price")));
				report.put("totalSalePrice", cursor.getString(cursor.getColumnIndex("total_sale_price")));
				report.put("totalAmount", cursor.getString(cursor.getColumnIndex("total_amount")));
				report.put("totalProductPrice", cursor.getString(cursor.getColumnIndex("total_product_price")));
				report.put("totalDiscount", cursor.getString(cursor.getColumnIndex("each_product_discount")));
				
				reports.add(report);
			}while(cursor.moveToNext());
		}
		cursor.close();
		
		dbHelper.close();
		
		return reports;
	}
}
