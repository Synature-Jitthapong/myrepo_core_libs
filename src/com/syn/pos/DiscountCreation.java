package com.syn.pos;

/**
 * 
 * @author j1tth4
 * 
 */
public interface DiscountCreation {
	boolean copyOrderToTmp(int transactionId, int computerId);

	boolean discountEatchProduct(int orderDetailId, int transactionId,
			int computerId, int vatType, float totalPrice, float discount);

	boolean cancelDiscount(int transactionId, int computerId);

	boolean confirmDiscount(int transactionId, int computerId);
}
