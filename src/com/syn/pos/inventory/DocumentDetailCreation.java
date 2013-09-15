package com.syn.pos.inventory;

public interface DocumentDetailCreation {
	int getMaxDocumentDetail(int documentId, int shopId);

	boolean addDocumentDetail(int documentId, int shopId, float materialId,
			float materialQty, float materialPrice, String unitName);

	boolean addDocumentDetail(int documentId, int shopId, float materialId,
			float materialQty, float materialPrice, int taxType, String unitName);

	boolean updateDocumentDetail(int docDetailId, int documentId, int shopId,
			int materialId, float materialQty, float materialPrice,
			String unitName);

	boolean updateDocumentDetail(int docDetailId, int documentId, int shopId,
			int materialId, float materialQty, float materialPrice,
			int taxType, String unitName);

	boolean deleteDocumentDetail(int docDetailId, int documentId, int shopId);
}
