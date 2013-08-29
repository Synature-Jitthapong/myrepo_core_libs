package com.syn.mpos.model;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private String productGroupCode;
	private String productGroupName;
	private String productDeptCode;
	private String productDeptName;
	public List<ReportDetail> reportDetail = 
			new ArrayList<ReportDetail>();
	
	public String getProductGroupCode() {
		return productGroupCode;
	}

	public void setProductGroupCode(String productGroupCode) {
		this.productGroupCode = productGroupCode;
	}

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public String getProductDeptCode() {
		return productDeptCode;
	}

	public void setProductDeptCode(String productDeptCode) {
		this.productDeptCode = productDeptCode;
	}

	public String getProductDeptName() {
		return productDeptName;
	}

	public void setProductDeptName(String productDeptName) {
		this.productDeptName = productDeptName;
	}

	public static class ReportDetail{
		private int productId;
		private String productCode;
		private String productName;
		private float productPrice;
		private float productAmount;
		private float productAmountPercent;
		private float subTotal;
		private float discount;
		private float totalPrice;
		private float totalPricePercent;
		private String vat;
		private long saleDate;
		private int totalBill;
		private float serviceCharge;
		private float totalSale;
		private float vatable;
		private float totalVat;
		private float cash;
		private float totalPayment;
		private float diff;
		
		public float getTotalVat() {
			return totalVat;
		}
		public void setTotalVat(float totalVat) {
			this.totalVat = totalVat;
		}
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public float getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(float productPrice) {
			this.productPrice = productPrice;
		}
		public float getProductAmount() {
			return productAmount;
		}
		public void setProductAmount(float productAmount) {
			this.productAmount = productAmount;
		}
		public float getProductAmountPercent() {
			return productAmountPercent;
		}
		public void setProductAmountPercent(float productAmountPercent) {
			this.productAmountPercent = productAmountPercent;
		}
		public float getSubTotal() {
			return subTotal;
		}
		public void setSubTotal(float subTotal) {
			this.subTotal = subTotal;
		}
		public float getDiscount() {
			return discount;
		}
		public void setDiscount(float discount) {
			this.discount = discount;
		}
		public float getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(float totalPrice) {
			this.totalPrice = totalPrice;
		}
		public float getTotalPricePercent() {
			return totalPricePercent;
		}
		public void setTotalPricePercent(float totalPricePercent) {
			this.totalPricePercent = totalPricePercent;
		}
		public String getVat() {
			return vat;
		}
		public void setVat(String vat) {
			this.vat = vat;
		}
		public long getSaleDate() {
			return saleDate;
		}
		public void setSaleDate(long saleDate) {
			this.saleDate = saleDate;
		}
		public int getTotalBill() {
			return totalBill;
		}
		public void setTotalBill(int totalBill) {
			this.totalBill = totalBill;
		}
		public float getServiceCharge() {
			return serviceCharge;
		}
		public void setServiceCharge(float serviceCharge) {
			this.serviceCharge = serviceCharge;
		}
		public float getTotalSale() {
			return totalSale;
		}
		public void setTotalSale(float totalSale) {
			this.totalSale = totalSale;
		}
		public float getVatable() {
			return vatable;
		}
		public void setVatable(float vatable) {
			this.vatable = vatable;
		}
		public float getCash() {
			return cash;
		}
		public void setCash(float cash) {
			this.cash = cash;
		}
		public float getTotalPayment() {
			return totalPayment;
		}
		public void setTotalPayment(float totalPayment) {
			this.totalPayment = totalPayment;
		}
		public float getDiff() {
			return diff;
		}
		public void setDiff(float diff) {
			this.diff = diff;
		}
	}
	
}
