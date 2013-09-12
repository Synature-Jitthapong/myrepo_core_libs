package com.syn.pos;

import java.util.List;

import com.syn.mpos.model.OrderTransaction;

/**
 * 
 * @author j1tth4
 *
 */
public interface Order extends Discount{
	int getMaxOrderDetail(int transactionId, int computerId);

	int addOrderDetail(int transactionId, int computerId, int productId,
			int productType, int vatType, String productName, 
			float qty, float pricePerUnit);
	
	boolean updateOrderDetail(int transactionId, int computerId,
			int orderDetailId, int vatType, float qty, float pricePerUnit);
	
	OrderTransaction.OrderDetail getSummaryTmp(int transactionId, int computerId);
	
	OrderTransaction.OrderDetail getSummary(int transactionId, int computerId);
	
	OrderTransaction.OrderDetail getOrder(int transactionId, int computerId, int orderDetailId);
	
	List<OrderTransaction.OrderDetail> listAllOrdersTmp(int transactionId, int computerId);
	
	List<OrderTransaction.OrderDetail> listAllOrders(int transactionId, int computerId);
	
	List<OrderTransaction> listHoldOrder(int computerId);
	
	boolean deleteOrderDetail(int transactionId, int computerId, int orderDetailId);

	boolean deleteAllOrderDetail(int transactionId, int computerId);
}
