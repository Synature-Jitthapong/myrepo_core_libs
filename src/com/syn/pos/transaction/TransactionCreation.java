package com.syn.pos.transaction;

public interface TransactionCreation {
	int getMaxTransaction(int computerId);

	int getMaxReceiptId(int computerId, int year, int month);

	int getCurrTransaction(int computerId);

	int openTransaction(int computerId, int shopId, int sessionId, int staffId);
	
	boolean updateTransactionVat(int transactionId, int computerId, 
			float totalSalePrice, float vatExclude);
	
	boolean successTransaction(int transactionId, int computerId, int staffId);

	boolean holdTransaction(int transactionId, int computerId, String remark);

	boolean prepareTransaction(int transactionId, int computerId);
	
	boolean deleteTransaction(int transactionId, int computerId);
}
