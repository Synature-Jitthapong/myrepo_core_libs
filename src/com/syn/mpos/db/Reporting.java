package com.syn.mpos.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.syn.mpos.model.Report;

import android.content.Context;
import android.database.Cursor;

public class Reporting {
	protected MPOSSQLiteHelper dbHelper;
	protected long dateFrom, dateTo;
	
	public Reporting(Context context, long dFrom, long dTo){
		dbHelper = new MPOSSQLiteHelper(context);
		dateFrom = dFrom;
		dateTo = dTo;
	}
	
	public Reporting(Context context){
		dbHelper = new MPOSSQLiteHelper(context);
	}
	
	public Report getSaleReportByBill(){
		Report report = new Report();
		
		String strSql = " SELECT a.sale_date, " +
				" SUM(a.transaction_id) AS totalBill, " +
				" SUM(b.total_product_price) AS totalProductPrice, " +
				" SUM(b.vat) AS totalVat, " +
				" SUM(b.service_charge) AS totalServiceCharge, " +
				" SUM(b.each_product_discount + b.member_discount) AS totalDiscount " +
				" FROM order_transaction a " +
				" LEFT JOIN order_detail b " +
				" ON a.transaction_id=b.transaction_id " +
				" AND a.computer_id=b.computer_id " +
				" WHERE a.transaction_status_id=2 " +
				" AND a.sale_date >= " + dateFrom + 
				" AND a.sale_date <= " + dateTo + 
				" GROUP BY a.sale_date";
		
		dbHelper.open();
		
		Cursor cursor = dbHelper.rawQuery(strSql);
		if(cursor.moveToFirst()){
			do{
				Report.ReportDetail reportDetail = 
						new Report.ReportDetail();
				
				reportDetail.setSaleDate(cursor.getLong(cursor.getColumnIndex("sale_date")));
				reportDetail.setTotalBill(cursor.getInt(cursor.getColumnIndex("totalBill")));
				reportDetail.setTotalPrice(cursor.getFloat(cursor.getColumnIndex("totalProductPrice")));
				reportDetail.setDiscount(cursor.getFloat(cursor.getColumnIndex("totalDiscount")));
				reportDetail.setSubTotal(reportDetail.getTotalPrice() - reportDetail.getDiscount());
				reportDetail.setServiceCharge(cursor.getFloat(cursor.getColumnIndex("totalServiceCharge")));
				reportDetail.setVatable(cursor.getFloat(cursor.getColumnIndex("totalVat")));
				reportDetail.setTotalVat(cursor.getFloat(cursor.getColumnIndex("totalVat")));
				reportDetail.setCash(0);
				reportDetail.setTotalPayment(0);
				reportDetail.setDiff(0);
				
				report.reportDetail.add(reportDetail);
				
			}while(cursor.moveToNext());
		}
		cursor.close();
		
		dbHelper.close();
		
		return report;
	}
	
	public List<Report> getSaleReportByProduct(){
		List<Report> reportLst = new ArrayList<Report>();
		
		String strSql = "SELECT a.menu_group_id, b.menu_dept_id, " +
				" a.menu_group_name_0, b.menu_dept_name_0 " +
				" FROM menu_group a " +
				" LEFT JOIN menu_dept b " +
				" ON a.menu_group_id=b.menu_group_id";
		
		dbHelper.open();
		
		Cursor cursor1 = dbHelper.rawQuery(strSql);
		
		if(cursor1.moveToFirst()){
			do{
				Report report = new Report();
				report.setProductGroupName(cursor1.getString(cursor1.getColumnIndex("menu_group_name_0")));
				report.setProductDeptName(cursor1.getString(cursor1.getColumnIndex("menu_dept_name_0")));
			
				strSql = " SELECT d.product_code, b.product_name, " +
						" b.product_price, " +
						" SUM(b.product_amount) AS totalAmount, " +
						" SUM(b.total_product_price) AS totalProductPrice, " +
						" SUM(b.each_product_discount + b.member_discount) AS totalDiscount " +
						" FROM order_transaction a " +
						" LEFT JOIN order_detail b " +
						" ON a.transaction_id=b.transaction_id " +
						" AND a.computer_id=b.computer_id " +
						" LEFT JOIN menu_item c " +
						" ON b.product_id=c.product_id " +
						" LEFT JOIN products d " +
						" ON c.product_id=d.product_id " +
						" WHERE c.menu_dept_id=" + cursor1.getInt(cursor1.getColumnIndex("menu_dept_id")) +
						" AND a.sale_date >= " + dateFrom + 
						" AND a.sale_date <= " + dateTo +
						" GROUP BY d.product_id " +
						" ORDER BY d.product_id ";
				
				Cursor cursor2 = dbHelper.rawQuery(strSql);
			
				if(cursor2.moveToFirst()){
					do{
						Report.ReportDetail reportDetail = 
								new Report.ReportDetail();
						reportDetail.setProductCode(cursor2.getString(cursor2.getColumnIndex("product_code")));
						reportDetail.setProductName(cursor2.getString(cursor2.getColumnIndex("product_name")));
						reportDetail.setProductPrice(cursor2.getFloat(cursor2.getColumnIndex("product_price")));
						reportDetail.setProductAmount(cursor2.getFloat(cursor2.getColumnIndex("totalAmount")));
						reportDetail.setSubTotal(cursor2.getFloat(cursor2.getColumnIndex("totalProductPrice")));
						reportDetail.setDiscount(cursor2.getFloat(cursor2.getColumnIndex("totalDiscount")));
						reportDetail.setTotalPrice(reportDetail.getSubTotal() - reportDetail.getDiscount());
						
						report.reportDetail.add(reportDetail);
					}while(cursor2.moveToNext());
					
					reportLst.add(report);
				}
				cursor2.close();
				
			}while(cursor1.moveToNext());
		}
		cursor1.close();	
		
		dbHelper.close();
		
		return reportLst;
	}
}
