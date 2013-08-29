package com.syn.mpos.db;

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

	boolean updateTransaction(int transactionId, int computerId, int staffId,
			float transVat, float transExclVat, float serviceCharge,
			float serviceChargeVat);

	boolean successTransaction(int transactionId, int computerId, int staffId);

	boolean holdTransaction(int transactionId, int computerId, String remark);

	boolean prepareTransaction(int transactionId, int computerId);
	
	boolean deleteTransaction(int transactionId, int computerId);
}