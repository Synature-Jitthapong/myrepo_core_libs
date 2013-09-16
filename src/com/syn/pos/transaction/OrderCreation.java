package com.syn.pos.transaction;

import com.syn.pos.DiscountCreation;

public interface OrderCreation extends DiscountCreation {
	int getMaxOrderDetail(int transactionId, int computerId);

	int addOrderDetail(int transactionId, int computerId, int productId,
			int productType, int vatType, String productName, float qty,
			float pricePerUnit);

	boolean updateOrderDetail(int transactionId, int computerId,
			int orderDetailId, int vatType, float qty, float pricePerUnit);

	boolean deleteOrderDetail(int transactionId, int computerId,
			int orderDetailId);

	boolean deleteAllOrderDetail(int transactionId, int computerId);
}
