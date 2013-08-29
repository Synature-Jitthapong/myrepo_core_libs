package com.syn.mpos.db;

/**
 * 
 * @author j1tth4
 * 
 */
public interface Discount {
	boolean copyOrderToTmp(int transactionId, int computerId);

	boolean discountEatchProduct(int orderDetailId, int transactionId,
			int computerId, int vatType, float totalPrice, float discount);

	boolean cancelDiscount(int transactionId, int computerId);

	boolean confirmDiscount(int transactionId, int computerId);
}