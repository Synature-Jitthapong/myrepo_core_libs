package com.syn.pos;

import java.util.List;

/**
 * 
 * @author j1tth4
 *
 */
public interface PaymentCreation {
	int getMaxPaymentDetailId(int transactionId, int computerId);

	boolean addPaymentDetail(int transactionId, int computerId, int payTypeId,
			float payAmount, String creditCardNo, int expireMonth,
			int expireYear, int bankId, int creditCardTypeId);

	boolean updatePaymentDetail(int transactionId, int computerId,
			int payTypeId, float paymentAmount, String creditCardNo,
			int expireMonth, int expireYear, int bankId, int creditCardTypeId);

	float getTotalPaid(int transactionId, int computerId);
	
	List<com.syn.pos.Payment.PaymentDetail> listPayment(int transactionId, int computerId);
	
	boolean deletePaymentDetail(int paymentId);

	boolean deleteAllPaymentDetail(int transactionId, int computerId);
}
