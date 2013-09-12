package com.syn.pos;

import com.syn.mpos.model.OrderTransaction;

/**
 * 
 * @author j1tth4
 *
 */
public interface Transaction {
	int getMaxTransaction(int computerId);

	int getMaxReceiptId(int computerId, int year, int month);

	int getCurrTransaction(int computerId);

	int openTransaction(int computerId, int shopId, int sessionId, int staffId);
	
	OrderTransaction getTransaction(int transactionId, int computerId);
	
	boolean updateTransactionVat(int transactionId, int computerId, 
			float totalSalePrice, float vatExclude);
	
	boolean successTransaction(int transactionId, int computerId, int staffId);

	boolean holdTransaction(int transactionId, int computerId, String remark);

	boolean prepareTransaction(int transactionId, int computerId);
	
	boolean deleteTransaction(int transactionId, int computerId);
}
