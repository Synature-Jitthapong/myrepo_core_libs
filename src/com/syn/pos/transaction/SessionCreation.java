package com.syn.pos.transaction;

public interface SessionCreation {
	int getMaxSessionId(int shopId, int computerId);

	int getCurrentSession(int shopId, int computerId);

	int addSession(int shopId, int computerId, int openStaffId, float openAmount);

	boolean closeSession(int sessionId, int computerId, int closeStaffId,
			float closeAmount, int isEndday);

	boolean addSessionEnddayDetail(String sessionDate, float totalQtyReceipt,
			float totalAmountReceipt);
}
